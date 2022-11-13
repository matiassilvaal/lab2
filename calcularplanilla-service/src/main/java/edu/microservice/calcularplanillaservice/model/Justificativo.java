package edu.microservice.calcularplanillaservice.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class Justificativo {
    private Long id;
    private Long idData;
    private String rut;
}
