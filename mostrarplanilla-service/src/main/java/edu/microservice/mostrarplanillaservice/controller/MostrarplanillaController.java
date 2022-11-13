package edu.microservice.mostrarplanillaservice.controller;



import edu.microservice.mostrarplanillaservice.entity.MostrarplanillaEntity;
import edu.microservice.mostrarplanillaservice.service.MostrarplanillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/mostrarplanilla")
public class MostrarplanillaController {

    @Autowired
    MostrarplanillaService mostrarplanillaService;

    @GetMapping
    public ResponseEntity<List<MostrarplanillaEntity>> getAll(){
        if(mostrarplanillaService.getAll().isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(mostrarplanillaService.getAll());
    }

}