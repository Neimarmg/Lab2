/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.Produtos;

/**
 * FXML Controller class
 *
 * @author neimarmoises
 */
public class TelaHomeController implements Initializable {

    @FXML
    private Button button;
    @FXML
    private Label label1;
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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
 
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        Produtos produtos = new Produtos();
        produtos.setNomeMarca("Proda");
        label1.setText(produtos.getNomeMarca());
    }


    
}
