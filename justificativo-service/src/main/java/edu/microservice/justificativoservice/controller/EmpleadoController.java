package edu.microservice.justificativoservice.controller;

import edu.microservice.empleadoservice.entity.EmpleadoEntity;
import edu.microservice.empleadoservice.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<EmpleadoEntity>> listar() {
        List<EmpleadoEntity> empleados = empleadoService.obtenerEmpleados();
        if (empleados.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(empleados);
    }
}