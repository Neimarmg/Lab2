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
public class Horario {
    int codHorario,  codHabilitcacaoPessoa, codEspecialidade, codConsultorio;
    
    String horaInicio, horaFim;

    public int getCodHorario() {
        return codHorario;
    }

    public void setCodHorario(int codHorario) {
        this.codHorario = codHorario;
    }

    public int getCodHabilitcacaoPessoa() {
        return codHabilitcacaoPessoa;
    }

    public void setCodHabilitcacaoPessoa(int codHabilitcacaoPessoa) {
        this.codHabilitcacaoPessoa = codHabilitcacaoPessoa;
    }

    public int getCodEspecialidade() {
        return codEspecialidade;
    }

    public void setCodEspecialidade(int codEspecialidade) {
        this.codEspecialidade = codEspecialidade;
    }

    public int getCodConsultorio() {
        return codConsultorio;
    }

    public void setCodConsultorio(int codConsultorio) {
        this.codConsultorio = codConsultorio;
    }


    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }
    
    
    
}
