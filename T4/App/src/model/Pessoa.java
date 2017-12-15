package model;

public class Pessoa {
    int codPessoa,codTipoPessoa,codProfissao;
    String descTipoPessoa, descProfissao;
    String nome, cidade, cpf, email, Ativa, tipoPessoa, profissoa;

    public Pessoa() {
    } 
    
    public Pessoa(int codPessoa, int codTipoPessoa, int codProfissao, String descTipoPessoa, String descProfissao, String nome, String cidade, String cpf, String email, String Ativa, String tipoPessoa, String profissoa) {
        this.codPessoa = codPessoa;
        this.codTipoPessoa = codTipoPessoa;
        this.codProfissao = codProfissao;
        this.descTipoPessoa = descTipoPessoa;
        this.descProfissao = descProfissao;
        this.nome = nome;
        this.cidade = cidade;
        this.cpf = cpf;
        this.email = email;
        this.Ativa = Ativa;
        this.tipoPessoa = tipoPessoa;
        this.profissoa = profissoa;
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

    public String getDescTipoPessoa() {
        return descTipoPessoa;
    }

    public void setDescTipoPessoa(String descTipoPessoa) {
        this.descTipoPessoa = descTipoPessoa;
    }

    public String getDescProfissao() {
        return descProfissao;
    }

    public void setDescProfissao(String descProfissao) {
        this.descProfissao = descProfissao;
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

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getProfissoa() {
        return profissoa;
    }

    public void setProfissoa(String profissoa) {
        this.profissoa = profissoa;
    }
    
    
    @Override
    public  String toString(){
        return String.valueOf(codPessoa);
    }
   
}