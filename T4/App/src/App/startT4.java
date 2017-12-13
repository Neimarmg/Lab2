/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import Controller.CadastroDeProdutosController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author neimarmoises
 */
public class startT4 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/form_CadastroDeProdutos.fxml"));        
        Scene scene = new Scene(root);        
        stage.setScene(scene);
        stage.setTitle("Home - Sistema Monetização ");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception {
        launch(args);
        new CadastroDeProdutosController().btnInsereNovo();
        
    }
    
}
