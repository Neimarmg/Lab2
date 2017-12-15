/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
/**
 *
 * @author neimarmoises
 */
public class VendaPedido {
    int codVendaPedido,codCliente, codConta;
    String nomeCliente,dataVenda;
   

    public VendaPedido() {
    }

    public VendaPedido(int codVendaPedido, int codCliente, String dataVenda, String nomeCliente, int codConta) {
        this.codVendaPedido = codVendaPedido;
        this.codCliente = codCliente;
        this.dataVenda = dataVenda;
        this.nomeCliente = nomeCliente;
        this.codConta = codConta;
    }

    public int getCodConta() {
        return codConta;
    }

    public void setCodConta(int codConta) {
        this.codConta = codConta;
    }

    
    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }    

    public int getCodVendaPedido() {
        return codVendaPedido;
    }

    public void setCodVendaPedido(int codVendaPedido) {
        this.codVendaPedido = codVendaPedido;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }  
    
}
