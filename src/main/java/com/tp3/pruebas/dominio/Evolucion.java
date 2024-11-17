package com.tp3.pruebas.dominio;

import lombok.Getter;

@Getter
public class Evolucion {
    private String informe;
    private Doctor doctor;
    private PedidoLaboratorio pedidoLaboratorio;
    private RecetaDigital recetaDigital;

    public Evolucion(String informe, Doctor doctor, String textoPedido, RecetaDigital recetaDigital) {
        this.informe = informe;
        this.doctor = doctor;
        this.pedidoLaboratorio = new PedidoLaboratorio(textoPedido);
        this.recetaDigital = recetaDigital;
    }

    public boolean tiene(Doctor doctor, String informe){
        return this.doctor.equals(doctor) && this.informe.equals(informe);
    }
}
