package com.trabalhoprog1;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.IOException;

public class CozinhaController {

    @FXML private TextArea txtPedidos;
    @FXML private TextField txtIdPedido;

    @FXML
    public void initialize() {
        atualizarLista();
    }

    @FXML
    public void atualizarLista() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== PEDIDOS EM ANDAMENTO ===\n\n");
        
        boolean temPedidoPendente = false;

        for (Pedido p : GerenciadorQuiosque.getInstancia().getHistoricoVendas()) {
            // === MUDANÇA AQUI: SÓ MOSTRA SE NÃO TIVER ENTREGUE ===
            if (p.getStatus() != StatusPedido.ENTREGUE) {
                
                temPedidoPendente = true;
                sb.append(String.format("PEDIDO #%d  [%s]\n", p.getNumeroAtendimento(), p.getStatus()));
                
                for (ItemCardapio item : p.getItens()) {
                    sb.append("   - ").append(item.getNome()).append("\n");
                }
                sb.append("-----------------------------\n");
            }
        }
        
        if (!temPedidoPendente) {
            sb.append("A fila está vazia! Todos os pedidos foram entregues.");
        }

        txtPedidos.setText(sb.toString());
    }

    private void mudarStatus(StatusPedido novoStatus) {
        try {
            int idAlvo = Integer.parseInt(txtIdPedido.getText());
            boolean encontrou = false;

            for (Pedido p : GerenciadorQuiosque.getInstancia().getHistoricoVendas()) {
                if (p.getNumeroAtendimento() == idAlvo) {
                    p.setStatus(novoStatus);
                    encontrou = true;
                    break;
                }
            }

            if (encontrou) {
                atualizarLista(); 
                txtIdPedido.clear();
            } else {
                mostrarAlerta("Erro", "Pedido " + idAlvo + " não encontrado.");
            }

        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "Digite um número válido no campo Nº do Pedido.");
        }
    }

    @FXML private void marcarEmPreparo() { mudarStatus(StatusPedido.EM_PREPARO); }
    @FXML private void marcarPronto() { mudarStatus(StatusPedido.PRONTO); }
    
    @FXML private void marcarEntregue() { 
        mudarStatus(StatusPedido.ENTREGUE); 
    }

    private void mostrarAlerta(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @FXML
    private void voltar() throws IOException {
        App.setRoot("MenuInicial");
    }
}