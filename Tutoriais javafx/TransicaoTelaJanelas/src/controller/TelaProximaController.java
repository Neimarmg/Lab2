/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class TelaProximaController implements Initializable {
    @FXML
    private AnchorPane painelTelaProxima;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    private void tratarBotaoVoltar(ActionEvent event) throws IOException {
        System.out.println("Voltar");
        Stage stage = (Stage) painelTelaProxima.getScene().getWindow();
        stage.close();
    }
}
