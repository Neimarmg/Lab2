/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package V;

import M.Menu;

/**
 *
 * @author Neimar
 */
public class MenuView {
     Menu menu = new Menu();
            
    public  void menuPrincipal(){
        menu.setDescMenu(
                "\n 1 - Produtos"
                +"\n 2 - Clientes contas"
                +"\n 3 - Compras"
                +"\n 4 - Relatórios"
                +"\n 6 - Sair\n"        
        );
        View.msgr(menu.getDescMenu());
    }   
    

    public  void menuProdutos(){
        menu.setDescMenu(
                "\n 1 - Lista de produtos"
                +"\n 2 - Novo produto"
                +"\n 3 - Sair\n"                
        );
        View.msgr(menu.getDescMenu());
    }   
    
    
    public  void menuOpercoes(){
        menu.setDescMenu(
                "\n 1 - Nova conta"
                +"\n 2 - Novo Depósito"
                +"\n 3 - Novo Saque"
                +"\n 4 - Saldo"
                +"\n 5 - Imprimir"
                +"\n 6 - Sair\n"                 
        );
        View.msgr(menu.getDescMenu());
    }  
       
       
    public  void menuClientes(){
        menu.setDescMenu(
                 "\n 1 - Novo Cliente"
                +"\n 2 - Consultar conta"
                +"\n 3 - Imprimir"
                +"\n 4 - Sair\n" 
                
        );
        View.msgr(menu.getDescMenu());
    }  
    
       
    public  void menuVendas(){
        menu.setDescMenu(
                 "\n 1 - Novo Compra"
                +"\n 2 - "
                +"\n 3 - Imprimir"
                +"\n 4 - Sair\n" 
                
        );
        View.msgr(menu.getDescMenu());
    }  
       
    
    public void carregaMenu(){      
        
        switch (menu.getCod()) {
            case 1:
                menuCadastro();
                break;
            
            case 2:
                menuVendas();
                break;
 
            case 3:
                menuAgendamentosConsultas();
                break;
            
            case 6:
                View.sair();
                break;
                
            default:
                View.opcaoInvalida();
                carregaMenu();
                break;
            }
    }
    
}
