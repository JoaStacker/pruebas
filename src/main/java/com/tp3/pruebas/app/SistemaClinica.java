package com.tp3.pruebas.app;

import com.tp3.pruebas.dominio.Diagnostico;
import com.tp3.pruebas.dominio.Doctor;
import com.tp3.pruebas.dominio.Paciente;
import com.tp3.pruebas.repositorio.RepositorioPaciente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SistemaClinica {
    private final RepositorioPaciente repositorioPaciente;

    public SistemaClinica(RepositorioPaciente repositorioPaciente) {
        this.repositorioPaciente = repositorioPaciente;
    }

    public Paciente buscarPaciente(String dniPaciente){

      return this.repositorioPaciente.buscarPaciente(dniPaciente).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
    }

    public Paciente agregarEvolucion(Doctor doctor, String dniPaciente, String diagnosticoElegido, String informe){
        Paciente paciente = repositorioPaciente.buscarPaciente(dniPaciente).orElseThrow(() -> new RuntimeException("Paciente inexistente"));
        paciente.agregarEvolucion(diagnosticoElegido, doctor, informe);
        repositorioPaciente.actualizarPaciente(paciente);

        return paciente;
    }

    public Paciente agregarDiagnosticoAHistoriaClinica(String dniPaciente, String diagnosticoElegido){
        Paciente paciente = repositorioPaciente.buscarPaciente(dniPaciente)
                .orElseThrow(() -> new RuntimeException("Paciente inexistente"));

        List<Diagnostico> diagnosticosCIE = repositorioPaciente.buscarDiagnosticosCIE()
                .orElseThrow(() -> new RuntimeException("No se encontraron diagnósticos CIE disponibles"));

        boolean diagnosticoExiste = diagnosticosCIE.stream()
                .anyMatch(diagnostico -> diagnostico.getNombre().equals(diagnosticoElegido));

        if (!diagnosticoExiste) {
            throw new RuntimeException("El diagnóstico elegido no existe en el CIE.");
        }
        paciente.agregarDiagnostico(diagnosticoElegido);
        repositorioPaciente.actualizarPaciente(paciente);

        return paciente;
    }
}
