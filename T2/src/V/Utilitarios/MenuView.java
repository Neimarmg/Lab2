/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package V.Utilitarios;

import M.Menu;
import V.View;

/**
 *
 * @author Neimar
 */
public class MenuView {
            
    public  void menuPrincipal(){
        Menu.setDescMenu(
                "\n 1 - Produtos"
                +"\n 2 - Clientes"                
                +"\n 3 - Compras"
                +"\n 4 - Relatórios"
                +"\n 5 - Operações"
                +"\n 6 - Sair\n"        
        );
        View.msgr(Menu.getDescMenu());
    }   
    

    public  void menuProdutos(){
        Menu.setDescMenu(
                "\n 1 - Novo produto"
                +"\n 2 - Imprimir"
                +"\n 3 - Sair\n"                
        );
        View.msgr(Menu.getDescMenu());
    }   
    
    
    public  void menuOpercoes(){
        Menu.setDescMenu(
                "\n 1 - Nova conta"
                +"\n 2 - Novo Depósito"
                +"\n 3 - Novo Saque"
                +"\n 4 - Saldo"
                +"\n 5 - Imprimir"
                +"\n 6 - Sair\n"                 
        );
        View.msgr(Menu.getDescMenu());
    }  
       
       
    public  void menuClientes(){
        Menu.setDescMenu(
                 "\n 1 - Novo Cliente"
                +"\n 2 - Consultar conta"
                +"\n 3 - Imprimir"
                +"\n 4 - Sair\n" 
                
        );
        View.msgr(Menu.getDescMenu());
    }  
    
       
    public  void menuVendas(){
        Menu.setDescMenu(
                 "\n 1 - Novo Compra"
                +"\n 2 - "
                +"\n 3 - Imprimir"
                +"\n 4 - Sair\n" 
                
        );
        View.msgr(Menu.getDescMenu());
    }  
       
    
    public void carregaMenu(){      
        
        switch (Menu.getCod()) {
            case 1:
               menuProdutos();
                break;
            
            case 2:
                menuClientes();
                break;
 
            case 3:
                menuVendas();
                break;

            case 4:
                menuVendas();
                break;
                
            case 6:
                menuOpercoes();
                break;
            
            case 7:
                View.sair();
                break;
                
            default:
                V.View.msg(Menu.getCod());
                //View.opcaoInvalida();
                //carregaMenu();
                break;
            }
    }
    
}
