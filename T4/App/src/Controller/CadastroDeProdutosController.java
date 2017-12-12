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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import model.Produtos;

/**
 * FXML Controller class
 *
 * @author Administrador
 */
public class CadastroDeProdutosController implements Initializable {
    Produtos produtos = new Produtos();
    
    @FXML
    private CheckBox ativo;
    @FXML
    private TextField descProduto;
    @FXML
    private TextField idProduto;
    @FXML
    private TextField preco;
    @FXML
    private TextField valorNotação;
 
    @FXML
    private ComboBox<?> idMarca;
    @FXML
    private ComboBox<?> idNotacao;
    @FXML
    private TableColumn<?, ?> descricao;
    @FXML
    private TableColumn<?, ?> nomeMarca;
    @FXML
    private TableColumn<?, ?> notacao;
    
    @FXML
    private Button btnAtualizar;
    @FXML
    private Button btnFechar;
    @FXML
    private Button btnImprimir;
    @FXML
    private Button btnNovo;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnSalvar;
    
    
    
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    
    
    
}
