package com.trabalhoprog1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pedido implements Imprimivel {
    private int numeroAtendimento;
    private List<ItemCardapio> itens;
    private StatusPedido status; 

    public Pedido() {
        this.itens = new ArrayList<>();
        this.status = StatusPedido.EM_PREPARO; 
        this.numeroAtendimento = new Random().nextInt(900) + 100;
    }

    public void adicionarItem(ItemCardapio item) {
        itens.add(item);
    }

    public void removerUltimoItem() {
        if (!itens.isEmpty()) {
            itens.remove(itens.size() - 1);
        }
    }

    public void removerItem(ItemCardapio item) {
        itens.remove(item);
    }

    public double getValorTotal() {
        double total = 0;
        for (ItemCardapio item : itens) {
            total += item.getPreco();
        }
        return total;
    }

    public int getNumeroAtendimento() { return numeroAtendimento; }
    
    public List<ItemCardapio> getItens() { return itens; }
    public StatusPedido getStatus() { return status; }
    public void setStatus(StatusPedido status) { this.status = status; }

    public String getResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido #").append(numeroAtendimento).append("\n\n");
        for (ItemCardapio item : itens) {
            sb.append(String.format("%-20s R$ %.2f\n", item.getNome(), item.getPreco()));
        }
        return sb.toString();
    }
}