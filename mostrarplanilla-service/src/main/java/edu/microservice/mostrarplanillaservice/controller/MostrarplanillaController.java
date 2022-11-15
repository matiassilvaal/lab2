package edu.microservice.mostrarplanillaservice.controller;



import edu.microservice.mostrarplanillaservice.entity.MostrarplanillaEntity;
import edu.microservice.mostrarplanillaservice.service.MostrarplanillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/mostrarplanilla")
public class MostrarplanillaController {

    @Autowired
    MostrarplanillaService mostrarplanillaService;

    @GetMapping
    public ResponseEntity<List<MostrarplanillaEntity>> getAll(@RequestHeader("Authorization") String bearerToken){
        if(mostrarplanillaService.getAll(bearerToken).isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(mostrarplanillaService.getAll(bearerToken));
    }

}