/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.Jdbc.ConnectionFactory;
import Dao.PessoaDAO;
import Dao.UtilitariosDAO;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import model.Utilitarios;
import model.negocio.Globais;

/**
 * FXML Controller class
 *
 * @author 181100053
 */
public class CadastroDeClientesController implements Initializable {
    Pessoa pessoa = new Pessoa();
    PessoaDAO pessoaDAO = new PessoaDAO();
    UtilitariosDAO utilitariosDAO = new UtilitariosDAO();
    
    
    @FXML 
    private List<Utilitarios> listaTipoPessoa = new ArrayList<Utilitarios>();
    @FXML 
    private ObservableList<Utilitarios> ObservableListTipoPessoa;
    @FXML 
    private List<Utilitarios> listaPrifissao = new ArrayList<Utilitarios>();
    @FXML 
    private ObservableList<Utilitarios> ObservableListPrifissao;
    
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
    private TextField cpfCliente;
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
    private ComboBox<Utilitarios> idTipo;
    @FXML
    private ComboBox<Utilitarios> idProfissao;
    @FXML
    private Label profissaoCliente;
    @FXML
    private TextField nomeCidade;
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
        carregaComboBoxs();
    }    
    @FXML
    public List<Utilitarios> listarTodos(String idUtilitario, String idTipoUtilitarios){ //procurar todos nao tem parametr00o
        ConnectionFactory.setSql("call cUtilitarios ("+idUtilitario+","+idTipoUtilitarios+")");
        try{            
            PreparedStatement prepara = utilitariosDAO.getCon().prepareStatement(ConnectionFactory.getSql());
            ResultSet resultado = prepara.executeQuery(); //retorna resultado da consulta da query -> tipo ResultSet
            
            while(resultado.next()){ //buscando valor das colunas, registro por registro
                
                Utilitarios utilitarios  = new Utilitarios();                
                    utilitarios.setCodUtilitario(resultado.getInt("CodUtilitario"));
                    utilitarios.setUtilitario(resultado.getString("utilitario"));
                    //utilitarios.setCodTipoUtilirario(resultado.getInt("codTipoUtilirario"));                  
                
                if(Globais.getContador()== 1){    
                    listaTipoPessoa.add(utilitarios); 
                    ObservableListTipoPessoa = FXCollections.observableArrayList(listaTipoPessoa);
               
                }else if(Globais.getContador()== 2){
                    listaPrifissao.add(utilitarios);
                    ObservableListPrifissao = FXCollections.observableArrayList(listaPrifissao);
                }
            }
            ConnectionFactory.fechaConexao(utilitariosDAO.getCon(), prepara, true );
        } catch(SQLException e){ 
            e.printStackTrace();
        }
        
        if(Globais.getContador()== 1){               
            return listaTipoPessoa;

        }else if(Globais.getContador()== 2){
            return listaTipoPessoa;

        }else{
            return  null;
        } 
    } 
    
    public void carregaComboBoxs(){
        Globais.getContador(true, true);
        listarTodos("3","0");        
        idTipo.setItems(ObservableListTipoPessoa);           
        Globais.getContador(true, false);
        listarTodos("6", "0");
        idProfissao.setItems(ObservableListPrifissao);        
        Globais.getContador(false , true);
    }
    
    
    @FXML
    private void btnInsereNovo() {
        pessoa.setNome(nome.getText());        
        pessoa.setCidade(nomeCidade.getText());
        pessoa.setCodTipoPessoa(Integer.parseInt(String.valueOf(idTipo.getValue())));
        pessoa.setCodProfissao(Integer.parseInt(String.valueOf(idProfissao.getValue())));
        pessoa.setCpf(cpfCliente.getText());
        pessoa.setEmail(email.getText());
        pessoa.setAtiva("S");
        new PessoaDAO().inserir(pessoa);
        
    }

    @FXML
    private void btnFechar(ActionEvent event) {
        System.exit(1);
    }
    
}
