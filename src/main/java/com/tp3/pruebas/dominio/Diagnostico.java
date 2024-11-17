package com.tp3.pruebas.dominio;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Diagnostico {
    private String nombre;
    private List <Evolucion> evoluciones;

    public Diagnostico(String nombre) {
        this.nombre = nombre;
        this.evoluciones = new ArrayList<>();
    }

    public boolean tieneNombre(String nombre){
        return this.nombre.equals(nombre);
    }

    public boolean tieneEvolucion(Doctor doctor, String informe){
        return this.evoluciones.stream()
                .anyMatch(evolucion -> evolucion.tiene(doctor, informe));
    }

    public void agregarEvolucion(Doctor doctor, String informe, String textoPedido, RecetaDigital recetaDigital){
        Evolucion evolucion = new Evolucion(informe, doctor, textoPedido, recetaDigital);
        evoluciones.add(evolucion);
    }
}
