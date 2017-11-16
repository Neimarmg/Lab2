package C;
import Dao.ContaDAO;
import Dao.ProdutosDAO;
import M.Conta;
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

public class ContaControle implements Serializable{
    Conta conta = new Conta();
    static Scanner var = new Scanner(System.in);
    
    public void parametrizaProduto(boolean ativaCampo)throws Exception {
        
        if (ativaCampo == true){
            conta.setCodConta(View.digitaNumero("Id"));
        }else{
            conta.setCodConta(0);
        }
        conta.setOperacao(View.digitaNumero("Operação"));
        System.err.println("Descrição produto");
      /* produtos.setDescProruto(var.nextLine());
        produtos.setCodMarca(View.digitaNumero("Cod Marca"));
        produtos.setValorNotacao(View.digitaNumero("Valor notação"));
        produtos.setCodNotacao(View.digitaNumero("Notação"));
        produtos.setPreco(View.digitaFloat("Preço"));*/

    }
    
        
    public void recarregaMenu(boolean exibeMenuPrincipal) throws Exception{               
        new MenuView().menuOpercoes();
        new Controlador().selecionaMenu(exibeMenuPrincipal);
        
        if (exibeMenuPrincipal == false){
            //executaProduto();
        }
    }
    
   /*
    public void executaProduto() throws Exception{       
       
        switch (Menu.getCod()) {
            case 1:
               
                parametrizaProduto(false);
                new ContaDAO().inserir(produtos);
                recarregaMenu(false);
                break;
            
            case 2:
                parametrizaProduto(true);
                new ContaDAO().atualiza(produtos);
                recarregaMenu(false);
                break;
 
            case 3:
                produtos.setCodProduto(View.digitaNumero("Id"));
                new ContaDAO().exclui(produtos);
                recarregaMenu(false);
                break;
            
            case 4:                               
                new ContaDAO().imprime(View.digitaString("Id"));  
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
    }*/
}
