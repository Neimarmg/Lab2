package C;
import Dao.ProdutosDAO;
import Dao.VendasPedidoDAO;
import M.Menu;
import M.VendaPedido;
import V.Utilitarios.MenuView;
import V.View;
import java.io.Serializable;
import java.sql.Date;


/**
 *
 * @author neimarmoises
 */
public class VendaPedidoControle implements Serializable{
    VendaPedido vendaPedido = new VendaPedido();
    
    public void parametrizaProduto(boolean ativaCampo)throws Exception {
        
        if (ativaCampo == true){
            vendaPedido.setCodVendaPedido(View.digitaNumero("Id"));
        }else{
            vendaPedido.setCodVendaPedido(0);
        }
        vendaPedido.setCodCliente(View.digitaNumero("Cod Cliente"));
        //vendaPedido.setDataVenda(Date.valueOf(View.digitaString("Data pedido")));


    }
    
        
    public void recarregaMenu(boolean exibeMenuPrincipal) throws Exception{               
        new MenuView().menuVendas();
        new Controlador().selecionaMenu(exibeMenuPrincipal);
        
        if (exibeMenuPrincipal == false){
            executaVendaProduto();
        }
    }
    
   
    public void executaVendaProduto() throws Exception{       
       
        switch (Menu.getCod()) {
            case 1:
               
                parametrizaProduto(false);
                new VendasPedidoDAO().inserir(vendaPedido);
                recarregaMenu(false);
                break;
            
            case 2:
                parametrizaProduto(true);
                new VendasPedidoDAO().atualiza(vendaPedido);
                recarregaMenu(false);
                break;
 
            case 3:
                vendaPedido.setCodVendaPedido(View.digitaNumero("Id"));
                new VendasPedidoDAO().exclui(vendaPedido);
                recarregaMenu(false);
                break;
            
            case 4:                
                new ProdutosDAO().imprime();  
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
