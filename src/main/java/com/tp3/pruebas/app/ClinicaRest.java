package com.tp3.pruebas.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.tp3.pruebas.dominio.Doctor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClinicaRest {

    private SistemaClinica sistemaClinica;
    private final Doctor doctorPrueba = new Doctor("Dr. Pruebas"); // debe tomarse de la sesion en el futuro.

    public ClinicaRest(SistemaClinica sistemaClinica){
        this.sistemaClinica = sistemaClinica;
    }

    @PostMapping("/paciente/{dniPaciente}/diagnostico/{nombreDiagnostico}/evolucion")
    public ResponseEntity<JsonNode> agregarEvolucion(@PathVariable String dniPaciente,
                                              @PathVariable String nombreDiagnostico,
                                              @RequestBody JsonNode json){
        var paciente = this.sistemaClinica.agregarEvolucion(
                doctorPrueba,
                dniPaciente,
                nombreDiagnostico,
                JsonParser.informeDesdeJson(json));

        return new ResponseEntity<>(JsonParser.pacienteAJson(paciente), HttpStatus.CREATED);
    }

    @GetMapping("/paciente/{dniPaciente}")
    public ResponseEntity<JsonNode> buscarPaciente(@PathVariable String dniPaciente){
        var paciente = this.sistemaClinica.buscarPaciente(dniPaciente);
        return new ResponseEntity<>(JsonParser.pacienteAJson(paciente), HttpStatus.OK);
    }


    @PostMapping("/paciente/{dniPaciente}/diagnostico/{nombreDiagnostico}/historia-clinica")
    public ResponseEntity<JsonNode> agregarDiagnosticoAHistoriaClinica(@PathVariable String dniPaciente,
                                                     @PathVariable String nombreDiagnostico){
        var paciente = this.sistemaClinica.agregarDiagnosticoAHistoriaClinica(
                dniPaciente,
                nombreDiagnostico);

        return new ResponseEntity<>(JsonParser.pacienteAJson(paciente), HttpStatus.CREATED);
    }
}
