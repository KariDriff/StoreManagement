package com.quatrocentosquatro.storemanagement.model;

/**
 * Classe que representa um funcionário no sistema.
 * Herda da classe Usuario e implementa funcionalidades específicas para funcionários.
 * 
 * @author João M. Chervinski
 */
public class Funcionario extends Usuario {
    private static final long serialVersionUID = 1L; // Versão de serialização para compatibilidade

    private boolean isAdmin; // Indica se o funcionário é um administrador.
    private String cargo; // Cargo do funcionário.

    /**
     * Construtor para criar um novo funcionário.
     * 
     * @param id    (int)    - ID do funcionário.
     * @param nome  (String) - Nome do funcionário.
     * @param login (String) - Login do funcionário.
     * @param senha (String) - Senha do funcionário.
     * @param cargo (String) - Cargo do funcionário.
     */
    public Funcionario(int id, String nome, String login, String senha, String cargo) {
        super(id, nome, login, senha); // Chama o construtor da classe pai (Usuario).
        this.cargo = cargo;
        this.isAdmin = false; // Define o funcionário como não administrador por padrão.
    }

    /**
     * <p> Implementação do método para visualizar o dashboard do funcionário.
     * <p> Exibe informações como nome, cargo e ações disponíveis.
     */
    @Override
    public void visualizarDashboard() {
        System.out.println("=== PAINEL DO FUNCIONÁRIO ===");
        System.out.println("Nome: " + nome);
        System.out.println("Cargo: " + cargo);
        System.out.println("Ações disponíveis:");
        System.out.println("   - Registrar venda");
        System.out.println("   - Visualizar estoque");
        System.out.println("   - Consultar produtos");
        System.out.println("Acesso restrito: sem permissões administrativas.");
        System.out.println("==============================\n");
    }

    /* Getters e Setters */

    public boolean isAdmin() { return isAdmin; }
    public void setAdmin(boolean admin) { isAdmin = admin; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
}
