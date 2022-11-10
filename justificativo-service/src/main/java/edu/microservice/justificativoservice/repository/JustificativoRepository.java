package edu.microservice.justificativoservice.repository;

import edu.microservice.justificativoservice.entity.JustificativoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JustificativoRepository extends JpaRepository<JustificativoEntity, Long> {
}