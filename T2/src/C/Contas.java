/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package C;

import M.ContaCliente;
import M.Menu;
import M.Pessoa;
import V.Utilitarios.MenuView;
import V.View;

/**
 *
 * @author neimarmoises
 */
public class Contas extends ContaCliente{
    CadastroPessoa cliente = new CadastroPessoa();
    
    public  void criarConta(){
        setCliente(cliente.getCodPessoa());
        setNroConta(View.digitaString("Nro conta"));
        deposito();
    }
    
    
    
    public void deposito(){
        setCliente(View.digitaString(cliente.getCodPessoa()));
        setNroConta(View.digitaString("Nro conta:"));
        setValorDeposito(View.digitaFloat("Valor depósito"));
        View.msgl();
        imprimeExtrato("deposito");
    }
    
    
    public void saque(){
        setValorSaque(View.digitaFloat("Valor saque:"));
        calculaSaldo();
        if (getSaldo()>= getValorDeposito()){            
            imprimeExtrato("Saque");
            View.msgcr("\nSaque realizado com sucesso");
       }else{
            View.msgcr("Saldo insuficiente!\n");
            imprimeExtrato("Saque");
        }       
    }

    
    public void imprimeExtrato(String tipoExtrato){
        float valorMovimento;
        calculaSaldo();
        
        if(tipoExtrato.equals("deposito")){
            valorMovimento = getValorDeposito();
        
        }else if(tipoExtrato.equals("Saque")){
            valorMovimento = getValorSaque();
       
        }else{
            valorMovimento = 0;
        }         
        View.msgl();
        new CadastroPessoa().imprimir();
        View.msgcr(    
            "Nro conta: "+getNroConta()
            +"\nValor "+tipoExtrato + " :" +valorMovimento
            +"\nSaldo atual conta: "+ getSaldo()
            +"\nid pessoa conta: "+ new Pessoa().getCodPessoa()
        );
    }
    
    public void calculaSaldo(){
        setSaldo(getValorDeposito()-getValorSaque());
    }
    
    
     public void recarregaMenu() throws Exception{
        new MenuView().menuOpercoes();
        new Controlador().selecionaMenu(false);
        executaConta();            
    }
    

    public void executaConta() throws Exception{       
        
        switch (Menu.getCod()) {
            case 1:
               criarConta();
                recarregaMenu();
                break;
            
            case 2:
                deposito();
                recarregaMenu();
                break;
 
            case 3:
                saque();
                recarregaMenu();
                break;
            
            case 4:
                imprimeExtrato("");
                recarregaMenu();
                break;
            
            case 5:
                View.sair();
                break;
                
            default:
                View.opcaoInvalida();       
                recarregaMenu();
                break;
        } 
    }
 }
