/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package M;

import java.sql.Date;

/**
 *
 * @author neimarmoises
 */
public class VendaPedido {
    int codVendaPedido,codCliente;

    String nomeCliente;
    Date dataVenda;

    public VendaPedido() {
    }

    public VendaPedido(int codVendaPedido, int codCliente, Date dataVenda, String nomeCliente) {
        this.codVendaPedido = codVendaPedido;
        this.codCliente = codCliente;
        this.dataVenda = dataVenda;
        this.nomeCliente = nomeCliente;
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

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }
    
    
    
    
}
