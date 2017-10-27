/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package C;

import M.Menu;
import M.Pessoa;
import V.Utilitarios.MenuView;
import V.View;

/**
 *
 * @author neimarmoises
 */
public class PessoaControle extends Pessoa{
    
    public void inserirNovo(){
        setCodPessoa(0);
        setNome(View.digitaString("Nome"));
        setCidade(View.digitaString("Cidade"));
        setCodTipoPessoa(View.digitaNumero("CodTipoPessoa"));
        setCodProfissao(View.digitaNumero("CodProfissão"));
        setCpf(View.digitaString("Cpf"));
        setEmail(View.digitaString("E-mail"));
        setAtiva(View.digitaString("Ativo"));
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
                inserirNovo();
                recarregaMenu(false);
                break;
            
            case 2:

                recarregaMenu(false);
                break;
 
            case 3:

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
