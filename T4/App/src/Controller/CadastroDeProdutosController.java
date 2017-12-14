package Controller;

import Dao.Jdbc.ConnectionFactory;
import Dao.ProdutosDAO;
import Dao.UtilitariosDAO;
import com.sun.javaws.Globals;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Produtos;
import model.Utilitarios;
import model.negocio.Globais;
import view.View;

/**
 * FXML Controller class
 *
 * @author Administrador
 */
public class CadastroDeProdutosController implements Initializable {
    Produtos produtos = new Produtos();
    Utilitarios utilitarios = new Utilitarios();
    UtilitariosDAO utilitariosDAO =  new UtilitariosDAO();
      
    @FXML 
    private List<Utilitarios> listaMarcas = new ArrayList<Utilitarios>();
    @FXML 
    private ObservableList<Utilitarios> ObservableListMarcas;
    @FXML 
    private List<Utilitarios> listaNotacao = new ArrayList<Utilitarios>();
    @FXML 
    private ObservableList<Utilitarios> ObservableListNotacao;
    
    @FXML
    private CheckBox ativo;
    @FXML
    private TextField descProduto;
    @FXML
    private TextField idProduto; 
    @FXML
    private TextField precoVenda;
    @FXML
    private TextField vNotacao; 
    @FXML
    private ComboBox<Utilitarios> idMarca;
    @FXML
    private ComboBox<Utilitarios> idNotacao;    
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
                    listaMarcas.add(utilitarios); 
                    ObservableListMarcas = FXCollections.observableArrayList(listaMarcas);
               
                }else if(Globais.getContador()== 2){
                    listaNotacao.add(utilitarios);
                    ObservableListNotacao = FXCollections.observableArrayList(listaNotacao);
                }
            }
            ConnectionFactory.fechaConexao(utilitariosDAO.getCon(), prepara, true );
        } catch(SQLException e){ 
            e.printStackTrace();
        }
        
        if(Globais.getContador()== 1){               
            return listaMarcas;

        }else if(Globais.getContador()== 2){
            return listaNotacao;

        }else{
            return  null;
        } 
    } 
    
    public void carregaComboBoxs(){
        Globais.getContador(true, true);
        listarTodos("14","0");        
        idMarca.setItems(ObservableListMarcas);           
        Globais.getContador(true, false);
        listarTodos("13", "0");
        idNotacao.setItems(ObservableListNotacao);        
        Globais.getContador(false , true);
    }
        
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaComboBoxs();
    }                 
     
    @FXML
    public void parametrizaProduto()throws Exception {
        //imprime("14", "0");
        produtos.setCodProduto(0);
        produtos.setDescProruto(descProduto.getText());
        produtos.setCodMarca(Integer.parseInt(String.valueOf(idMarca.getValue())));
        produtos.setValorNotacao(Float.parseFloat(vNotacao.getText()));
        produtos.setCodNotacao(Integer.parseInt(String.valueOf(idNotacao.getValue())));
        produtos.setPreco(Float.parseFloat(precoVenda.getText()));
       
        

    }
    
    @FXML
    public void btnInsereNovo() throws IOException, Exception{
        parametrizaProduto();
        new ProdutosDAO().inserir(produtos);
        
        
    }
           
    @FXML
    public  void btnFechar(){
         System.exit(1);
    }
    
}