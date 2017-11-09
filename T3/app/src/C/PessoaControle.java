package C;
import Dao.pessoaDAO;
import M.Menu;
import M.Pessoa;
import V.Utilitarios.MenuView;
import V.View;
import java.io.Serializable;

/**
 *
 * @author neimarmoises
 */
public class PessoaControle implements Serializable{
    Pessoa pessoa = new  Pessoa();
    
    public void parametrizaPessoa(boolean ativaCampo){
        if (ativaCampo == true){
            pessoa.setCodPessoa(View.digitaNumero("Id"));
        }else{
            pessoa.setCodPessoa(0);
        }        
        pessoa.setNome(View.digitaString("Nome"));
        pessoa.setCidade(View.digitaString("Cidade"));
        pessoa.setCodTipoPessoa(View.digitaNumero("Tipo pessoa"));
        pessoa.setCodProfissao(View.digitaNumero("Profissão"));
        pessoa.setCpf(View.digitaString("Cpf"));
        pessoa.setEmail(View.digitaString("E-mail"));
        pessoa.setAtiva(View.digitaString("Ativa"));     
    }
    
        
   public void recarregaMenu(boolean exibeMenuPrincipal) throws Exception{               
        
        if(exibeMenuPrincipal == true) {            
            executaPessoa();            
        }else{
            Menu.setCod(0);
            new Controlador().selecionaMenu(exibeMenuPrincipal);
            new Controlador().carregaApp();
        }
    }
    
    public void executaPessoa() throws Exception{       
       
        switch (Menu.getCod()) {
            case 1:
                parametrizaPessoa(false);
                new pessoaDAO().inserir(pessoa);   
                new MenuView().menuClientes();
                recarregaMenu(false);
                
                break;
            
            case 2:
                parametrizaPessoa(true);
                new pessoaDAO().atualiza(pessoa);
                new MenuView().menuClientes();
                recarregaMenu(false);
                break;
 
            case 3:
                pessoa.setCodPessoa(View.digitaNumero("Id"));
                new pessoaDAO().exclui(pessoa);
                new MenuView().menuClientes();
                recarregaMenu(false);
                break;
            
            case 4:                
                new pessoaDAO().imprime();  
                new MenuView().menuClientes();
                recarregaMenu(false );
                break;
                
            case 5:
                new MenuView().menuClientes();                
                recarregaMenu(true);
                break;
            
            case 6:
                View.sair();
                break;
                
            default:
                View.opcaoInvalida();       
                //recarregaMenu();
                break;
            } 
    }
}
