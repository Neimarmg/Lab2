package C;
/* Pesquisar */
import M.Menu;
import M.Pessoa;
import V.Utilitarios.View;

/**
 * @author neimarmoises
 */
public class MenuControle {
    Menu menu = new Menu();

    public void selecionaMenu(){
         menu.setDescMenu(View.digitaString(""));
    }

    public void carregaApp(){
        selecionaMenu();
        switch (menu.getDescMenu()) {

            case "paciente":
               new PessoaCadastro().CriaPessoa();
                
                break;
                
            default:
                View.opcaoInvalida();
                carregaApp();
                break;
            }
    }       
    

}
