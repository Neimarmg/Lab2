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
    
    private void imprimeMenu(){
        View.msgr(Menu.getDescMenu());
    }
            
    public  void menuPrincipal(){
        Menu.setDescMenu(
                "\n 1 - Produtos"
                +"\n 2 - Clientes"                
                +"\n 3 - Compras"
                +"\n 4 - Relatórios"
                +"\n 5 - Operações"
                +"\n 6 - Sair\n"        
        );
        imprimeMenu();
    }   
    

    public  void menuProdutos(){
        Menu.setDescMenu(
                 "\n 1 - Novo produto"
                +"\n 2 - Atualizar"
                +"\n 3 - Excluir"
                +"\n 4 - Imprimir"
                +"\n 5 - Retornar"
                +"\n 6 - Sair\n"                
        );
        imprimeMenu();
    }   
    
    
    public  void menuOpercoes(){
        Menu.setDescMenu(
                 "\n 1 - Nova conta"
                +"\n 2 - Novo Depósito"
                +"\n 3 - Novo Saque"
                +"\n 4 - Saldo"
                +"\n 5 - Imprimir"
                +"\n 6 - Retornar"        
                +"\n 7 - Sair\n"                 
        );
        imprimeMenu();
    }  
       
       
    public  void menuClientes(){
        Menu.setDescMenu(
                 "\n 1 - Novo Cliente"
                +"\n 2 - Atualizar"
                +"\n 3 - Excluir"
                +"\n 4 - Imprimir"
                +"\n 5 - Retornar"  
                +"\n 6 - Sair\n" 
               
        );
        imprimeMenu();
    }  
    
       
    public  void menuVendas(){
        Menu.setDescMenu(
                 "\n 1 - Novo Compra"
                +"\n 2 - Atualizar"
                +"\n 3 - Excluir"
                +"\n 4 - Imprimir"
                +"\n 5 - Retornar"               
                +"\n 6 - Sair\n" 
                
        );
        imprimeMenu();
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
                
                break;
                
            case 5:
                menuOpercoes();
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
