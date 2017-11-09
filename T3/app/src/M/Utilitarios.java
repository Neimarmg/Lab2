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
public class Utilitarios {
    int codUtilitario,	codTipoUtilirario,codSubGrupo;
    String  utilitario,favorita,Obs;

    public Utilitarios() {
    }

    public Utilitarios(int codUtilitario, int codTipoUtilirario, int codSubGrupo, String utilitario, String favorita, String Obs) {
        this.codUtilitario = codUtilitario;
        this.codTipoUtilirario = codTipoUtilirario;
        this.codSubGrupo = codSubGrupo;
        this.utilitario = utilitario;
        this.favorita = favorita;
        this.Obs = Obs;
    }

    public int getCodUtilitario() {
        return codUtilitario;
    }

    public void setCodUtilitario(int codUtilitario) {
        this.codUtilitario = codUtilitario;
    }

    public int getCodTipoUtilirario() {
        return codTipoUtilirario;
    }

    public void setCodTipoUtilirario(int codTipoUtilirario) {
        this.codTipoUtilirario = codTipoUtilirario;
    }

    public int getCodSubGrupo() {
        return codSubGrupo;
    }

    public void setCodSubGrupo(int codSubGrupo) {
        this.codSubGrupo = codSubGrupo;
    }

    public String getUtilitario() {
        return utilitario;
    }

    public void setUtilitario(String utilitario) {
        this.utilitario = utilitario;
    }

    public String getFavorita() {
        return favorita;
    }

    public void setFavorita(String favorita) {
        this.favorita = favorita;
    }

    public String getObs() {
        return Obs;
    }

    public void setObs(String Obs) {
        this.Obs = Obs;
    }
    
    
}


/*SELECT codPessoa, nome, cidade, utilitarios.utilitario as Tipo, profissao.utilitario as profissoa, cpf, email, Ativa, cref 

FROM `pessoa`
	LEFT JOIN utilitarios ON
    	pessoa.codTipoPessoa = utilitarios.codUtilitario
       
     LEFT JOIN utilitarios as profissao ON	
     	pessoa.codProfissao = profissao.codUtilitario


WHERE 1*/