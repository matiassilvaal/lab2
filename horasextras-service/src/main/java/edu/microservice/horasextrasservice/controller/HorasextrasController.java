package edu.microservice.horasextrasservice.controller;

import edu.microservice.horasextrasservice.entity.HorasextrasEntity;
import edu.microservice.horasextrasservice.service.HorasextrasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/horasextras")
public class HorasextrasController {
    @Autowired
    HorasextrasService horasextrasService;

    @GetMapping("/suma/{rut}")
    public ResponseEntity<Integer> obtenerSumaHorasExtras(String rut){
        Integer suma = horasextrasService.obtenerSumaHorasExtras(rut);
        if(suma == null) return ResponseEntity.ok(0);
        return ResponseEntity.ok(suma);
    }

    @PostMapping
    public ResponseEntity<HorasextrasEntity> ingresarHorasExtras(@RequestParam String fecha, @RequestParam String rut){
        System.out.println("fecha: " + fecha + " rut: " + rut);
        if(!fecha.isBlank() && !rut.isBlank()) {
            if (horasextrasService.ingresarHorasExtras(fecha, rut))
                return ResponseEntity.ok().build();
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.badRequest().build();
    }

}