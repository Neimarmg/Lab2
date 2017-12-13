package Controller;

import Dao.ProdutosDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Produtos;
import view.View;

/**
 * FXML Controller class
 *
 * @author Administrador
 */
public class CadastroDeProdutosController implements Initializable {
    private AnchorPane form_TelaHome;
    Produtos produtos = new Produtos();
    
    @FXML
    private CheckBox ativo;
    @FXML
    private TextField descProduto;
    @FXML
    private TextField idProduto;
    @FXML
    private TextField precoVenda;
    @FXML
    private TextField valorNotação;
 
    @FXML
    private ComboBox<String> idMarca;
    @FXML
    private ComboBox<String> idNotacao;
    
    
    
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

    public void parametrizaProduto()throws Exception {
       
        produtos.setCodProduto(0);
        produtos.setDescProruto(descProduto.getText());
        produtos.setCodMarca(idMarca.getVisibleRowCount());
        produtos.setValorNotacao(Float.valueOf(valorNotação.getText()));
        produtos.setCodNotacao(idNotacao.getVisibleRowCount());
        produtos.setPreco(Float.valueOf(precoVenda.getText()));

    }
    
    @FXML
    public void btnInsereNovo() throws IOException, Exception{
        parametrizaProduto();
        new ProdutosDAO().inserir(produtos);
        
        
    }
            
    
}
