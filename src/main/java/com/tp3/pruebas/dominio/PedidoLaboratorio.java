package com.tp3.pruebas.dominio;

import lombok.Getter;

@Getter
public class PedidoLaboratorio {
    private String textoPedido;

    public PedidoLaboratorio(String textoPedido) {
        this.textoPedido = textoPedido;
    }
}
