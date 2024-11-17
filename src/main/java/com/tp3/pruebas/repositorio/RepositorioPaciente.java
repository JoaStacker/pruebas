package com.tp3.pruebas.repositorio;

import com.tp3.pruebas.dominio.Diagnostico;
import com.tp3.pruebas.dominio.Paciente;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.Mapping;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class RepositorioPaciente {

    private Map<String, Paciente> pacientes;
    private List<Diagnostico> diagnosticosCIE;

    public RepositorioPaciente() {
        this.pacientes = new HashMap<>();
        this.diagnosticosCIE = new ArrayList<>();

        // quitar esto cuando implemente la base de datos.
        cargarPacientes();
        cargarDiagnosticosCIE();
    }

    public Optional<Paciente> buscarPaciente(String dniPaciente){
        return Optional.ofNullable(pacientes.get(dniPaciente));
    }

    public Optional<List<Diagnostico>> buscarDiagnosticosCIE(){
        return Optional.ofNullable(diagnosticosCIE);
    }

    public void actualizarPaciente(Paciente paciente){}

    private void cargarPacientes(){
//        pacientes.put("123456", new Paciente("123456", "Anakin Skywalker", List.of("Angina")));
    }

    private void cargarDiagnosticosCIE(){
//        pacientes.put(new Diagnostico("Angina"));
    }
}
