package C;
import Dao.produtosDAO;
import M.Menu;
import M.Produtos;
import V.Utilitarios.MenuView;
import V.View;
import java.io.Serializable;

/**
 *
 * @author neimarmoises
 */
public class ProdutoControle implements Serializable{
    Produtos produtos = new Produtos();
    
    public void parametrizaProduto(boolean ativaCampo){
        if (ativaCampo == true){
            produtos.setCodProduto(View.digitaNumero("Id"));
        }else{
            produtos.setCodProduto(0);
        }        
        produtos.setDescProruto(View.digitaString("Descrição produto"));
        produtos.setCodMarca(View.digitaNumero("Cod Marca"));
        produtos.setValorNotacao(View.digitaNumero("Valor notação"));
        produtos.setCodNotacao(View.digitaNumero("Notação"));
        produtos.setPreco(View.digitaFloat("Preço"));
          
    }
    
        
    public void recarregaMenu(boolean exibeMenuPrincipal) throws Exception{               
        new MenuView().menuProdutos();
        new Controlador().selecionaMenu(exibeMenuPrincipal);
        
        if (exibeMenuPrincipal == false){
            executaProduto();
        }
    }
    
   
    public void executaProduto() throws Exception{       
       
        switch (Menu.getCod()) {
            case 1:
               
                parametrizaProduto(false);
                new produtosDAO().inserir(produtos);
                recarregaMenu(false);
                break;
            
            case 2:
                parametrizaProduto(true);
                new produtosDAO().atualiza(produtos);
                recarregaMenu(false);
                break;
 
            case 3:
                produtos.setCodProduto(View.digitaNumero("Id"));
                new produtosDAO().exclui(produtos);
                recarregaMenu(false);
                break;
            
            case 4:                
                new produtosDAO().imprime();  
                recarregaMenu(false); 
                break;
                
            case 5:
                recarregaMenu(true);
                break;
            
            case 6:
                View.sair();
                break;
                
            default:
                View.opcaoInvalida();       
                recarregaMenu(false);
                break;
            } 
    }
}
