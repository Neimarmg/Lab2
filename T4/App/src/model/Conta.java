/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author 181100053
 */
public class Conta {
    int codConta,operacao;
    String nroConta, agencia, banco;
    Float saldoAtual;

    public Conta() {
    }

    public Conta(int codConta, int operacao, String nroConta, String agencia, String banco, Float saldoAtual) {
        this.codConta = codConta;
        this.operacao = operacao;
        this.nroConta = nroConta;
        this.agencia = agencia;
        this.banco = banco;
        this.saldoAtual = saldoAtual;
    }

    
    public int getCodConta() {
        return codConta;
    }

    public void setCodConta(int codConta) {
        this.codConta = codConta;
    }

    public int getOperacao() {
        return operacao;
    }

    public void setOperacao(int operacao) {
        this.operacao = operacao;
    }

    public String getNroConta() {
        return nroConta;
    }

    public void setNroConta(String nroConta) {
        this.nroConta = nroConta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public Float getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(Float saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    
    
    
}
