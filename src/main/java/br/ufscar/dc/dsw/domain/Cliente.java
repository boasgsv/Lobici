package br.ufscar.dc.dsw.domain;

import java.util.Date;


public class Cliente {
    private String CPF;
    private String nome;
    private String telefone;
    private String sexo;
    private Date data_nascimento;
    private long Id;

    public Cliente(String CPF, String nome, String telefone, String sexo, Date data) {
        Id = 5;
        this.CPF = CPF;
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
        this.data_nascimento = data;
    }
    public Cliente(Long Id,String CPF, String nome, String telefone, String sexo, Date data_nascimento) {
        this.Id = Id;
        this.CPF = CPF;
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
        this.data_nascimento = data_nascimento;
    }
   

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    } 
    public long getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getDataNascimento() {
        return data_nascimento;
    }

    public void setDataNascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }
}

