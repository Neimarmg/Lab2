/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.Jdbc.ConnectionFactory;
import Dao.UtilitariosDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import model.Menus;
import model.Utilitarios;
import model.negocio.Globais;


/**
 * FXML Controller class
 *
 * @author neimarmoises
 */
public class TelaHomeController implements Initializable {
    @FXML
    UtilitariosDAO utilitariosDAO = new UtilitariosDAO();
    @FXML
    private List<Utilitarios> listaDeMenusCadastros = new ArrayList<Utilitarios>();
    @FXML
    private ObservableList<Utilitarios> ObservableListMenuCadastros;
    @FXML
    private List<Utilitarios> listaDeMenusOpercaoes = new ArrayList<Utilitarios>();
    @FXML
    private ObservableList<Utilitarios> ObservableListMenuOperacoes;
  
 
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
    private ChoiceBox<Utilitarios> menuBtnCadastro;
    @FXML
    private Button btnOpercoes;
    @FXML
    private ChoiceBox<Utilitarios> menuBtnOpercoes;
    @FXML
    private Button btnRelatorios;
    @FXML
    private ChoiceBox<Utilitarios> menuBtnRelatorios;
    
    
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
                    listaDeMenusCadastros.add(utilitarios); 
                    ObservableListMenuCadastros = FXCollections.observableArrayList(listaDeMenusCadastros);
           
                }else if(Globais.getContador()== 2){
                    
                    listaDeMenusOpercaoes.add(utilitarios); 
                    ObservableListMenuOperacoes = FXCollections.observableArrayList(listaDeMenusCadastros);
                }
            }
            ConnectionFactory.fechaConexao(utilitariosDAO.getCon(), prepara, true );
        } catch(SQLException e){ 
            e.printStackTrace();
        }
        
        if(Globais.getContador()== 1){               
            return listaDeMenusCadastros;

        }else if(Globais.getContador()== 2){
            return listaDeMenusOpercaoes;

        }else{
            return null;
        } 
    } 
    
        
    public void carregaComboBoxs(){
        Globais.getContador(true, true);
        listarTodos("18","0");        
        menuBtnCadastro.setItems(ObservableListMenuCadastros);           
        Globais.getContador(true, false);
        listarTodos("19", "0");
        menuBtnOpercoes.setItems(ObservableListMenuOperacoes);
        Globais.getContador(false , true);
    }
    
    
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
        
        carregaComboBoxs();
    }    

   @FXML
    private void abreCadastros() {
        
        try {           
            if (Integer.parseInt(String.valueOf(menuBtnCadastro.getValue())) == 26){ 
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(this.getClass().getResource("/view/form_CadastroDeProdutos.fxml"));
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);           
                stage.showAndWait();                
                
            }else if (Integer.parseInt(String.valueOf(menuBtnCadastro.getValue())) == 25){
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(this.getClass().getResource("/view/form_CadastroDeClientes.fxml"));
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);           
                stage.showAndWait();
                
            }else if (Integer.parseInt(String.valueOf(menuBtnCadastro.getValue())) == 29){
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(this.getClass().getResource("/view/form_pedidoVenda.fxml"));
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


