package edu.microservice.justificativoservice.service;

import edu.microservice.justificativoservice.entity.JustificativoEntity;
import edu.microservice.justificativoservice.model.Data;
import edu.microservice.justificativoservice.repository.JustificativoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Time;


@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Service
public class JustificativoService {
    static final String HORAMAXIMA = "09:10:00";
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    JustificativoRepository justificativoRepository;

    public Boolean ingresarJustificativo(String fecha, String rut){
        Data info = restTemplate.getForObject("http://data-service/data/entrada/" + rut + "/" + fecha, Data.class);
        if(info == null) return false;
        if(calcularSiJustificativo(info, rut)) {
            justificativoRepository.ingresarJustificativo(info.getId(), rut);
            return true;
        }
        return false;
    }
    public boolean calcularSiJustificativo(Data info, String rut){
        if(justificativoRepository.findByRutAndIdData(rut, info.getId()) != null) return false;
        return info.getHora().compareTo(Time.valueOf(HORAMAXIMA)) >= 0;
    }
    public JustificativoEntity getJustificativo(Long id){
        return justificativoRepository.findById(id).orElse(null);
    }
}


