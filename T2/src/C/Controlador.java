package C;
/* Pesquisar */

import M.Menu;
import V.Utilitarios.MenuView;
import V.View;


/**
 * @author neimarmoises
 */
public class Controlador {
    static int cod = 0;
    private MenuView mv = new MenuView();
    

    public void selecionaMenu(boolean exibeMenuPrincipal){
       if (exibeMenuPrincipal == true) {           
           mv.menuPrincipal();
           Menu.setCod(View.digitaNumero(""));
           View.msgl();
       }else{           
           Menu.setCod(View.digitaNumero(""));
           View.msgl();
       }   
       
     }
 
     
    public void carregaApp() throws Exception{       
        try {  
           
            switch (Menu.getCod()) {
                case 1:
                   mv.carregaMenu();
                    selecionaMenu(false);
                    new CadastroProdutos().executaProduto();
                    break;

                case 2:
                    mv.carregaMenu();
                    selecionaMenu(false);
                    new CadastroPessoa().executaPessoa();
                    break;

                case 3:
                    mv.carregaMenu();
                    selecionaMenu(false);
                    new Vendas().executaVendas();
                    break;

                case 4:
                    View.sair();
                    break;

                default:
                    View.opcaoInvalida();
                    selecionaMenu(true);
                    carregaApp();
                    break;
                } 
         } catch (Exception e) {
            selecionaMenu(true);
            carregaApp();
        }
    }
    
    public static String getCodAuto(){
        cod = cod + 1;
        return String.valueOf(cod);
    }
       
    
}