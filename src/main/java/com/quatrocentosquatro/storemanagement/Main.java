package com.quatrocentosquatro.storemanagement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.quatrocentosquatro.storemanagement.controller.GerenciarEstoque;
import com.quatrocentosquatro.storemanagement.controller.GerenciarFornecedores;
import com.quatrocentosquatro.storemanagement.controller.GerenciarUsuarios;
import com.quatrocentosquatro.storemanagement.controller.ProcessamentoDeVendas;
import com.quatrocentosquatro.storemanagement.controller.Financeiro;
import com.quatrocentosquatro.storemanagement.model.Administrador;
import com.quatrocentosquatro.storemanagement.model.Fornecedor;
import com.quatrocentosquatro.storemanagement.model.Funcionario;
import com.quatrocentosquatro.storemanagement.model.Produto;
import com.quatrocentosquatro.storemanagement.model.Usuario;

/**
 * Classe principal que inicia o sistema de gerenciamento de loja.
 * Permite login, exibe menus e executa ações baseadas no tipo de usuário.
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Financeiro financeiro = new Financeiro();
    private static GerenciarEstoque estoqueController = new GerenciarEstoque();
    private static ProcessamentoDeVendas vendasController = new ProcessamentoDeVendas(estoqueController, financeiro);
    private static GerenciarUsuarios usuarioController = new GerenciarUsuarios();
    private static GerenciarFornecedores fornecedorController = new GerenciarFornecedores();
    private static Usuario usuarioLogado = null;

    // Metodo de login
    private static void login() {

        usuarioController.adicionarAdministrador("Admin", "adm", "1234");

        System.out.println("=== LOGIN ===");
        System.out.print("Login: ");
        String login = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        usuarioLogado = usuarioController.autenticar(login, senha);

        if (usuarioLogado != null) {
            System.out.println("Login realizado com sucesso.");
            System.out.println("Olá, " + usuarioLogado.getNome() + "!");
        } else {
            System.out.println("Login inválido.");
        }
    }

    public static void main(String[] args) {
        while (usuarioLogado == null) {
            login();
        }

        boolean executando = true;
        while (executando) {
            if (usuarioLogado instanceof Administrador) {
                menuAdministrador();
            } else {
                menuFuncionario();
            }

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (usuarioLogado instanceof Administrador) {
                executando = executarAcaoAdmin(opcao);
            } else {
                executando = executarAcaoFuncionario(opcao);
            }
        }
    }

    // === Metodos de Menu ===
    private static void menuAdministrador() {
        System.out.println("\n===== MENU ADMINISTRADOR =====");
        System.out.println("1 - Cadastrar produto");
        System.out.println("2 - Listar produtos");
        System.out.println("3 - Atualizar produto");
        System.out.println("4 - Remover produto");
        System.out.println("5 - Cadastrar funcionário/admin");
        System.out.println("6 - Listar usuários");
        System.out.println("7 - Atualizar usuários");
        System.out.println("8 - Remover usuários");
        System.out.println("9 - Exibir dashboards");
        System.out.println("10 - Cadastrar fornecedor");
        System.out.println("11 - Listar fornecedores");
        System.out.println("12 - Atualizar fornecedor");
        System.out.println("13 - Remover fornecedor");
        System.out.println("14 - Registrar entrada financeira");
        System.out.println("15 - Registrar saída financeira");
        System.out.println("16 - Ver relatório financeiro");
        System.out.println("17 - Comprar produtos com baixo estoque");
        System.out.println("18 - Registrar nova venda");
        System.out.println("19 - Listar vendas realizadas");
        System.out.println("0 - Logout");
    }

    private static void menuFuncionario() {
        System.out.println("\n===== MENU FUNCIONÁRIO =====");
        System.out.println("1 - Cadastrar produto");
        System.out.println("2 - Listar produtos");
        System.out.println("3 - Atualizar produto");
        System.out.println("4 - Remover produto");
        System.out.println("5 - Exibir dashboards");
        System.out.println("6 - Registrar nova venda");
        System.out.println("7 - Listar vendas realizadas");
        System.out.println("0 - Logout");
    }

    // === Metodos de Execução de Ações ===

    private static boolean executarAcaoAdmin(int opcao) {
        switch (opcao) {
            case 1: cadastrarProduto(); break;
            case 2: listarProdutos(); break;
            case 3: atualizarProduto(); break;
            case 4: removerProduto(); break;
            case 5: cadastrarUsuario(); break;
            case 6: listarUsuarios(); break;
            case 7: atualizarUsuarios(); break;
            case 8: removerUsuarios(); break;
            case 9: exibirDashboards(); break;
            case 10: cadastrarFornecedor(); break;
            case 11: listarFornecedores(); break;
            case 12: atualizarFornecedor(); break;
            case 13: removerFornecedor(); break;
            case 14: registrarEntrada(); break;
            case 15: registrarSaida(); break;
            case 16: verRelatorioFinanceiro(); break;
            case 17: estoqueController.comprarProdutos(scanner, 10); break;
            case 18: vendasController.realizarVenda(scanner); break;
            case 19: vendasController.listarVendas(); break;
            case 0: logout(); return false;
            default: System.out.println("Opção inválida."); break;
        }
        return true;
    }

    private static boolean executarAcaoFuncionario(int opcao) {
        switch (opcao) {
            case 1: cadastrarProduto(); break;
            case 2: listarProdutos(); break;
            case 3: atualizarProduto(); break;
            case 4: removerProduto(); break;
            case 5: exibirDashboards(); break;
            case 6: vendasController.realizarVenda(scanner); break;
            case 7: vendasController.listarVendas(); break;
            case 0: logout(); return false;
            default: System.out.println("Opção inválida."); break;
        }
        return true;
    }

    // === CRUD Produto ===
    private static void cadastrarProduto() {
        System.out.println("=== Cadastro de Produto ===");
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("Marca: ");
            String marca = scanner.nextLine();

            System.out.print("Preço: ");
            double preco = scanner.nextDouble();

            System.out.print("Quantidade: ");
            int qtd = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Lote: ");
            String lote = scanner.nextLine();

            System.out.print("Código de barras: ");
            String codigo = scanner.nextLine();

            System.out.print("Data de validade (DD-MM-YYYY): ");
            String dataValidadeStr = scanner.nextLine();
            LocalDate validade = LocalDate.parse(dataValidadeStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            System.out.print("Volume (L): ");
            int litros = scanner.nextInt();

            System.out.print("Peso (g): ");
            int peso = scanner.nextInt();
            scanner.nextLine();

            Produto p = new Produto(0, nome, marca, preco, qtd, lote, codigo, validade, litros, peso);
            estoqueController.adicionarProduto(p);

            System.out.println("Produto cadastrado com sucesso!");

        } catch (InputMismatchException | DateTimeParseException e) {
            System.out.println("Erro ao cadastrar produto: dado inválido.");
            scanner.nextLine(); // limpar buffer
        }
    }

    private static void listarProdutos() {
        List<Produto> lista = estoqueController.listarProdutos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            System.out.println("=== Lista de Produtos ===");
            for (Produto p : lista) {
                System.out.printf("ID: %d | Nome: %s | Marca: %s | Qtd: %d | Preço: R$ %.2f\n",
                        p.getId(), p.getNome(), p.getMarca(), p.getQuantidade(), p.getPreco());
            }
        }
    }

    private static void atualizarProduto() {
        System.out.print("Informe o ID do produto: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Produto p = estoqueController.buscarPorId(id);
        if (p == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Novo nome (" + p.getNome() + "): ");
        p.setNome(scanner.nextLine());

        System.out.print("Nova marca (" + p.getMarca() + "): ");
        p.setMarca(scanner.nextLine());

        System.out.print("Novo preço (" + p.getPreco() + "): ");
        p.setPreco(scanner.nextDouble());

        System.out.print("Nova quantidade (" + p.getQuantidade() + "): ");
        p.setQuantidade(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Novo lote (" + p.getLote() + "): ");
        p.setLote(scanner.nextLine());

        System.out.print("Novo código de barras (" + p.getCodigoBarras() + "): ");
        p.setCodigoBarras(scanner.nextLine());

        System.out.print("Nova data de validade (DD-MM-YYYY): ");
        String dataStr = scanner.nextLine();
        p.setDataValidade(LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        System.out.print("Novo volume (L): ");
        p.setVolumeLitros(scanner.nextInt());

        System.out.print("Novo peso (g): ");
        p.setPesoGramas(scanner.nextInt());
        scanner.nextLine();

        estoqueController.atualizarProduto(id, p);
        System.out.println("Produto atualizado.");
    }

    private static void removerProduto() {
        System.out.print("Informe o ID do produto a remover: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        estoqueController.removerProduto(id);
        System.out.println("Produto removido (se existir).");
    }

    private static Produto buscarProduto(int id) {
        return estoqueController.buscarPorId(id);
    }

    // === CRUD Usuario ===
    private static void cadastrarUsuario() {
        System.out.println("=== Cadastro de Usuário ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Login: ");
        String login = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        System.out.print("Tipo (1 = Funcionário | 2 = Administrador): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1) {
            System.out.print("Cargo: ");
            String cargo = scanner.nextLine();
            usuarioController.adicionarFuncionario(nome, login, senha, cargo);
        } else {
            usuarioController.adicionarAdministrador(nome, login, senha);
        }

        System.out.println("Usuário cadastrado.");
    }

    private static void listarUsuarios() {
        List<Usuario> lista = usuarioController.listarUsuarios();
        if (lista.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            for (Usuario u : lista) {
                String tipo = (u instanceof Administrador) ? "Administrador" : "Funcionário";
                System.out.printf("ID: %d | Nome: %s | Tipo: %s | Login: %s\n",
                        u.getId(), u.getNome(), tipo, u.getLogin());
            }
        }
    }

    private static void atualizarUsuarios() {
        System.out.print("ID do usuário: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Usuario u = usuarioController.buscarPorId(id);
        if (u == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.print("Novo nome (" + u.getNome() + "): ");
        String nome = scanner.nextLine();

        System.out.print("Novo login (" + u.getLogin() + "): ");
        String login = scanner.nextLine();

        System.out.print("Nova senha: ");
        String senha = scanner.nextLine();

        usuarioController.atualizarUsuario(id, nome, login, senha);
        System.out.println("Usuário atualizado.");
    }

    private static void removerUsuarios() {
        System.out.print("ID do usuário a remover: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        usuarioController.removerUsuario(id);
        System.out.println("Usuário removido.");
    }

    private static void exibirDashboards() {
        System.out.println("=== Dashboards dos Usuários (Polimorfismo) ===");
        for (Usuario u : usuarioController.listarUsuarios()) {
            u.visualizarDashboard();
        }

    }

    // === CRUD Fornecedores ===
    private static void cadastrarFornecedor() {
        System.out.println("=== Cadastro de Fornecedor ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();

        fornecedorController.adicionarFornecedor(nome, telefone, email, cnpj);
        System.out.println("Fornecedor cadastrado.");
    }

    private static void listarFornecedores() {
        List<Fornecedor> lista = fornecedorController.listarFornecedores();
        if (lista.isEmpty()) {
            System.out.println("Nenhum fornecedor cadastrado.");
        } else {
            for (Fornecedor f : lista) {
                System.out.printf("ID: %d | Nome: %s | CNPJ: %s | Email: %s | Telefone: %s\n",
                        f.getId(), f.getNome(), f.getCnpj(), f.getEmail(), f.getTelefone());
            }
        }
    }

    private static void atualizarFornecedor() {
        System.out.print("ID do fornecedor: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Fornecedor f = fornecedorController.buscarPorId(id);
        if (f == null) {
            System.out.println("Fornecedor não encontrado.");
            return;
        }

        System.out.print("Novo nome (" + f.getNome() + "): ");
        String nome = scanner.nextLine();

        System.out.print("Novo telefone (" + f.getTelefone() + "): ");
        String telefone = scanner.nextLine();

        System.out.print("Novo email (" + f.getEmail() + "): ");
        String email = scanner.nextLine();

        System.out.print("Novo CNPJ (" + f.getCnpj() + "): ");
        String cnpj = scanner.nextLine();

        fornecedorController.atualizarFornecedor(id, nome, telefone, email, cnpj);
        System.out.println("Fornecedor atualizado.");
    }

    private static void removerFornecedor() {
        System.out.print("ID do fornecedor a remover: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        fornecedorController.removerFornecedor(id);
        System.out.println("Fornecedor removido.");
    }

    // === CRUD Financeiro ===
    private static void registrarEntrada() {
        System.out.println("=== Registrar Entrada Financeira ===");
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Valor: ");
        float valor = scanner.nextFloat();
        scanner.nextLine();

        financeiro.registrarEntrada(valor, descricao);
        System.out.println("Entrada registrada.");
    }

    private static void registrarSaida() {
        System.out.println("=== Registrar Saída Financeira ===");
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Valor: ");
        float valor = scanner.nextFloat();
        scanner.nextLine();

        financeiro.registrarSaida(valor, descricao);
        System.out.println("Saída registrada.");
    }

    private static void verRelatorioFinanceiro() {
        financeiro.gerarRelatorio();
    }

    private static void logout() {
        usuarioLogado = null;
        System.out.println("Logout realizado com sucesso.");
    }

}
