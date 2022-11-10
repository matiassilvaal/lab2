package edu.microservice.dataservice.controller;

import edu.microservice.dataservice.entity.DataEntity;
import edu.microservice.dataservice.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@RestController
@RequestMapping("/data")
public class DataController {
    @Autowired
    DataService dataService;

    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<DataEntity> uploadFile() {
        if(dataService.readDataFromFile())
            return ResponseEntity.ok().build();
        return ResponseEntity.internalServerError().build();
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<DataEntity>> importar() {
        List<DataEntity> data = dataService.obtenerData();
        if(data.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(data);
    }

}
