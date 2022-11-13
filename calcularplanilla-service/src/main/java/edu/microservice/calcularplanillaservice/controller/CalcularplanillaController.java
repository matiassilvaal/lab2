package edu.microservice.calcularplanillaservice.controller;


import edu.microservice.calcularplanillaservice.entity.CalcularplanillaEntity;
import edu.microservice.calcularplanillaservice.service.CalcularplanillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/calcularplanilla")
public class CalcularplanillaController {

    @Autowired
    CalcularplanillaService calcularplanillaService;

    @PostMapping
    public ResponseEntity<CalcularplanillaEntity> calculosDePlanilla(){
        if(calcularplanillaService.calcularPlanilla()){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<List<CalcularplanillaEntity>> getAll(){
        if(calcularplanillaService.getAll().isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(calcularplanillaService.getAll());
    }


}