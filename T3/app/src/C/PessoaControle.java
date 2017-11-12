package C;
import Dao.PessoaDAO;
import M.Menu;
import M.Pessoa;
import V.Utilitarios.MenuView;
import V.View;
import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author neimarmoises
 */
public class PessoaControle implements Serializable{
    Pessoa pessoa = new  Pessoa();
     static Scanner var = new Scanner(System.in);
     
    public void parametrizaPessoa(boolean ativaCampo){
        if (ativaCampo == true){
            pessoa.setCodPessoa(View.digitaNumero("Id"));
        }else{
            pessoa.setCodPessoa(0);
        }
        System.out.println("Nome");
        pessoa.setNome(var.nextLine());
        System.out.println("\nNome cidade");        
        pessoa.setCidade(var.nextLine());
        pessoa.setCodTipoPessoa(View.digitaNumero("Tipo pessoa"));
        pessoa.setCodProfissao(View.digitaNumero("Profissão"));
        pessoa.setCpf(View.digitaString("Cpf"));
        pessoa.setEmail(View.digitaString("E-mail"));
        pessoa.setAtiva(View.digitaString("Ativa"));     
    }
    
        
    public void recarregaMenu(boolean exibeMenuPrincipal) throws Exception{               
        new MenuView().menuClientes();
        new Controlador().selecionaMenu(exibeMenuPrincipal);
        
        if (exibeMenuPrincipal == false){
            executaPessoa();
        }
    }
    
   
    public void executaPessoa() throws Exception{       
       
        switch (Menu.getCod()) {
            case 1:
                parametrizaPessoa(false);
                new PessoaDAO().inserir(pessoa);
                recarregaMenu(false);
                break;
            
            case 2:
                parametrizaPessoa(true);
                new PessoaDAO().atualiza(pessoa);
                recarregaMenu(false);
                break;
 
            case 3:
                pessoa.setCodPessoa(View.digitaNumero("Id"));
                new PessoaDAO().exclui(pessoa);
                recarregaMenu(false);
                break;
            
            case 4:                
                new PessoaDAO().imprime();  
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
