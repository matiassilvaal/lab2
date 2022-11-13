package edu.microservice.calcularplanillaservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "calcularplanilla")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalcularplanillaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)

    @Getter
    private Long id;
    private String rut;
    private String apellidosNombres;
    private String categoria;
    private Integer aniosEnEmpresa;
    private Integer sueldoFijo;
    private Integer bonificacionServicios;
    private Integer horasExtras;
    private Double descuentos;
    private Double sueldoBruto;
    private Double cotizacionPrevisional;
    private Double cotizacionSalud;
    private Double sueldoFinal;
}
