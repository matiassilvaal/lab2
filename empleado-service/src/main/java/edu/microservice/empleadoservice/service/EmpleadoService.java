package edu.microservice.empleadoservice.service;

import edu.microservice.empleadoservice.entity.EmpleadoEntity;
import edu.microservice.empleadoservice.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Service
public class EmpleadoService {
    @Autowired
    EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public List<EmpleadoEntity> obtenerEmpleados() {
        return empleadoRepository.findAll();
    }


}


