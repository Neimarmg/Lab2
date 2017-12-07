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
public class Produtos {
    int codProduto, codMarca, codNotacao;
    String descProruto,nomeMarca ,notacao;
    float preco,valorNotacao;

    public Produtos() {
    }

    public Produtos(int codProduto, int codMarca, int codNotacao, String descProruto,String nomeMarca, float preco, float valorNotacao, String  notacao) {
        this.codProduto = codProduto;
        this.codMarca = codMarca;
        this.codNotacao = codNotacao;
        this.descProruto = descProruto;
        this.nomeMarca = nomeMarca;
        this.preco = preco;
        this.valorNotacao = valorNotacao;
        this.notacao = notacao;
    }

    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public int getCodMarca() {
        return codMarca;
    }

    public void setCodMarca(int codMarca) {
        this.codMarca = codMarca;
    }

    public int getCodNotacao() {
        return codNotacao;
    }

    public void setCodNotacao(int codNotacao) {
        this.codNotacao = codNotacao;
    }

    public String getDescProruto() {
        return descProruto;
    }

    public void setDescProruto(String descProruto) {
        this.descProruto = descProruto;
    }

    public String getNomeMarca() {
        return nomeMarca;
    }

    public void setNomeMarca(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public float getValorNotacao() {
        return valorNotacao;
    }

    public void setValorNotacao(float valorNotacao) {
        this.valorNotacao = valorNotacao;
    }

    public String getNotacao() {
        return notacao;
    }

    public void setNotacao(String notacao) {
        this.notacao = notacao;
    }

   
    
  
}
