package edu.microservice.justificativoservice.controller;

import edu.microservice.justificativoservice.entity.JustificativoEntity;
import edu.microservice.justificativoservice.service.JustificativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/justificativo")
public class JustificativoController {
    @Autowired
    JustificativoService justificativoService;

    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<JustificativoEntity> ingresarJustificativo(@RequestParam String fecha, @RequestParam String rut){
        if(justificativoService.ingresarJustificativo(fecha, rut))
            return ResponseEntity.ok().build();
        return ResponseEntity.internalServerError().build();
    }
}