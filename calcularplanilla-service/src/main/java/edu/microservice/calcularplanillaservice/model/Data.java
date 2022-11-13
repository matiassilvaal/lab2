package edu.microservice.calcularplanillaservice.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class Data {
    private Long id;
    private Date fecha;
    private Time hora;
    private String rut;
}
