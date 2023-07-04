package br.ufscar.dc.dsw.domain;

public class Locadora {
    private String CNPJ;
    private long id;
    private String cidade;
    private String nome;

    public Locadora(long id) {
        this.id = id;
    }

    public Locadora(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Locadora(long id, String nome, String CNPJ) {
        this.id = id;
        this.nome = nome;
        this.CNPJ = CNPJ;
    }

    public Locadora(long id, String nome, String CNPJ, String cidade) {
        this.id = id;
        this.nome = nome;
        this.CNPJ = CNPJ;
        this.cidade = cidade;
    }

    //Id
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    //CNPJ
    public String getCNPJ() {
        return CNPJ;
    }
    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    //Nome
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    //Cidade
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.nome = cidade;
    }
}