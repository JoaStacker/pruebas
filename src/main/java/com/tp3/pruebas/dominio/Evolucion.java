package com.tp3.pruebas.dominio;

import lombok.Getter;

@Getter
public class Evolucion {
    private String informe;
    private Doctor doctor;

    public Evolucion(String informe, Doctor doctor) {
        this.informe = informe;
        this.doctor = doctor;
    }

    public boolean tiene(Doctor doctor, String informe){
        return this.doctor.equals(doctor) && this.informe.equals(informe);
    }
}
