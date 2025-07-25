package com.quatrocentosquatro.storemanagement.model;

/**
 * Classe que representa um Administrador, que herda de Funcionario.
 * 
 * @author João M. Chervinski
 */
public class Administrador extends Funcionario { // O compilador avisa que o implemento é redundante, porém eu fiz testes e só funcionou com ele... c'est la vie

    private static final long serialVersionUID = 1L; // Versão de serialização para compatibilidade

    /**
     * Construtor da classe Administrador.
     *
     * @param id    (int)    - O ID do administrador.
     * @param nome  (String) - O nome do administrador.
     * @param login (String) - O login do administrador.
     * @param senha (String) - A senha do administrador.
     */
    public Administrador(int id, String nome, String login, String senha) {
        super(id, nome, login, senha, "Administrador");
        setAdmin(true);
    }

    /**
     * <p> Método para visualizar o painel do administrador.
     * <p> Exibe informações e ações disponíveis para o administrador.
     */
    @Override
    public void visualizarDashboard() {
        System.out.println("=== PAINEL DO ADMINISTRADOR ===");
        System.out.println("Nome: " + getNome());
        System.out.println("Cargo: " + getCargo());
        System.out.println("Ações disponíveis:");
        System.out.println("   - Gerenciar usuários");
        System.out.println("   - Gerenciar produtos e estoque");
        System.out.println("   - Acompanhar relatórios financeiros");
        System.out.println("   - Criar/Editar eventos e promoções");
        System.out.println("   - Ver logs do sistema");
        System.out.println("===============================\n");
    }
}
