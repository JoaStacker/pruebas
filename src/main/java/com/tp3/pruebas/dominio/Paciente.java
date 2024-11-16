package com.tp3.pruebas.dominio;

import java.util.List;

public class Paciente {
    private String dni;
    private String nombre;
    private HistoriaClinica historiaClinica;

    public Paciente(String dni, String nombre, List<String> diagnosticosPreexistentes) {
        this.dni = dni;
        this.nombre = nombre;
        this.historiaClinica = new HistoriaClinica(diagnosticosPreexistentes);
    }

    public Diagnostico buscarDiagnostico(String nombreDiagnostico){
        return this.historiaClinica.buscarDiagnostico(nombreDiagnostico);
    }

    public void agregarEvolucion(String diagnosticoElegido, Doctor doctor, String informe){
        this.historiaClinica.agregarEvolucion(diagnosticoElegido, doctor, informe);
    }
}
