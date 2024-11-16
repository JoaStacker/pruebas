package com.tp3.pruebas.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tp3.pruebas.dominio.Diagnostico;
import com.tp3.pruebas.dominio.Evolucion;
import com.tp3.pruebas.dominio.HistoriaClinica;
import com.tp3.pruebas.dominio.Paciente;

public class JsonParser {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String informeDesdeJson(JsonNode json){
        return json.get("informe").asText("");
    }

    public static JsonNode pacienteAJson(Paciente paciente){
        ObjectNode json = mapper.createObjectNode();
        json.put("nombre", paciente.getNombre());
        json.put("dni", paciente.getDni());
        json.put("historiaClinica", historiaClinicaAJson(paciente.getHistoriaClinica()));

        return json;
    }

    public static JsonNode historiaClinicaAJson(HistoriaClinica historiaClinica){
        ObjectNode json = mapper.createObjectNode();
        ArrayNode array = mapper.createArrayNode();
        historiaClinica.getDiagnosticos().forEach(diagnostico -> array.add(diagnosticoAJson(diagnostico)));

        json.set("diagnosticos", array);

        return json;
    }

    public static JsonNode diagnosticoAJson(Diagnostico diagnostico){
        ObjectNode json = mapper.createObjectNode();
        ArrayNode array = mapper.createArrayNode();
        diagnostico.getEvoluciones().forEach(evolucion -> array.add(evolucionAJson(evolucion)));

        json.put("nombre", diagnostico.getNombre());
        json.set("evoluciones", array);

        return json;
    }

    public static JsonNode evolucionAJson(Evolucion evolucion){
        ObjectNode json = mapper.createObjectNode();

        json.put("informe", evolucion.getInforme());
        json.put("doctor", evolucion.getDoctor().getNombre());

        return json;
    }
}
