package com.tp3.pruebas.dominio;

import lombok.Getter;

@Getter
public class Medicamento {
    private String nombre;
    private String cantidad;

    public Medicamento(String nombre, String cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }
}
