package com.trabalhoprog1;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class PagamentoController {

    @FXML private Label lblTotal;
    @FXML private VBox boxItens; 

    @FXML
    public void initialize() {
        atualizarTela();
    }

    private void atualizarTela() {
        boxItens.getChildren().clear(); 
        Pedido pedido = GerenciadorQuiosque.getInstancia().getPedidoAtual();

       
        for (ItemCardapio item : pedido.getItens()) {
            HBox linha = new HBox(10); 
            linha.setAlignment(Pos.CENTER_LEFT);
            linha.setStyle("-fx-border-color: lightgray; -fx-border-width: 0 0 1 0; -fx-padding: 10;");

            
            Label lblInfo = new Label(item.getNome() + " - R$ " + String.format("%.2f", item.getPreco()));
            lblInfo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            Button btnRemover = new Button("X");
            btnRemover.setStyle("-fx-background-color: #ff4d4d; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;");
            
            btnRemover.setOnAction(e -> {
                pedido.removerItem(item);
                atualizarTela(); 
            });

            linha.getChildren().addAll(lblInfo, spacer, btnRemover);
            
            boxItens.getChildren().add(linha);
        }

        if (pedido.getItens().isEmpty()) {
            Label vazio = new Label("Seu carrinho está vazio.");
            vazio.setStyle("-fx-font-size: 18px; -fx-text-fill: gray;");
            boxItens.getChildren().add(vazio);
        }

        lblTotal.setText("TOTAL: R$ " + String.format("%.2f", pedido.getValorTotal()));
    }

    @FXML
    private void confirmarPagamento() throws IOException {
        Pedido pedido = GerenciadorQuiosque.getInstancia().getPedidoAtual();

        if (pedido.getItens().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Atenção");
            alert.setContentText("Você não pode finalizar um pedido vazio!");
            alert.showAndWait();
            return;
        }

        int numeroPedido = pedido.getNumeroAtendimento();
        GerenciadorQuiosque.getInstancia().finalizarPedidoAtual();

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sucesso!");
        alert.setHeaderText("Pedido Confirmado");
        alert.setContentText("Seu pedido #" + numeroPedido + " foi enviado para a cozinha!\nAguarde o chamado.");
        alert.showAndWait();

        App.setRoot("MenuInicial");
    }
    
    @FXML private void voltar() throws IOException { 
        App.setRoot("Lanches"); 
    }
}