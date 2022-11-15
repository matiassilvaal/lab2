package edu.microservice.calcularplanillaservice.model;

import java.io.Serial;
import java.io.Serializable;

public class UserInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String usuario;

    private String clave;

    public UserInfo(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getClave() {
        return clave;
    }

}
