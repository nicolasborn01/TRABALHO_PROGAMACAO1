package com.trabalhoprog1;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;

public class LanchesController {
    @FXML private Button btComida1;
    @FXML private Button btComida2;
    @FXML private Button btComida3;

    @FXML
    public void initialize() {
        // Exemplo: Botão 1 é X-Burguer
        configurarBotao(btComida1, new Lanche("X-Burguer", 25.00, "/Imagens/x-burguer.jpg"));
        configurarBotao(btComida2, new Lanche("Pastel", 20.00, "/Imagens/pastel.jpeg"));
        configurarBotao(btComida3, new Lanche("Hot Dog", 15.00, "/Imagens/hot-dog.png"));
    }

    private void configurarBotao(Button botao, ItemCardapio item) {
        if (botao == null) return;

        botao.setText(""); 
        botao.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");

        VBox layout = new VBox(5);
        layout.setAlignment(Pos.CENTER); 

        ImageView view = null;
        try {
            if (getClass().getResourceAsStream(item.getCaminhoImagem()) != null) {
                view = new ImageView(new Image(getClass().getResourceAsStream(item.getCaminhoImagem())));
                view.setFitHeight(110); 
                view.setFitWidth(130);  
                view.setPreserveRatio(true);
                
                DropShadow sombra = new DropShadow();
                sombra.setRadius(10.0);
                sombra.setColor(Color.color(0, 0, 0, 0.3));
                view.setEffect(sombra);
            }
        } catch (Exception e) {
            System.out.println("Erro imagem: " + item.getNome());
        }

        Label lblNome = new Label(item.getNome());
        lblNome.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #333;");
        
        Label lblPreco = new Label("R$ " + String.format("%.2f", item.getPreco()));
        lblPreco.setStyle("-fx-font-size: 13px; -fx-text-fill: #27ae60; -fx-font-weight: bold;");

        if (view != null) {
            layout.getChildren().add(view);
        }
        layout.getChildren().addAll(lblNome, lblPreco);

        botao.setGraphic(layout);

        botao.setOnAction(event -> {
            GerenciadorQuiosque.getInstancia().getPedidoAtual().adicionarItem(item);
            System.out.println("Adicionado: " + item.getNome());
        });
    }

    @FXML private void btRemoverUltimoAction() {
        GerenciadorQuiosque.getInstancia().getPedidoAtual().removerUltimoItem();
    }
    @FXML private void trocarParaBebidas() throws IOException { App.setRoot("Bebidas"); }
    @FXML private void trocarParaPagamento() throws IOException { App.setRoot("Pagamento"); }

    @FXML
    private void irParaCozinha() throws IOException {
        App.setRoot("Cozinha");
    }
}