package com.quatrocentosquatro.storemanagement.model;

public class Fornecedor {
    private int id;
    private String nome;
    private String telefone;
    private String email;
    private String cnpj;
    private boolean isAtivo;

    public Fornecedor(int id, String nome, String telefone, String email, String cnpj) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cnpj = cnpj;
        this.isAtivo = true;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public boolean isAtivo() { return isAtivo; }
    public void setAtivo(boolean ativo) { isAtivo = ativo; }
}