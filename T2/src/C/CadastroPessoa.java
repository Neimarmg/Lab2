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
        View.msgcr(
                 "\n Id pessoa: " +getCodPessoa()
                +"\n Nome     : " +getNome()
                +"\n cod tipo : " +getCodTipo()
                +"\n cod CPF  : " +getCpf()
        );
    }
    
    
    public void recarregaMenu(boolean exibeMenuPrincipal) throws Exception{
        new MenuView().menuVendas();
        new Controlador().selecionaMenu(exibeMenuPrincipal);       
        
        if(exibeMenuPrincipal == false) {
            executaPessoa();
        }else{
            new Controlador().carregaApp();
        }
    }
    

    public void executaPessoa() throws Exception{       
       
        switch (Menu.getCod()) {
            case 1:
                CriaPessoa();
                recarregaMenu(false);
                break;
            
            case 2:
                new Contas().executaConta();
                recarregaMenu(false);
                break;
 
            case 3:
                imprimir();
                recarregaMenu(false);
                break;
            
            case 4:
                recarregaMenu(true);
                break;
                
            case 5:
                View.sair();
                break;
                
            default:
                View.opcaoInvalida();       
                //recarregaMenu();
                break;
            } 
    }
    
               
}
