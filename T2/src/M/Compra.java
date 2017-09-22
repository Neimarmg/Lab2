/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package M;

import java.util.Date;

/**
 *
 * @author neimarmoises
 */
public class Compra extends ItensCompras {
    String codPedido,codCliente;
    String dataCompra;

    public Compra(){
        
    }
        
    public Compra(String codPedido, String codCliente, String dataCompra) {
        this.codPedido = codPedido;
        this.codCliente = codCliente;
        this.dataCompra = dataCompra;
    }

    
    public String getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(String codPedido) {
        this.codPedido = codPedido;
    }

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public String getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }
   
}
