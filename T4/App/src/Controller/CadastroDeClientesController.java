/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.PessoaDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Pessoa;

/**
 * FXML Controller class
 *
 * @author neimarmoises
 */
public class CadastroDeClientesController implements Initializable {
    Pessoa pessoa = new Pessoa();
    PessoaDAO pessoaDAO =  new PessoaDAO();
    
    @FXML
    private AnchorPane form_cadastroClientes;
    @FXML
    private AnchorPane cidade;
    @FXML
    private CheckBox ativo;
    @FXML
    private TextField nome;
    @FXML
    private TextField idPessoa;
    @FXML
    private Label cpf;
    @FXML
    private Label tipo;
    @FXML
    private Label profissao;
    @FXML
    private TextField email;
    @FXML
    private Button btnNovo;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnSalvar;
    @FXML
    private ComboBox<?> idTipo;
    @FXML
    private ComboBox<?> idProfissao;
    @FXML
    private Label profissao1;
    @FXML
    private TableColumn<?, ?> idCliente;
    @FXML
    private TableColumn<?, ?> NomeCliente;
    @FXML
    private TableColumn<?, ?> tipoCliente;
    @FXML
    private TableColumn<?, ?> ProfissaoCliente;
    @FXML
    private TableColumn<?, ?> cpfCleinte;
    @FXML
    private TableColumn<?, ?> status;
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

    @FXML
    private void btnInsereNovo(ActionEvent event) {
    
         //pessoa.setNome(nome.getText());        
        //pessoa.setCidade(cidade.getText());
        //pessoa.setCodTipoPessoa(1);
        //pessoa.setCodProfissao(1);
        //pessoa.setCpf(cpf.getText());
        //pessoa.setEmail(email.getText());
        //pessoa.setAtiva("S");
    }

    @FXML
    private void btnFechar(ActionEvent event) {
        System.exit(1);
    }
    
}
