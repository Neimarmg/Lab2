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
public class ItensCompras {
    String codItens = "0",codProduto = "0", qt;
    String armazenaCompra;
    
    public ItensCompras() {
    }  
   

    public String getCodItens() {
        return codItens;
    }

    public void setCodItens(String codItens) {
        this.codItens = codItens;
    }

    public String getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(String codProduto) {
        this.codProduto = codProduto;
    }

    public String getQt() {
        return qt;
    }

    public void setQt(String qt) {
        this.qt = qt;
    }

    public String getArmazenaCompra() {
        return armazenaCompra;
    }

    public void setArmazenaCompra(String armazenaCompra) {
        
        this.armazenaCompra = armazenaCompra;
    }
    
    
}
