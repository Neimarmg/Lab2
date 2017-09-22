/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package M;

import java.util.ArrayList;

/**
 *
 * @author neimarmoises
 */
public class Produtos {
    String codtuto= "0" ,descProruto,preco;

    public Produtos() {
        
    } 
    
    
    public Produtos(String codtuto, String descProruto, String preco) {
        this.codtuto = codtuto;
        this.descProruto = descProruto;
        this.preco = preco;
    }

    public String getCodtuto() {
        return codtuto;
    }

    public void setCodtuto(String codtuto) {
        this.codtuto = codtuto;
    }

    public String getDescProruto() {
        return descProruto;
    }

    public void setDescProruto(String descProruto) {
        this.descProruto = descProruto;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
    
    
}
