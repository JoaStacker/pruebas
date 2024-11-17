package com.tp3.pruebas.dominio;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class HistoriaClinica {
    private List<Diagnostico> diagnosticos;

    public HistoriaClinica(List<String> diagnosticos) {
        this.diagnosticos = diagnosticos
                .stream()
                .map(Diagnostico::new)
                .collect(Collectors.toList());
    }

    public Diagnostico buscarDiagnostico(String nombreDiagnostico){
        return this.diagnosticos.stream()
                .filter(diagnostico -> diagnostico.tieneNombre(nombreDiagnostico))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Diagnostico no encontrado."));
    }

    public void agregarEvolucion(String diagnosticoElegido, Doctor doctor, String informe,
                                 String textoPedido, RecetaDigital recetaDigital){
        Diagnostico diagnostico = buscarDiagnostico(diagnosticoElegido);
        diagnostico.agregarEvolucion(doctor, informe, textoPedido, recetaDigital);
    }

    public void agregarDiagnostico(String diagnosticoElegido){
        Diagnostico diagnostico = new Diagnostico(diagnosticoElegido);
        this.diagnosticos.add(diagnostico);
    }
}
