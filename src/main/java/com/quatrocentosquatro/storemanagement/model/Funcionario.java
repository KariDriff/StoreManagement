package main.java.com.quatrocentosquatro.storemanagement.model;

public class Funcionario extends Usuario {
    private boolean isAdmin;
    private String cargo;

    public Funcionario(int id, String nome, String login, String senha, String cargo) {
        super(id, nome, login, senha);
        this.cargo = cargo;
        this.isAdmin = false;
    }

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

    public boolean isAdmin() { return isAdmin; }
    public void setAdmin(boolean admin) { isAdmin = admin; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
}
