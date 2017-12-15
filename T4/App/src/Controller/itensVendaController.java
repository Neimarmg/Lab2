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
import Dao.VendasItensDAO;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Pessoa;
import model.Produtos;
import model.VendaIntens;
import model.VendaPedido;
import view.View;

/**
 * FXML Controller class
 *
 * @author neimarmoises
 */
public class itensVendaController implements Initializable {
    @FXML
    double valorProd;
    @FXML
    VendaIntens vendaIntens = new VendaIntens();
    @FXML
    ProdutosDAO   produtosDAO = new ProdutosDAO();

    @FXML  
    private List<Produtos> listaProduto = new ArrayList<Produtos>();
    @FXML 
    private ObservableList<Produtos> ObservableListprodutos;
    
    
    
    @FXML
    private AnchorPane form_itensVenda;
    @FXML
    private TextField idVendaPedido;
    @FXML
    private TextField preco;
    @FXML
    private ComboBox<Produtos> idProduto;
    @FXML
    private Button btnNovo;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnSalvar;
    @FXML
    private TextField qt;
    @FXML
    private Label acescimo;
    @FXML
    private TextField desconto;
    @FXML
    private TextField acrescimo;
    @FXML
    private TextField valorBruto;
    @FXML
    private TextField valorTotal;
    @FXML
    private TextField totalLiquido;
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

     public  void totalizaPedido(String idProduto) throws SQLException{
        ConnectionFactory.setSql("SELECT sum(qtVenda)as qt, sum(totalValorBruto)AS vBruto, sum(valorTotal)as vTotal, sum(totalValorLiquido) as vLiq FROM `vendaintens` WHERE codVendaPedido in("+idProduto+")");
        try{            
            PreparedStatement prepara = produtosDAO.getCon().prepareStatement(ConnectionFactory.getSql());
            ResultSet resultado = prepara.executeQuery(); //retorna resultado da consulta da query -> tipo ResultSet
            while(resultado.next()){ //buscando valor das colunas, registro por registro
                
                vendaIntens.setQtVenda(resultado.getInt("qt"));
                vendaIntens.setValorBruto(resultado.getFloat("vTotal"));
                vendaIntens.setValorTotal(resultado.getFloat("vTotal"));
                vendaIntens.setValorLiquido(resultado.getFloat("vLiq"));                    
            }
            ConnectionFactory.fechaConexao(produtosDAO.getCon(), prepara, true );
        
        } catch(SQLException e){ 
            e.printStackTrace();
        }
        
        View.msgcr("Tolta geral da compra\n"
               +"\nTotal de itens: "+vendaIntens.getQtVenda()
               +"\nSubtotal bruto: "+vendaIntens.getValorBruto()
               +"\nSubtotal: "+vendaIntens.getValorTotal()
               +"\nSubtotal liquído: "+vendaIntens.getValorLiquido()
        );
            
    }
    
    
    @FXML
    public List<Produtos> listarTodosProdutos(String idProdutos){ 
        ConnectionFactory.setSql("call cProdutos ("+idProdutos+")");
        try{            
            PreparedStatement prepara = produtosDAO.getCon().prepareStatement(ConnectionFactory.getSql());
            ResultSet resultado = prepara.executeQuery();
            
            while(resultado.next()){                
                Produtos produtos = new Produtos();
                produtos.setCodProduto(resultado.getInt("codProduto"));
                produtos.setDescProruto(resultado.getString("descProduto"));
                produtos.setPreco(resultado.getInt("preco"));
                
                listaProduto.add(produtos); 
                ObservableListprodutos = FXCollections.observableArrayList(listaProduto); 
                valorProd = produtos.getPreco();
            }
            ConnectionFactory.fechaConexao(produtosDAO.getCon(), prepara, true );
        } catch(SQLException e){ 
            e.printStackTrace();
        }        
        return  listaProduto;
        } 

    
    public void imprime(String idPessoa){
        listarTodosProdutos(idPessoa);
        
        for (Produtos produtos:listaProduto)
            View.msg(
                   "\n"+produtos.getCodProduto()
                  +","+produtos.getDescProruto()
                  +","+produtos.getPreco());   
        View.msgl();
    }
    
    @FXML
    public void carregaComboBoxs(){
        imprime("0");
        idProduto.setItems(ObservableListprodutos);
    }
    
    @FXML
    public void parametrizaPedidoVenda()throws Exception {
        
        vendaIntens.setCodVendaPedido(BdUtil.getPk());        
        vendaIntens.setCodProduto(Integer.parseInt(String.valueOf(idProduto.getValue())));  
        listarTodosProdutos(String.valueOf(BdUtil.getPk()));
        preco.setText(String.valueOf(valorProd));
        vendaIntens.setPreco(Float.parseFloat(preco.getText()));
        vendaIntens.setDesconto(Float.parseFloat(desconto.getText()));
        vendaIntens.setAcrescimo(Double.valueOf(acrescimo.getText()));
        vendaIntens.setQtVenda(Integer.parseInt(qt.getText()));
        vendaIntens.calculaVenda();
        
        valorBruto.setText(String.valueOf(vendaIntens.getValorBruto()));
        valorTotal.setText(String.valueOf(vendaIntens.getValorTotal()));
        totalLiquido.setText(String.valueOf(vendaIntens.getValorLiquido()));
    }
    
    
    @FXML
    private void btnInsereNovo() throws Exception {
        parametrizaPedidoVenda();
        new VendasItensDAO().inserir(vendaIntens);
        
    }
    
   
}
