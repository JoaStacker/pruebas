package com.tp3.pruebas.app;

import com.tp3.pruebas.dominio.Doctor;
import com.tp3.pruebas.dominio.Paciente;
import com.tp3.pruebas.repositorio.RepositorioPaciente;

public class SistemaClinica {
    private final RepositorioPaciente repositorioPaciente;

    public SistemaClinica(RepositorioPaciente repositorioPaciente) {
        this.repositorioPaciente = repositorioPaciente;
    }

    public Paciente agregarEvolucion(Doctor doctor, String dniPaciente, String diagnosticoElegido, String informe){
        Paciente paciente = repositorioPaciente.buscarPaciente(dniPaciente).orElseThrow(() -> new RuntimeException("Paciente inexistente"));
        paciente.agregarEvolucion(diagnosticoElegido, doctor, informe);
        repositorioPaciente.actualizarPaciente(paciente);

        return paciente;
    }
}
