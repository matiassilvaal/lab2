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

    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<HorasextrasEntity>> getAll(){
        if(horasextrasService.getAll().isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(horasextrasService.getAll());
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