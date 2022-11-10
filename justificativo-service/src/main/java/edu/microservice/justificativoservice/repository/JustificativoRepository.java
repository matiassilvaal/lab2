package edu.microservice.justificativoservice.repository;

import edu.microservice.justificativoservice.entity.JustificativoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
public interface JustificativoRepository extends JpaRepository<JustificativoEntity, Long> {
    JustificativoEntity findByRutAndIdData(String rut, Long idData);

    // make post query
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO justificativos (id_data, rut) VALUES (:id_data, :rut)", nativeQuery = true)
    void ingresarJustificativo(@Param("id_data") Long id_data, @Param("rut") String rut);

}