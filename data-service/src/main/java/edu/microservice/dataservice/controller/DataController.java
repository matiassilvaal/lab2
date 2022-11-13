package edu.microservice.dataservice.controller;

import edu.microservice.dataservice.entity.DataEntity;
import edu.microservice.dataservice.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/data")
public class DataController {
    @Autowired
    DataService dataService;

    @PostMapping
    public ResponseEntity<DataEntity> uploadFile() {
        if(dataService.readDataFromFile())
            return ResponseEntity.ok().build();
        return ResponseEntity.internalServerError().build();
    }

    @GetMapping
    public ResponseEntity<List<DataEntity>> importar() {
        List<DataEntity> data = dataService.obtenerData();
        if(data.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/entrada/{rut}/{fecha}")
    public DataEntity encontrarEntrada(@PathVariable("rut") String rut, @PathVariable("fecha") String fecha) {
        Date date;
        try{
            date = Date.valueOf(fecha);
        }
        catch (IllegalArgumentException e){
            return null;
        }
        DataEntity data = dataService.encontrarEntrada(rut, date);
        if(data == null)
            return null;
        return data;
    }
    @GetMapping("/salida/{rut}/{fecha}")
    public DataEntity encontrarSalida(@PathVariable("rut") String rut, @PathVariable("fecha") String fecha) {
        Date date;
        try{
            date = Date.valueOf(fecha);
        }
        catch (IllegalArgumentException e){
            return null;
        }
        DataEntity data = dataService.encontrarSalida(rut, date);
        if(data == null)
            return null;
        return data;
    }

    @GetMapping("/fechas")
    @CrossOrigin
    public ResponseEntity<Date[]> obtenerFechas() {
        Date[] fechas = dataService.obtenerFechasUnicas();
        if(fechas == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(fechas);
    }

}
