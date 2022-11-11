package edu.microservice.horasextrasservice.controller;

import edu.microservice.horasextrasservice.entity.HorasextrasEntity;
import edu.microservice.horasextrasservice.service.HorasextrasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;


@RestController
@RequestMapping("/horasextras")
public class HorasextrasController {
    @Autowired
    HorasextrasService horasextrasService;

    @GetMapping("/suma/{rut}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Integer> obtenerSumaHorasExtras(String rut){
        Integer suma = horasextrasService.obtenerSumaHorasExtras(rut);
        if(suma == null) return ResponseEntity.ok(0);
        return ResponseEntity.ok(suma);
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<HorasextrasEntity> ingresarHorasExtras(@RequestParam String fecha, @RequestParam String rut){
        if(!fecha.isBlank() && !rut.isBlank()) {
            if (horasextrasService.ingresarHorasExtras(Date.valueOf(fecha), rut))
                return ResponseEntity.ok().build();
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.badRequest().build();
    }

}