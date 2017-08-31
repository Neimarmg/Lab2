package M;

public class Pessoa {
    int codPessoa, codTipo, codProfissao, cpf ;
    String nome;

    public  Pessoa(){
    }

    public Pessoa(int codPessoam, int codTipo, int codProfissao, int cpf, String nome) {
        this.codPessoa = codPessoam;
        this.codTipo = codTipo;
        this.codProfissao = codProfissao;
        this.cpf = cpf;
        this.nome = nome;
    }
 
    
    
    public int getCodPessoa() {
        return codPessoa;
    }

    public void setCodPessoa(int codPessoa) {
        this.codPessoa = codPessoa;
    }

    public int getCodTipo() {
        return codTipo;
    }

    public void setCodTipo(int codTipo) {
        this.codTipo = codTipo;
    }

    public int getCodProfissao() {
        return codProfissao;
    }

    public void setCodProfissao(int codProfissao) {
        this.codProfissao = codProfissao;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
