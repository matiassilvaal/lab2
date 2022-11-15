package edu.microservice.calcularplanillaservice.repository;

import edu.microservice.calcularplanillaservice.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Integer> {
    UserData findByUsuario(String username);
}
