package edu.microservice.calcularplanillaservice.service;

import edu.microservice.calcularplanillaservice.entity.CalcularplanillaEntity;
import edu.microservice.calcularplanillaservice.model.Data;
import edu.microservice.calcularplanillaservice.model.Empleado;
import edu.microservice.calcularplanillaservice.model.Justificativo;
import edu.microservice.calcularplanillaservice.repository.CalcularplanillaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;


@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Service
public class CalcularplanillaService {
    static final String HORAMAXIMA = "09:10:00";
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CalcularplanillaRepository calcularplanillaRepository;


    public List<CalcularplanillaEntity> getAll(){
        return calcularplanillaRepository.findAll();
    }

    public Integer calcularAniosDeServicio(Empleado empleado){
        return 2022-(empleado.getFechaDeIngreso().getYear()+1900);
    }
    public Integer sueldoFijo(Empleado empleado){
        switch (empleado.getCategoria()) {
            case "A" -> {
                return 1700000;
            }
            case "B" -> {
                return 1200000;
            }
            case "C" -> {
                return 800000;
            }
            default -> {
                return 0;
            }
        }
    }
    public Integer montoAniosDeServicio(Empleado empleado, Integer sueldoFijo){
        int anios = calcularAniosDeServicio(empleado);
        if(anios >= 5 && anios < 10) return sueldoFijo * 5 / 100;
        else if (anios >= 10 && anios < 15) return sueldoFijo * 8 / 100;
        else if (anios >= 15 && anios < 20) return sueldoFijo * 11 / 100;
        else if (anios >= 20 && anios < 25) return sueldoFijo * 14 / 100;
        else if (anios >= 25) return sueldoFijo * 17 / 100;
        return 0;
    }
    public Integer calcularHorasExtras(Empleado empleado){
        Integer sum = restTemplate.getForObject("http://horasextras-service/horasextras/suma/" + empleado.getRut(), Integer.class);
        return calcularHorasExtrasCategoria(empleado, sum);
    }
    public Integer calcularHorasExtrasCategoria(Empleado empleado, Integer horasExtras){
        if(horasExtras == null) return 0;
        return switch (empleado.getCategoria()) {
            case "A" -> horasExtras * 25000;
            case "B" -> horasExtras * 20000;
            case "C" -> horasExtras * 10000;
            default -> 0;
        };
    }
    public Double calcularDescuentos(Empleado empleado, Integer sueldoFijo) {
        Date[] fechas= restTemplate.getForObject("http://data-service/data/fechas", Date[].class);
        return calcularDescuentoPorFecha(empleado, sueldoFijo, fechas);
    }
    public Double calcularDescuentoPorFecha(Empleado empleado, Integer sueldoFijo, Date[] fechas){
        double descuento = 0.0;
        for(Date fecha : fechas){
            Data entrada = restTemplate.getForObject("http://data-service/data/entrada/" + empleado.getRut() + "/" + fecha, Data.class);
            if(entrada != null){
                descuento += calcularPorHoraDescuento(entrada, sueldoFijo);
            }
        }
        return descuento;
    }
    public Double calcularPorHoraDescuento(Data entrada, Integer sueldoFijo) {
        Justificativo justificativo  = restTemplate.getForObject("http://justificativo-service/justificativo/" + entrada.getId(), Justificativo.class);
        if (entrada.getHora().compareTo(Time.valueOf("08:10:00")) > 0 && entrada.getHora().compareTo(Time.valueOf("08:25:00")) < 0)
            return sueldoFijo * 0.01;
        else if (entrada.getHora().compareTo(Time.valueOf("08:25:00")) > 0 && entrada.getHora().compareTo(Time.valueOf("08:45:00")) < 0)
            return sueldoFijo * 0.03;
        else if (entrada.getHora().compareTo(Time.valueOf("08:45:00")) > 0 && entrada.getHora().compareTo(Time.valueOf(HORAMAXIMA)) < 0)
            return sueldoFijo * 0.06;
        else if (entrada.getHora().compareTo(Time.valueOf(HORAMAXIMA)) > 0 && justificativo == null)
            return sueldoFijo * 0.15;
        return 0.0;
    }
    public Double calcularSueldoBruto(Empleado empleado){
        Integer sueldoFijo = sueldoFijo(empleado);
        Integer montoAniosDeServicio = montoAniosDeServicio(empleado, sueldoFijo);
        Integer horasExtras = calcularHorasExtras(empleado);
        return (double) sueldoFijo + (double) montoAniosDeServicio + (double) horasExtras;
    }
    public Double calcularContizacionPrevisional(Empleado empleado){
        Double sueldoBruto = calcularSueldoBruto(empleado);
        return (sueldoBruto-calcularDescuentos(empleado, sueldoFijo(empleado)))*0.1;
    }
    public Double calcularCotizacionSalud(Empleado empleado){
        Double sueldoBruto = calcularSueldoBruto(empleado);
        return (sueldoBruto-calcularDescuentos(empleado, sueldoFijo(empleado)))*0.08;
    }
    public Double calcularSueldoFinal(Empleado empleado){
        Double sueldoBruto = calcularSueldoBruto(empleado);
        Double descuentos = calcularDescuentos(empleado, sueldoFijo(empleado));
        Double cotizacionPrevisional = calcularContizacionPrevisional(empleado);
        Double cotizacionSalud = calcularCotizacionSalud(empleado);
        return sueldoBruto-descuentos-cotizacionPrevisional-cotizacionSalud;
    }

    public Boolean calcularPlanilla(){
        Empleado[] empleados = restTemplate.getForObject("http://empleado-service/empleado", Empleado[].class);
        if(empleados == null) return false;
        List<Empleado> listaEmpleados= Arrays.asList(empleados);
        return calcularPlanilla(listaEmpleados);
    }
    public Boolean calcularPlanilla(List<Empleado> empleados){
        borrarPlanilla();
        double resultado;
        try{
            for (Empleado empleado : empleados){
                CalcularplanillaEntity planilla = new CalcularplanillaEntity();
                planilla.setRut(empleado.getRut());
                planilla.setApellidosNombres(empleado.getApellidos()+" "+empleado.getNombres());
                planilla.setCategoria(empleado.getCategoria());
                planilla.setAniosEnEmpresa(calcularAniosDeServicio(empleado));
                planilla.setSueldoFijo(sueldoFijo(empleado));
                planilla.setBonificacionServicios(montoAniosDeServicio(empleado,planilla.getSueldoFijo()));
                planilla.setHorasExtras(calcularHorasExtras(empleado));
                planilla.setDescuentos(calcularDescuentos(empleado,planilla.getSueldoFijo()));
                planilla.setSueldoBruto(calcularSueldoBruto(empleado));
                planilla.setCotizacionPrevisional((resultado = calcularContizacionPrevisional(empleado)) > 0 ? resultado : 0);
                planilla.setCotizacionSalud((resultado = calcularCotizacionSalud(empleado)) > 0 ? resultado : 0);
                planilla.setSueldoFinal((resultado = calcularSueldoFinal(empleado)) > 0 ? resultado : 0);
                guardarPlanilla(planilla);
            }
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    public void guardarPlanilla(CalcularplanillaEntity planilla){
        calcularplanillaRepository.save(planilla);
    }
    public void borrarPlanilla(){
        calcularplanillaRepository.deleteAll();
    }
}


