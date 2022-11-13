package edu.microservice.calcularplanillaservice.repository;

import edu.microservice.calcularplanillaservice.entity.CalcularplanillaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface CalcularplanillaRepository extends JpaRepository<CalcularplanillaEntity, Long> {
    /*HorasextrasEntity findByRutAndIdData(String rut, Long idData);
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO horasextras (id_data, rut, cantidad) VALUES (:id_data, :rut, :cantidad)", nativeQuery = true)
    void ingresarHorasExtras(@Param("id_data") Long id_data, @Param("rut") String rut, @Param("cantidad") Integer cantidad);*/

}