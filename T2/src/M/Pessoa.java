package M;

public class Pessoa {
    String codPessoa, codTipo, cpf ;
    String nome;

    public  Pessoa(){
    }

    public Pessoa(String codPessoa, String codTipo, String cpf, String nome) {
        this.codPessoa = codPessoa;
        this.codTipo = codTipo;
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getCodPessoa() {
        return codPessoa;
    }

    public void setCodPessoa(String codPessoa) {
        this.codPessoa = codPessoa;
    }

    public String getCodTipo() {
        return codTipo;
    }

    public void setCodTipo(String codTipo) {
        this.codTipo = codTipo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    
}
