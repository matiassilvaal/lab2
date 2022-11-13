package edu.microservice.mostrarplanillaservice.repository;

import edu.microservice.mostrarplanillaservice.entity.MostrarplanillaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MostrarplanillaRepository extends JpaRepository<MostrarplanillaEntity, Long> {
}