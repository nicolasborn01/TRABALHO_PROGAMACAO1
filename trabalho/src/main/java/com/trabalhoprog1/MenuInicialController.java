package com.trabalhoprog1;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import java.io.IOException;
import java.util.Optional;

public class MenuInicialController {

    @FXML
    private void iniciarTotem() throws IOException {
        App.setRoot("Lanches"); 
    }

    @FXML
    private void acessarCozinha() throws IOException {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Acesso Restrito");
        dialog.setHeaderText("Área Exclusiva de Funcionários");
        dialog.setContentText("Por favor, digite a senha administrativa:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            String senhaDigitada = result.get();

            if (senhaDigitada.equals("admin123")) { 
                App.setRoot("Cozinha");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Acesso Negado");
                alert.setContentText("Senha incorreta!");
                alert.showAndWait();
            }
        }
    }
}