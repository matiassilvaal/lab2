package edu.microservice.mostrarplanillaservice.service;


import edu.microservice.mostrarplanillaservice.entity.MostrarplanillaEntity;
import edu.microservice.mostrarplanillaservice.repository.MostrarplanillaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;


@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Service
public class MostrarplanillaService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    MostrarplanillaRepository mostrarplanillaRepository;

    public List<MostrarplanillaEntity> getAll(String bearerToken){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", bearerToken);
        mostrarplanillaRepository.deleteAll();
        ResponseEntity<MostrarplanillaEntity[]> mostrarplanillaEntities = restTemplate.exchange("http://calcularplanilla-service/calcularplanilla", HttpMethod.GET, new HttpEntity<>("parameters", headers), MostrarplanillaEntity[].class);
        List<MostrarplanillaEntity> mostrarplanillaEntityList = Arrays.asList(mostrarplanillaEntities.getBody());
        mostrarplanillaRepository.saveAll(mostrarplanillaEntityList);
        return mostrarplanillaRepository.findAll();
    }

}


