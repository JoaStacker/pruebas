package com.tp3.pruebas.repositorio;

import com.tp3.pruebas.dominio.Paciente;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.Mapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class RepositorioPaciente {

    private Map<String, Paciente> pacientes;

    public RepositorioPaciente() {
        this.pacientes = new HashMap<>();

        // quitar esto cuando implemente la base de datos.
        cargarPacientes();
    }

    public Optional<Paciente> buscarPaciente(String dniPaciente){
        return Optional.ofNullable(pacientes.get(dniPaciente));
    }

    public void actualizarPaciente(Paciente paciente){}

    private void cargarPacientes(){
        pacientes.put("123456", new Paciente("123456", "Juan Doe", List.of("Angina")));
    }
}
