package M;

public class Pessoa {
    int codPessoa,codTipoPessoa,codProfissao;
    String nome="" , cidade="", cpf="", email="", Ativa="";
    
    public Pessoa(){ 
    }
    
    public Pessoa(int codPessoa, String nome, String cidade, int codTipoPessoa, int codProfissao, String cpf, String email, String Ativa) {
        this.codPessoa = codPessoa;
        this.nome = nome;
        this.cidade = cidade;
        this.codTipoPessoa = codTipoPessoa;
        this.codProfissao = codProfissao;      
        this.cpf = cpf;
        this.email = email;
        this.Ativa = Ativa;
    }

    public int getCodPessoa() {
        return codPessoa;
    }

    public void setCodPessoa(int codPessoa) {
        this.codPessoa = codPessoa;
    }

    public int getCodTipoPessoa() {
        return codTipoPessoa;
    }

    public void setCodTipoPessoa(int codTipoPessoa) {
        this.codTipoPessoa = codTipoPessoa;
    }

    public int getCodProfissao() {
        return codProfissao;
    }

    public void setCodProfissao(int codProfissao) {
        this.codProfissao = codProfissao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAtiva() {
        return Ativa;
    }

    public void setAtiva(String Ativa) {
        this.Ativa = Ativa;
    }    
}