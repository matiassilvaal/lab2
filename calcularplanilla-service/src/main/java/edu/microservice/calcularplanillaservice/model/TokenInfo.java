package edu.microservice.calcularplanillaservice.model;

import java.io.Serial;
import java.io.Serializable;

public class TokenInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String jwtToken;

    public TokenInfo(String jwtToken) {
        this.jwtToken = jwtToken;
    }

}

