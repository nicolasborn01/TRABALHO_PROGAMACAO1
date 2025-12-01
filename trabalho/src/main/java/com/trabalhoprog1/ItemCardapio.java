package com.trabalhoprog1;

public abstract class ItemCardapio {
    protected String nome;
    protected double preco;
    protected String caminhoImagem;

    public ItemCardapio(String nome, double preco, String caminhoImagem) {
        this.nome = nome;
        this.preco = preco;
        this.caminhoImagem = caminhoImagem;
    }

    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public String getCaminhoImagem() { return caminhoImagem; }

    public abstract String getTipo();
}