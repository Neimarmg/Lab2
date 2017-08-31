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
public class Contatos {
    String telefone, email, pagweb;
    int codTipoContato;
    boolean ativo;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPagweb() {
        return pagweb;
    }

    public void setPagweb(String pagweb) {
        this.pagweb = pagweb;
    }

    public int getCodTipoContato() {
        return codTipoContato;
    }

    public void setCodTipoContato(int codTipoContato) {
        this.codTipoContato = codTipoContato;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
}
