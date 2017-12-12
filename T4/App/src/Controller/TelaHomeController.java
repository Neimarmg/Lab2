/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author neimarmoises
 */
public class TelaHomeController implements Initializable {
    
    @FXML
    private Button button;
    @FXML
    private DatePicker DataFim;
    @FXML
    private DatePicker dataInicio;
    @FXML
    private ChoiceBox<?> idConta;
    @FXML
    private ChoiceBox<?> idProduto;
    @FXML
    private ChoiceBox<?> idCliente;
    @FXML
    private Button btnCadastro;
    @FXML
    private ChoiceBox<?> menuBtnCadastro;
    @FXML
    private Button btnOpercoes;
    @FXML
    private ChoiceBox<?> menuBtnOpercoes;
    @FXML
    private Button btnRelatorios;
    @FXML
    private ChoiceBox<?> menuBtnRelatorios;

    
    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public  void initialize(URL url, ResourceBundle rb) {
        LocalDate di = dataInicio.getValue();
        dataInicio.setValue(di.now());
        
        LocalDate df = DataFim.getValue();
        DataFim.setValue(df.now());        
 
    }    
    
    

    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/form_ContasMonvimentos.fxml/"));
        Stage stage = new Stage();

        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        
        stage.initOwner(stage.getScene().getWindow());
        stage.showAndWait();

    }

   

    
}
