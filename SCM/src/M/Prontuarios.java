/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package M;

import java.util.Date;

/**
 *
 * @author 181100053
 */
public class Prontuarios {
    int codProntuario;
    String descConsulta, descReceita;
    Date cadataConsulta;

    public int getCodProntuario() {
        return codProntuario;
    }

    public void setCodProntuario(int codProntuario) {
        this.codProntuario = codProntuario;
    }

    public String getDescConsulta() {
        return descConsulta;
    }

    public void setDescConsulta(String descConsulta) {
        this.descConsulta = descConsulta;
    }

    public String getDescReceita() {
        return descReceita;
    }

    public void setDescReceita(String descReceita) {
        this.descReceita = descReceita;
    }

    public Date getCadataConsulta() {
        return cadataConsulta;
    }

    public void setCadataConsulta(Date cadataConsulta) {
        this.cadataConsulta = cadataConsulta;
    }
    
    
    
    
    
}
