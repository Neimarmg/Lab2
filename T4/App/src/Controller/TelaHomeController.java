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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author neimarmoises
 */
public class TelaHomeController implements Initializable {
    @FXML
    private AnchorPane form_cadastrfoProdutos;
    private AnchorPane telaHome;

 
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
    private ChoiceBox<String> menuBtnCadastro;
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
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LocalDate di = dataInicio.getValue();
        dataInicio.setValue(di.now());
        
        LocalDate df = DataFim.getValue();
        DataFim.setValue(df.now());        
        menuBtnCadastro.setItems(FXCollections.observableArrayList("1","2"));
    }    

    @FXML
    private void abreCadastros(ActionEvent event) {
        
        try {
           
            if (menuBtnCadastro.getValue() == "1"){ 
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(this.getClass().getResource("/view/form_CadastroDeProdutos.fxml"));
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);           
                stage.showAndWait();
                
                
            }else if (menuBtnCadastro.getValue() == "2"){
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(this.getClass().getResource("/view/form_CadastroDeClientes.fxml"));
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);           
                stage.showAndWait();
            }
            menuBtnCadastro.setFocusTraversable(true);
        } catch (IOException ex) {
            Logger.getLogger(TelaHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception e){
            e.getMessage();
            view.View.msgc("Não e possivel abrir");
                   
        }
    }

} 
