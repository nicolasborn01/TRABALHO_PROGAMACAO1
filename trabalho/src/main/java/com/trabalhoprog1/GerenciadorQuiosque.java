package com.trabalhoprog1;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorQuiosque {
    private static GerenciadorQuiosque instancia;
    private Pedido pedidoAtual;
    private List<Pedido> historicoVendas; 
    private GerenciadorQuiosque() {
        this.historicoVendas = new ArrayList<>();
        this.pedidoAtual = new Pedido();
    }

    public static GerenciadorQuiosque getInstancia() {
        if (instancia == null) {
            instancia = new GerenciadorQuiosque();
        }
        return instancia;
    }

    public Pedido getPedidoAtual() {
        return pedidoAtual;
    }

    public void finalizarPedidoAtual() {
        historicoVendas.add(pedidoAtual);
        pedidoAtual = new Pedido(); 
    }

    public List<Pedido> getHistoricoVendas() {
        return historicoVendas;
    }
}