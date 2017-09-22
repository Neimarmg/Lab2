package C;

import M.Menu;
import M.Pessoa;
import V.Utilitarios.MenuView;
import V.View;
/**
 *
 * @author 181100053
 */
public class CadastroPessoa extends Pessoa{
   
    public void CriaPessoa(){
        setCodPessoa(Controlador.getCodAuto());
        setNome(View.digitaString("Nome"));  
        setCodTipo(View.digitaString("CodTipo"));
        setCpf(View.digitaString("CPF"));
        View.msgl();
    }
    
    
    public void imprimir(){
        if(Integer.parseInt(getCodPessoa()) > 0  ){
            View.msgcr(
                     "\n Id pessoa: " +getCodPessoa()
                    +"\n Nome     : " +getNome()
                    +"\n cod tipo : " +getCodTipo()
                    +"\n cod CPF  : " +getCpf()
            );
        }else{
            View.msgr("Não foram encontrados clientes cadastrados!");
        }
    }
    
    
    public void recarregaMenu() throws Exception{
       new MenuView().menuClientes();
       new Controlador().selecionaMenu(false);
       executaPessoa();            
    }
    

    public void executaPessoa() throws Exception{       
       
        switch (Menu.getCod()) {
            case 1:
                CriaPessoa();
                recarregaMenu();
                break;
            
            case 2:
                new Contas().executaConta();
                recarregaMenu();
                break;
 
            case 3:
                imprimir();
                recarregaMenu();
                break;
            
            case 4:
                View.sair();
                break;
                
            default:
                View.opcaoInvalida();       
                //recarregaMenu();
                break;
            } 
    }
    
               
}
