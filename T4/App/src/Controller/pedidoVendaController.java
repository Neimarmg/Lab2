/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.Jdbc.BdUtil;
import Dao.Jdbc.ConnectionFactory;
import Dao.PessoaDAO;
import Dao.ProdutosDAO;
import Dao.VendasPedidoDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Conta;
import model.Pessoa;
import model.VendaPedido;
import view.View;

/**
 * FXML Controller class
 *
 * @author neimarmoises
 */
public class pedidoVendaController implements Initializable {
    @FXML
    VendaPedido  vendaPedido = new VendaPedido();
    @FXML
    PessoaDAO pessoaDAO = new PessoaDAO();
    @FXML  
    private List<Pessoa> listaClientes = new ArrayList<Pessoa>();
    @FXML 
    private ObservableList<Pessoa> ObservableListClientes;
    
    /*@FXML 
    private List<Utilitarios> listaPrifissao = new ArrayList<Utilitarios>();
    @FXML 
    private ObservableList<Utilitarios> ObservableListPrifissao;
   */
       
    
    @FXML
    private AnchorPane form_pedidoVenda;
    @FXML
    private TextField idVendaPedido;
    @FXML
    private TextField nomeCliente;
    @FXML
    private ComboBox<Pessoa> idCliente;
    @FXML
    private ComboBox<Conta> idConta;
    @FXML
    private DatePicker dataVenda;
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
    @FXML 
    private Button btnInsereNovo;
    

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaComboBoxs();
        LocalDate df = dataVenda.getValue();
        dataVenda.setValue(df.now()); 
    }  
    
    
    @FXML
    public List<Pessoa> listarTodosClientes(String idPessoa){ 
        ConnectionFactory.setSql("call cPessoa ("+idPessoa+")");
        try{            
            PreparedStatement prepara = pessoaDAO.getCon().prepareStatement(ConnectionFactory.getSql());
            ResultSet resultado = prepara.executeQuery();
            
            while(resultado.next()){                
                Pessoa pessoa  = new Pessoa();
                pessoa.setCodPessoa(resultado.getInt("codPessoa"));
                pessoa.setNome(resultado.getString("nome"));
                

                listaClientes.add(pessoa); 
                ObservableListClientes = FXCollections.observableArrayList(listaClientes);               
            }
            ConnectionFactory.fechaConexao(pessoaDAO.getCon(), prepara, true );
        } catch(SQLException e){ 
            e.printStackTrace();
        }        
        return  listaClientes;
        } 

    
    public void imprime(String idPessoa){
        listarTodosClientes(idPessoa);
        
        for (Pessoa pessoa:listaClientes)
            View.msg(
                   "\n"+pessoa.getCodPessoa()
                  +","+pessoa.getNome());   
        View.msgl();
    }
    
    @FXML
    public void carregaComboBoxs(){
        imprime("0");
        idCliente.setItems(ObservableListClientes);
    }
    
    @FXML
    public void parametrizaPedidoVenda()throws Exception {
        vendaPedido.setCodCliente(Integer.parseInt(String.valueOf(idCliente.getValue())));
        vendaPedido.setDataVenda(String.valueOf(dataVenda.getValue()));
        vendaPedido.setCodConta(1);//String.valueOf(dataVenda.getValue()));
    }
    
    
    @FXML
    public void btnInsereNovo() throws IOException, Exception{
        parametrizaPedidoVenda();
        new VendasPedidoDAO().inserir(vendaPedido);
        idVendaPedido.setText(String.valueOf(BdUtil.getPk()));
     }
   

    @FXML 
    public void btnInsereNovoItem()throws Exception{
        btnInsereNovo();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/form_itensVenda.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);           
        stage.showAndWait();  
    }
    
} 

    
  
    

