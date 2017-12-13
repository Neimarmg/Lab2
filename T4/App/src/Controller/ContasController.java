/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author neimarmoises
 */
public class ContasController implements Initializable {

    @FXML
    private TextField ValorInicial;
    @FXML
    private TextField valorBaixado;
    @FXML
    private ComboBox<?> idConta;
    @FXML
    private ComboBox<?> idTipoMovimento;
    @FXML
    private DatePicker dataBaixa;
    @FXML
    private DatePicker dataVencimento;
    @FXML
    private Button btnNovo;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnAtualizar;
    @FXML
    private Button btnFechar;
    @FXML
    private Button btnImprimir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
