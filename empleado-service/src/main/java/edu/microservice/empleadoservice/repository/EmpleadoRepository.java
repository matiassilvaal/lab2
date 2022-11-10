package edu.microservice.empleadoservice.repository;

import edu.microservice.empleadoservice.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Long> {
}