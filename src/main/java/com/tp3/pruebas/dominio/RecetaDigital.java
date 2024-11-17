package com.tp3.pruebas.dominio;

import lombok.Getter;

import java.util.List;

@Getter
public class RecetaDigital {
    private List<Medicamento> medicamentos;

    public RecetaDigital(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }
}
