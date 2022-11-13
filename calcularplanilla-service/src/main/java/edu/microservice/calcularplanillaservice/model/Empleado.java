package edu.microservice.calcularplanillaservice.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {
    private Long id;
    private String rut;
    private String nombres;
    private String apellidos;
    private Date fechaDeNacimiento;
    private String categoria;
    private Date fechaDeIngreso;
}
