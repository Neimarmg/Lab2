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
    
    public void criaNovaCompras(boolean continarComprando){
        setCodPedido(Controlador.getCodAuto());
        setCodCliente(cliente.getCodPessoa());
        setDataCompra(View.digitaString("Data compra"));
        
        if (continarComprando == true){
            criaNovoItenCompra(false);
        }
    }
    
    public void criaNovoItenCompra(boolean continarComprando){
      
        
    }
    
    public void criaImprimir(){
        
    }    
    
    public void recarregaMenu() throws Exception{
       new MenuView().menuVendas();
       new Controlador().selecionaMenu(false);
       executaVendas();            
    }
    
    public void executaVendas() throws Exception{       
       
        switch (Menu.getCod()) {
            case 1:
                criaImprimir();
                recarregaMenu();
                break;
            
            case 2:
                Dia.ImprimeHora();
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
