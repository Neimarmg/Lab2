
package exemplotableview;

import javafx.beans.property.SimpleStringProperty;


public class PacienteTableModel {
    private SimpleStringProperty nome;
    private SimpleStringProperty rg;
    private SimpleStringProperty dataNasc;

    public PacienteTableModel(String nome, String rg, String dataNasc) {
        this.nome = new SimpleStringProperty(nome);
        this.rg = new SimpleStringProperty(rg);
        this.dataNasc = new SimpleStringProperty(dataNasc);
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getRg() {
        return rg.get();
    }

    public void setRg(String rg) {
        this.rg.set(rg);
    }

    public String getDataNasc() {
        return dataNasc.get();
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc.set(dataNasc);
    }
    
    
    
}
