package com.quatrocentosquatro.storemanagement.model;

public class Administrador extends Funcionario {

    public Administrador(int id, String nome, String login, String senha) {
        super(id, nome, login, senha, "Administrador");
        setAdmin(true);
    }

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
