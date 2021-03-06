/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package C;

import M.Menu;
import M.Produtos;
import V.Utilitarios.MenuView;
import V.View;

/**
 *
 * @author neimarmoises
 */
public class CadastroProdutos extends Produtos{
    //Produtos produto = new Produtos();
    Controlador controlador = new Controlador();
    
   
    public void CriaProduto(){
        setCodtuto(Controlador.getCodAuto());
        setDescProruto(View.digitaString("Descrição produto"));
        setPreco(View.digitaString("Preço"));
        View.msgl();
    }
    
    public void Imprime(){
        if(Integer.parseInt(getCodtuto()) > 0  ){
            View.msgcr(
                "\n Id produto: " +getCodtuto()
                +"\n Produto  : " +getDescProruto()
                +"\n Preço    : " +getPreco()
            );
        }else{
            View.msgr("Não foram encontrados produtos!");
        }
    }
    
    public void recarregaMenu(boolean exibeMenuPrincipal) throws Exception{
        new MenuView().menuVendas();
        new Controlador().selecionaMenu(exibeMenuPrincipal);       
        
        if(exibeMenuPrincipal == false) {
            executaProduto();
        }else{
            new Controlador().carregaApp();
        }
    }
    
    public void executaProduto() throws Exception{       
       
        switch (Menu.getCod()) {
            case 1:
                CriaProduto();                
                recarregaMenu(false);
                break;
                
            case 2:
                Imprime();
                recarregaMenu(false);
                break; 
                
            case 3:
                recarregaMenu(true);
                break;
                
            case 4:
                View.sair();
                break;
                
            default:
                View.opcaoInvalida();       
                break;
            } 
    }
    
}
