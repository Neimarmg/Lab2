/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package M;

/**
 *
 * @author 181100053
 */
public class Endereco {
    String rua,nro, compl;
    int codCidade,codTipoEndereco, codEmpresa;
    boolean ativo;

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public String getCompl() {
        return compl;
    }

    public void setCompl(String compl) {
        this.compl = compl;
    }

    public int getCodCidade() {
        return codCidade;
    }

    public void setCodCidade(int codCidade) {
        this.codCidade = codCidade;
    }

    public int getCodTipoEndereco() {
        return codTipoEndereco;
    }

    public void setCodTipoEndereco(int codTipoEndereco) {
        this.codTipoEndereco = codTipoEndereco;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }   

    public int getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(int codEmpresa) {
        this.codEmpresa = codEmpresa;
    }
    
    
}
