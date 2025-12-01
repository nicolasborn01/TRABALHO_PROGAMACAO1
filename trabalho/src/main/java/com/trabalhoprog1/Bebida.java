package com.trabalhoprog1;

public class Bebida extends ItemCardapio {
    public Bebida(String nome, double preco, String caminhoImagem) {
        super(nome, preco, caminhoImagem);
    }

    @Override
    public String getTipo() { return "Bebida"; }
}