package com.quatrocentosquatro.storemanagement.model;

/** 
 * <p> Superclasse abstrata Usuario para funcionários e administradores.
 * <p> Fornecedor não é um usuário do sistema e não herda dessa classe, ele é uma entidade separada, sem relação direta com o sistema de usuários.
 * 
 * @author João M. Chervinski
 */
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

    /**
     * Método abstrato para visualização do dashboard, a ser implementado pelas subclasses.
     */
    public abstract void visualizarDashboard(); // <- método polimórfico

    /* Getters e Setters */
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}
