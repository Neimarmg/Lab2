/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package C;

import M.Compra;
import M.Menu;
import M.Pessoa;
import V.Utilitarios.Dia;
import V.Utilitarios.MenuView;
import V.View;

/**
 *
 * @author neimarmoises
 */
public class Vendas extends Compra {
    Pessoa cliente = new Pessoa();

    public void criaNovoItenCompra(){
        setCodItens(Controlador.getCodAuto());
        setCodProduto(View.digitaString("Codigo produto"));
        setQt(View.digitaString("Quantidade"));        
    }
    
    public void criaNovaCompras(String continarComprando){
        if (continarComprando.equals("?")){    
            setCodPedido(Controlador.getCodAuto());
            setCodCliente(cliente.getCodPessoa());
            setDataCompra(View.digitaString("Data compra"));
            View.msgcr("intens da compra");
            criaNovoItenCompra();
            criaNovaCompras(View.digitaString("Continuar Comprando"));
        
        }else if (continarComprando.equals("sim")){
            criaNovoItenCompra();
            criaNovaCompras(View.digitaString("Continuar Comprando"));
            View.msgl();
        }
    }
    
    public void imprime(){
        
    }    
    
    public void recarregaMenu() throws Exception{
       new MenuView().menuVendas();
       new Controlador().selecionaMenu(false);
       executaVendas();            
    }
    
    public void executaVendas() throws Exception{       
       
        switch (Menu.getCod()) {
            case 1:
                criaNovaCompras("?");
                recarregaMenu();
                break;
            
            case 2:
                imprime();
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
