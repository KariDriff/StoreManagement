package com.quatrocentosquatro.storemanagement.model;

// Superclasse abstrata Usuario para funcionários e administradores, fornecedor não é um usuário do sistema
// e não herda dessa classe. Fornecedor é uma entidade separada, sem relação direta com o sistema de usuários.

public abstract class Usuario {
    protected int id;
    protected String nome;
    protected String login;
    protected String senha;

    public Usuario() {}

    public Usuario(int id, String nome, String login, String senha) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public abstract void visualizarDashboard(); // <- método polimórfico

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}
