package C;
/* Pesquisar */
import M.Menu;
import M.Pessoa;
import V.Utilitarios.MenuView;
import V.Utilitarios.View;

/**
 * @author neimarmoises
 */
public class Controlador {
    Menu menu = new Menu();
    MenuView mv  = new MenuView();

    public void selecionaMenu(boolean exibeMenuPrincipal){
       if (exibeMenuPrincipal == true) {           
           mv.menuPrincipal();
           menu.setCod(View.digitaNumero(""));
       }else{
           menu.setCod(View.digitaNumero(""));
           mv.carregaMenu();
       }        
                
    }
      
    
    public void carregaApp(){    
        
        selecionaMenu(true);        
        switch (menu.getCod()) {

            case 1:
                mv.menuCadastroPacientes();
                selecionaMenu(false);                
                break;
            
            case 2:
                mv.menuCadastroMedicamentos();
                selecionaMenu(false);                
                break;
                
            case 3:
                mv.menuAgendamentosConsultas();
                selecionaMenu(false);                
                break;
                
            default:
                View.opcaoInvalida();
                carregaApp();
                break;
            }
    }       
    

}
