package edu.microservice.calcularplanillaservice.service;

import edu.microservice.calcularplanillaservice.entity.UserData;
import edu.microservice.calcularplanillaservice.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDataService {

    @Autowired
    UserDataRepository userDataRepository;

    public List<UserData> getAll() {
        return userDataRepository.findAll();
    }

    public UserData getUserById(int id) {
        return userDataRepository.findById(id).orElse(null);
    }

    public UserData save(UserData userData) {
        UserData userDataNew = userDataRepository.save(userData);
        return userDataNew;
    }

}

