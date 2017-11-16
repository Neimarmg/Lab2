package C;
import Dao.ProdutosDAO;
import M.Menu;
import M.Produtos;
import V.Utilitarios.MenuView;
import V.View;
import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author neimarmoises
 */
public class ProdutoControle implements Serializable{
    Produtos produtos = new Produtos();
    static Scanner var = new Scanner(System.in);
    
    public void parametrizaProduto(boolean ativaCampo)throws Exception {
        
        if (ativaCampo == true){
            produtos.setCodProduto(View.digitaNumero("Id"));
        }else{
            produtos.setCodProduto(0);
        }
        System.err.println("Descrição produto");
        produtos.setDescProruto(var.nextLine());
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
                new ProdutosDAO().inserir(produtos);
                recarregaMenu(false);
                break;
            
            case 2:
                parametrizaProduto(true);
                new ProdutosDAO().atualiza(produtos);
                recarregaMenu(false);
                break;
 
            case 3:
                produtos.setCodProduto(View.digitaNumero("Id"));
                new ProdutosDAO().exclui(produtos);
                recarregaMenu(false);
                break;
            
            case 4:                               
                new ProdutosDAO().imprime(View.digitaString("Id"));  
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
