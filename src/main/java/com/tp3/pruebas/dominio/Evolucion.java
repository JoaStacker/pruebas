package com.tp3.pruebas.dominio;

import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class Evolucion {
    private String informe;
    private Doctor doctor;
    private PedidoLaboratorio pedidoLaboratorio;
    private RecetaDigital recetaDigital;

    public Evolucion(String informe, Doctor doctor, String textoPedido, List<Medicamento> listaMedicamentos) {
        this.informe = informe;
        this.doctor = doctor;
        if(textoPedido != null){
            this.pedidoLaboratorio = new PedidoLaboratorio(textoPedido);
        }else{
            this.pedidoLaboratorio = null;
        }
        if(listaMedicamentos != null) {
            this.recetaDigital = new RecetaDigital(listaMedicamentos);
        }else{
            this.recetaDigital = null;
        }
    }

    public boolean tiene(Doctor doctor, String informe){
        return this.doctor.equals(doctor) && this.informe.equals(informe);
    }
}
