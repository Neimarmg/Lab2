package C;
/* Pesquisar */

import M.Menu;
import V.Utilitarios.MenuView;
import V.View;
import java.util.InputMismatchException;

/**
 * @author neimarmoises
 */
public class Controlador {
    static int cod = 0;
    private MenuView mv = new MenuView();
 

    public void selecionaMenu(boolean exibeMenuPrincipal) throws Exception{
       if (exibeMenuPrincipal == true) {           
           mv.menuPrincipal();
           Menu.setCod(View.digitaNumero("\nCOMANDO"));
           View.msgl();
       }else{ 
           Menu.setCod(View.digitaNumero("\nCOMANDO"));
           View.msgl();
       }   
       
     }
 
    
    public void carregaApp() throws Exception{       
        try {  
           
            switch (Menu.getCod()) {
                case 1:
                    mv.carregaMenu();
                    selecionaMenu(false);
                    new ProdutoControle().executaProduto();
                    break;

                case 2:
                    mv.carregaMenu();
                    selecionaMenu(false);
                    new PessoaControle().executaPessoa();
                    break;

                case 3:
                    
                    mv.carregaMenu();
                    selecionaMenu(false);
                    
                    break;
                    
                case 4:
                    View.objetoNaoImplementado();
                    break;
 
                case 5:
                    View.objetoNaoImplementado();
                    break;
                    
                case 6:
                    View.sair();
                    break;

                default:
                    View.opcaoInvalida();
                    selecionaMenu(true);
                    carregaApp();
                    break;
                } 
         
        }catch (InputMismatchException e) {
            View.msgcr("\nErr: Você digitou textos no comando menu!");         
            selecionaMenu(true);
            carregaApp();
        }
           
    } 
}