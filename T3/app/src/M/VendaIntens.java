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
public class VendaIntens extends Produtos{
    int codVendaIntens,	codVendaPedido,	qtVenda;
    double desconto=0, acrescimo=0, valorTotal, valorBruto, valorLiquido;

    public VendaIntens() {
    }   

    public VendaIntens(int codVendaIntens, int codVendaPedido, int qtVenda, double valorTotal, double valorBruto, double valorLiquido, int codProduto, int codMarca, int codNotacao, String descProruto, String nomeMarca, float preco, float valorNotacao, String notacao) {
        super(codProduto, codMarca, codNotacao, descProruto, nomeMarca, preco, valorNotacao, notacao);
        this.codVendaIntens = codVendaIntens;
        this.codVendaPedido = codVendaPedido;
        this.qtVenda = qtVenda;
        this.valorTotal = valorTotal;
        this.valorBruto = valorBruto;
        this.valorLiquido = valorLiquido;
    }

    public void calculaVenda(){
        setValorBruto(getQtVenda()*getPreco());
        setValorTotal(getValorBruto() - getDesconto());
        setValorLiquido(getValorTotal()+ getAcrescimo());
    }
    

    public int getCodVendaIntens() {
        return codVendaIntens;
    }

    public void setCodVendaIntens(int codVendaIntens) {
        this.codVendaIntens = codVendaIntens;
    }

    public int getCodVendaPedido() {
        return codVendaPedido;
    }

    public void setCodVendaPedido(int codVendaPedido) {
        this.codVendaPedido = codVendaPedido;
    }

    public int getQtVenda() {
        return qtVenda;
    }

    public void setQtVenda(int qtVenda) {
        this.qtVenda = qtVenda;

    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getValorBruto() {
   
        return valorBruto;
    }

    public void setValorBruto(double valorBruto) {       
        this.valorBruto = valorBruto;
    }

    public double getValorLiquido() {

        return valorLiquido;
    }

    public void setValorLiquido(double valorLiquido) {
        this.valorLiquido = valorLiquido;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getAcrescimo() {        
        return acrescimo;
    }

    public void setAcrescimo(double acrescimo) {
        this.acrescimo = acrescimo;
    }
    
    
    @Override
    public int getCodProduto() {
        return codProduto;
    }

    @Override
    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    @Override
    public int getCodMarca() {
        return codMarca;
    }

    @Override
    public void setCodMarca(int codMarca) {
        this.codMarca = codMarca;
    }

    @Override
    public int getCodNotacao() {
        return codNotacao;
    }
    
    @Override
    public void setCodNotacao(int codNotacao) {
        this.codNotacao = codNotacao;
    }
    
    @Override
    public String getDescProruto() {
        return descProruto;
    }
    
    @Override
    public void setDescProruto(String descProruto) {
        this.descProruto = descProruto;
    }
    
    @Override
    public String getNomeMarca() {
        return nomeMarca;
    }
    
    @Override
    public void setNomeMarca(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }
    
    @Override
    public String getNotacao() {
        return notacao;
    }
    
    @Override
    public void setNotacao(String notacao) {
        this.notacao = notacao;
    }
    
    @Override
    public float getPreco() {
        return preco;
    }
    
    @Override
    public void setPreco(float preco) {
        this.preco = preco;
    }
    
    @Override
    public float getValorNotacao() {
        return valorNotacao;
    }

    @Override
    public void setValorNotacao(float valorNotacao) {
        this.valorNotacao = valorNotacao;
    }
    
       
}
