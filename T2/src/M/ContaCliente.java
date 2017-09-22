/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package M;

/**
 *
 * @author neimarmoises
 */
public class ContaCliente extends Pessoa{
    float saldo, valorSaque, valorDeposito;
    String nroConta, cliente = "0";
    
    public ContaCliente() {
    }    

    public ContaCliente(float saldo, float valorSaque, float valorDeposito, String nroConta, String cliente) {
        this.saldo = saldo;
        this.valorSaque = valorSaque;
        this.valorDeposito = valorDeposito;
        this.nroConta = nroConta;
        this.cliente = cliente;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getValorSaque() {
        return valorSaque;
    }

    public void setValorSaque(float valorSaque) {
        this.valorSaque = valorSaque;
    }

    public float getValorDeposito() {
        return valorDeposito;
    }

    public void setValorDeposito(float valorDeposito) {
        this.valorDeposito = valorDeposito;
    }

    public String getNroConta() {
        return nroConta;
    }

    public void setNroConta(String nroConta) {
        this.nroConta = nroConta;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

   
}
