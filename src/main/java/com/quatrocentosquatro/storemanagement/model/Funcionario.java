package com.quatrocentosquatro.storemanagement.model;

/**
 * Classe que representa um funcionário no sistema.
 * Herda da classe Usuario e implementa funcionalidades específicas para funcionários.
 */
public class Funcionario extends Usuario {
    private boolean isAdmin; // Indica se o funcionário é um administrador.
    private String cargo; // Cargo do funcionário.

    /**
     * Construtor para criar um novo funcionário.
     * @param id ID do funcionário.
     * @param nome Nome do funcionário.
     * @param login Login do funcionário.
     * @param senha Senha do funcionário.
     * @param cargo Cargo do funcionário.
     */
    public Funcionario(int id, String nome, String login, String senha, String cargo) {
        super(id, nome, login, senha); // Chama o construtor da classe pai (Usuario).
        this.cargo = cargo;
        this.isAdmin = false; // Define o funcionário como não administrador por padrão.
    }

    /**
     * Implementação do método para visualizar o dashboard do funcionário.
     * Exibe informações como nome, cargo e ações disponíveis.
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

    /**
     * Retorna se o funcionário é um administrador.
     * @return true se for administrador, false caso contrário.
     */
    public boolean isAdmin() { return isAdmin; }

    /**
     * Define se o funcionário é um administrador.
     * @param admin Valor booleano para definir se é administrador.
     */
    public void setAdmin(boolean admin) { isAdmin = admin; }

    /**
     * Retorna o cargo do funcionário.
     * @return O cargo do funcionário.
     */
    public String getCargo() { return cargo; }

    /**
     * Define o cargo do funcionário.
     * @param cargo O cargo a ser definido.
     */
    public void setCargo(String cargo) { this.cargo = cargo; }
}
