package com.trabalhoprog1;

public class Lanche extends ItemCardapio {
    public Lanche(String nome, double preco, String caminhoImagem) {
        super(nome, preco, caminhoImagem);
    }

    @Override
    public String getTipo() { return "Lanche"; }
}