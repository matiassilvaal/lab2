package edu.microservice.horasextrasservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "horasextras")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HorasextrasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private Long idData;
    private String rut;
    private Integer cantidad;
}

