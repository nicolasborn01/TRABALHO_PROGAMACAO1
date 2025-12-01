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

public class BebidasController {
    @FXML private Button btComida1; 
    @FXML private Button btComida2;
    @FXML private Button btComida3;

    @FXML
    public void initialize() {
        configurarBotao(btComida1, new Bebida("Coca-Cola", 6.00, "/Imagens/coca-cola.jpg"));
        configurarBotao(btComida2, new Bebida("Suco", 8.00, "/Imagens/suco.jpg"));
        configurarBotao(btComida3, new Bebida("Água", 4.00, "/Imagens/agua.jpg"));
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
    @FXML private void trocarParaLanches() throws IOException { App.setRoot("Lanches"); }
    @FXML private void trocarParaPagamento() throws IOException { App.setRoot("Pagamento"); }
}