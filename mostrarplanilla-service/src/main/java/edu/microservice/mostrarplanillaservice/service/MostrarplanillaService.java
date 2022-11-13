package edu.microservice.mostrarplanillaservice.service;


import edu.microservice.mostrarplanillaservice.entity.MostrarplanillaEntity;
import edu.microservice.mostrarplanillaservice.repository.MostrarplanillaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;


@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Service
public class MostrarplanillaService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    MostrarplanillaRepository mostrarplanillaRepository;

    public List<MostrarplanillaEntity> getAll(){
        mostrarplanillaRepository.deleteAll();
        MostrarplanillaEntity[] mostrarplanillaEntities = restTemplate.getForObject("http://calcularplanilla-service/calcularplanilla", MostrarplanillaEntity[].class);
        List<MostrarplanillaEntity> mostrarplanillaEntityList = Arrays.asList(mostrarplanillaEntities);
        mostrarplanillaRepository.saveAll(mostrarplanillaEntityList);
        return mostrarplanillaRepository.findAll();
    }

}


