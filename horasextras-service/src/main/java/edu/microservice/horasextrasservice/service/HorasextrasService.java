package edu.microservice.horasextrasservice.service;

import edu.microservice.horasextrasservice.entity.HorasextrasEntity;
import edu.microservice.horasextrasservice.model.Data;
import edu.microservice.horasextrasservice.repository.HorasextrasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.sql.Time;
import java.util.List;


@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Service
public class HorasextrasService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    HorasextrasRepository horasextrasRepository;

    public List<HorasextrasEntity> getAll(){
        return horasextrasRepository.findAll();
    }
    public Boolean ingresarHorasExtras(Date fecha, String rut){
        Data info = restTemplate.getForObject("http://data-service/data/salida/" + rut + "/" + fecha, Data.class);
        if(info == null) return false;
        Integer calculo = calcularSiHorasExtras(info, rut);
        if(calculo != 0){
            horasextrasRepository.ingresarHorasExtras(info.getId(),rut,calculo);
            return true;
        }
        return false;
    }
    public Integer calcularSiHorasExtras(Data info, String rut){
        if(horasextrasRepository.findByRutAndIdData(rut, info.getId()) != null) return -1;
        int diferencia = info.getHora().getHours()-Time.valueOf("18:00:00").getHours();
        return Math.max(diferencia, 0);
    }
}


