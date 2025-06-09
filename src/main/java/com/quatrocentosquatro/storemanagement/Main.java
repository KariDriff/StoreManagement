package com.quatrocentosquatro.storemanagement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.quatrocentosquatro.storemanagement.controller.GerenciarEstoque;
import com.quatrocentosquatro.storemanagement.controller.GerenciarFornecedores;
import com.quatrocentosquatro.storemanagement.controller.GerenciarUsuarios;
import com.quatrocentosquatro.storemanagement.controller.ProcessamentoDeVendas;
import com.quatrocentosquatro.storemanagement.controller.Financeiro;
import com.quatrocentosquatro.storemanagement.model.Administrador;
import com.quatrocentosquatro.storemanagement.model.Despesa;
import com.quatrocentosquatro.storemanagement.model.Fornecedor;
import com.quatrocentosquatro.storemanagement.model.Produto;
import com.quatrocentosquatro.storemanagement.model.Usuario;
import com.quatrocentosquatro.storemanagement.views.TelaLogin;

/**
 * Classe principal que inicia o sistema de gerenciamento de loja.
 * Permite login, exibe menus e executa ações baseadas no tipo de usuário.
 * 
 * @author João M. Chervinski
 * @author Kaio A. Souza
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in); // Scanner para entrada de dados do usuário
    private static Financeiro financeiro = new Financeiro(); // Controlador para funcionalidades financeiras
    private static GerenciarEstoque estoqueController = new GerenciarEstoque(); // Controlador para gerenciar o estoque
    private static ProcessamentoDeVendas vendasController = new ProcessamentoDeVendas(estoqueController, financeiro); // Controlador para processar vendas
    private static GerenciarUsuarios usuarioController = new GerenciarUsuarios(); // Controlador para gerenciar usuários
    private static GerenciarFornecedores fornecedorController = new GerenciarFornecedores(); // Controlador para gerenciar fornecedores
    private static Usuario usuarioLogado = null; // Usuário atualmente logado no sistema

    /**
     * Metodo de login
     */
    private static void login() {

        usuarioController.adicionarAdministrador("Admin", "adm", "1234"); // Adiciona um administrador padrão para facilitar o login

        System.out.println("=== LOGIN ===");
        System.out.print("Login: ");
        String login = scanner.nextLine(); // Lê o login do usuário

        System.out.print("Senha: ");
        String senha = scanner.nextLine(); // Lê a senha do usuário

        usuarioLogado = usuarioController.autenticar(login, senha); // Autentica o usuário

        if (usuarioLogado != null) {
            System.out.println("Login realizado com sucesso.");
            System.out.println("Olá, " + usuarioLogado.getNome() + "!");
        } else {
            System.out.println("Login inválido.");
        }
    }

    /**
     * Livrai-nos do mal, a main().
     * 
     * @param args
     */
    public static void main(String[] args) {
        TelaLogin.main(args);

        /*
         * O código abaixo não está sendo usado pois seria o sistema funcionando sem uma
         * interface, no caso de não ser terminada a tempo.
         */
        
        /*
        while (usuarioLogado == null) {
            login(); // Mantém o loop até que o usuário faça login
        }

        boolean executando = true;
        while (executando) {
            if (usuarioLogado instanceof Administrador) {
                menuAdministrador(); // Exibe o menu de administrador se o usuário for um administrador
            } else {
                menuFuncionario(); // Exibe o menu de funcionário se o usuário for um funcionário
            }

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt(); // Lê a opção escolhida pelo usuário
            scanner.nextLine(); // Limpa o buffer do scanner

            if (usuarioLogado instanceof Administrador) {
                executando = executarAcaoAdmin(opcao); // Executa a ação correspondente à opção escolhida pelo administrador
            } else {
                executando = executarAcaoFuncionario(opcao); // Executa a ação correspondente à opção escolhida pelo funcionário
            }
        }
        */
    }

    // === Metodos de Menu ===
    /**
     * Exibe o menu de opções para o administrador.
     * Permite ao administrador gerenciar produtos, usuários, fornecedores e finanças.
     */
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
        System.out.println("16 - Ver despesas pendentes");
        System.out.println("17 - Ver relatório financeiro");
        System.out.println("18 - Comprar produtos com baixo estoque");
        System.out.println("19 - Registrar nova venda");
        System.out.println("20 - Listar vendas realizadas");
        System.out.println("0 - Logout");
    }

    /**
     * Exibe o menu de opções para o funcionário.
     * Permite ao funcionário gerenciar produtos e registrar vendas.
     */
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

    /**
     * Executa a ação correspondente à opção escolhida pelo usuário.
     * As ações variam dependendo se o usuário é um administrador ou um funcionário.
     */
    
    private static boolean executarAcaoAdmin(int opcao) {
        switch (opcao) {
            case 1: cadastrarProduto(); break; // Cadastra um novo produto
            case 2: listarProdutos(); break; // Lista todos os produtos
            case 3: atualizarProduto(); break; // Atualiza um produto existente
            case 4: removerProduto(); break; // Remove um produto
            case 5: cadastrarUsuario(); break; // Cadastra um novo usuário
            case 6: listarUsuarios(); break; // Lista todos os usuários
            case 7: atualizarUsuarios(); break; // Atualiza um usuário existente
            case 8: removerUsuarios(); break; // Remove um usuário
            case 9: exibirDashboards(); break; // Exibe os dashboards
            case 10: cadastrarFornecedor(); break; // Cadastra um novo fornecedor
            case 11: listarFornecedores(); break; // Lista todos os fornecedores
            case 12: atualizarFornecedor(); break; // Atualiza um fornecedor existente
            case 13: removerFornecedor(); break; // Remove um fornecedor
            case 14: registrarEntrada(); break; // Registra uma entrada financeira
            case 15: registrarSaida(); break; // Registra uma saída financeira
            case 16: verDespesas(); break; // ver despesas para pagá-las.
            case 17: verRelatorioFinanceiro(); break; // Visualiza o relatório financeiro
            case 18: estoqueController.comprarProdutos(scanner, 10); break; // Compra produtos com baixo estoque
            case 19: vendasController.realizarVenda(scanner); break; // Realiza uma venda
            case 20: vendasController.listarVendas(); break; // Lista todas as vendas
            case 0: logout(); return false; // Faz logout e encerra a execução
            default: System.out.println("Opção inválida."); break; // Opção inválida
        }
        return true;
    }

    private static boolean executarAcaoFuncionario(int opcao) {
        switch (opcao) {
            case 1: cadastrarProduto(); break; // Cadastra um novo produto
            case 2: listarProdutos(); break; // Lista todos os produtos
            case 3: atualizarProduto(); break; // Atualiza um produto existente
            case 4: removerProduto(); break; // Remove um produto
            case 5: exibirDashboards(); break; // Exibe os dashboards
            case 6: vendasController.realizarVenda(scanner); break; // Realiza uma venda
            case 7: vendasController.listarVendas(); break; // Lista todas as vendas
            case 0: logout(); return false; // Faz logout e encerra a execução
            default: System.out.println("Opção inválida."); break; // Opção inválida
        }
        return true;
    }

    // === CRUD Produto ===
    /*
     * Métodos para gerenciar produtos no estoque.
     * Permite cadastrar, listar, atualizar e remover produtos.
     */

    private static void cadastrarProduto() {
        System.out.println("=== Cadastro de Produto ===");
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine(); // Lê o nome do produto

            System.out.print("Marca: ");
            String marca = scanner.nextLine(); // Lê a marca do produto

            System.out.print("Preço: ");
            double preco = scanner.nextDouble(); // Lê o preço do produto

            System.out.print("Quantidade: ");
            int qtd = scanner.nextInt(); // Lê a quantidade do produto
            scanner.nextLine(); // Limpa o buffer

            System.out.print("Lote: ");
            String lote = scanner.nextLine(); // Lê o lote do produto

            System.out.print("Código de barras: ");
            String codigo = scanner.nextLine(); // Lê o código de barras do produto

            System.out.print("Data de validade (DD-MM-YYYY): ");
            String dataValidadeStr = scanner.nextLine(); // Lê a data de validade do produto
            LocalDate validade = LocalDate.parse(dataValidadeStr, DateTimeFormatter.ofPattern("dd-MM-yyyy")); // Converte a string para LocalDate

            System.out.print("Volume (L): ");
            int litros = scanner.nextInt(); // Lê o volume em litros

            System.out.print("Peso (g): ");
            int peso = scanner.nextInt(); // Lê o peso em gramas
            scanner.nextLine(); // Limpa o buffer

            Produto p = new Produto(0, nome, marca, preco, qtd, lote, codigo, validade, litros, peso); // Cria um novo produto
            estoqueController.adicionarProduto(p); // Adiciona o produto ao estoque

            System.out.println("Produto cadastrado com sucesso!");

        } catch (InputMismatchException | DateTimeParseException e) {
            System.out.println("Erro ao cadastrar produto: dado inválido.");
            scanner.nextLine(); // limpar buffer
        }
    }

    private static void listarProdutos() {
        List<Produto> lista = estoqueController.listarProdutos(); // Obtém a lista de produtos
        if (lista.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            System.out.println("=== Lista de Produtos ===");
            for (Produto p : lista) {
                System.out.printf("ID: %d | Nome: %s | Marca: %s | Qtd: %d | Preço: R$ %.2f\n | Data de Validade: %s | Lote: %s | Código de Barras: %s | Volume: %dL | Peso: %dg\n",
                        p.getId(), p.getNome(), p.getMarca(), p.getQuantidade(), p.getPreco());
            }
        }
    }

    private static void atualizarProduto() {
        System.out.print("Informe o ID do produto: ");
        int id = scanner.nextInt(); // Lê o ID do produto a ser atualizado
        scanner.nextLine(); // Limpa o buffer

        Produto p = estoqueController.buscarPorId(id); // Busca o produto pelo ID
        if (p == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Novo nome (" + p.getNome() + "): ");
        p.setNome(scanner.nextLine()); // Lê o novo nome

        System.out.print("Nova marca (" + p.getMarca() + "): ");
        p.setMarca(scanner.nextLine()); // Lê a nova marca

        System.out.print("Novo preço (" + p.getPreco() + "): ");
        p.setPreco(scanner.nextDouble()); // Lê o novo preço

        System.out.print("Nova quantidade (" + p.getQuantidade() + "): ");
        p.setQuantidade(scanner.nextInt()); // Lê a nova quantidade
        scanner.nextLine(); // Limpa o buffer

        System.out.print("Novo lote (" + p.getLote() + "): ");
        p.setLote(scanner.nextLine()); // Lê o novo lote

        System.out.print("Novo código de barras (" + p.getCodigoBarras() + "): ");
        p.setCodigoBarras(scanner.nextLine()); // Lê o novo código de barras

        System.out.print("Nova data de validade (DD-MM-YYYY): ");
        String dataStr = scanner.nextLine(); // Lê a nova data de validade
        p.setDataValidade(LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"))); // Converte a string para LocalDate

        System.out.print("Novo volume (L): ");
        p.setVolumeLitros(scanner.nextInt()); // Lê o novo volume

        System.out.print("Novo peso (g): ");
        p.setPesoGramas(scanner.nextInt()); // Lê o novo peso
        scanner.nextLine(); // Limpa o buffer

        estoqueController.atualizarProduto(id, p); // Atualiza o produto no estoque
        System.out.println("Produto atualizado.");
    }

    private static void removerProduto() {
        System.out.print("Informe o ID do produto a remover: ");
        int id = scanner.nextInt(); // Lê o ID do produto a ser removido
        scanner.nextLine(); // Limpa o buffer
        estoqueController.removerProduto(id); // Remove o produto do estoque
        System.out.println("Produto removido (se existir).");
    }

    // === CRUD Usuario ===
    /*
     * Métodos para gerenciar usuários do sistema.
     * Permite cadastrar, listar, atualizar e remover usuários.
     * Exibe dashboards personalizados para cada tipo de usuário.
     */

    private static void cadastrarUsuario() {
        System.out.println("=== Cadastro de Usuário ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine(); // Lê o nome do usuário

        System.out.print("Login: ");
        String login = scanner.nextLine(); // Lê o login do usuário

        System.out.print("Senha: ");
        String senha = scanner.nextLine(); // Lê a senha do usuário

        System.out.print("Tipo (1 = Funcionário | 2 = Administrador): ");
        int tipo = scanner.nextInt(); // Lê o tipo de usuário
        scanner.nextLine(); // Limpa o buffer

        if (tipo == 1) {
            System.out.print("Cargo: ");
            String cargo = scanner.nextLine(); // Lê o cargo do funcionário
            usuarioController.adicionarFuncionario(nome, login, senha, cargo); // Adiciona um funcionário
        } else {
            usuarioController.adicionarAdministrador(nome, login, senha); // Adiciona um administrador
        }

        System.out.println("Usuário cadastrado.");
    }

    private static void listarUsuarios() {
        List<Usuario> lista = usuarioController.listarUsuarios(); // Obtém a lista de usuários
        if (lista.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            for (Usuario u : lista) {
                String tipo = (u instanceof Administrador) ? "Administrador" : "Funcionário"; // Determina o tipo de usuário
                System.out.printf("ID: %d | Nome: %s | Tipo: %s | Login: %s\n",
                        u.getId(), u.getNome(), tipo, u.getLogin());
            }
        }
    }

    private static void atualizarUsuarios() {
        System.out.print("ID do usuário: ");
        int id = scanner.nextInt(); // Lê o ID do usuário a ser atualizado
        scanner.nextLine(); // Limpa o buffer

        Usuario u = usuarioController.buscarPorId(id); // Busca o usuário pelo ID
        if (u == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.print("Novo nome (" + u.getNome() + "): ");
        String nome = scanner.nextLine(); // Lê o novo nome

        System.out.print("Novo login (" + u.getLogin() + "): ");
        String login = scanner.nextLine(); // Lê o novo login

        System.out.print("Nova senha: ");
        String senha = scanner.nextLine(); // Lê a nova senha

        usuarioController.atualizarUsuario(id, nome, login, senha); // Atualiza o usuário
        System.out.println("Usuário atualizado.");
    }

    private static void removerUsuarios() {
        System.out.print("ID do usuário a remover: ");
        int id = scanner.nextInt(); // Lê o ID do usuário a ser removido
        scanner.nextLine(); // Limpa o buffer

        usuarioController.removerUsuario(id); // Remove o usuário
        System.out.println("Usuário removido.");
    }

    // === Exibir Dashboards ===
    /*
     * Exibe os dashboards personalizados para cada tipo de usuário.
     * Utiliza polimorfismo para chamar o método de visualização do dashboard.
     */

    private static void exibirDashboards() {
        System.out.println("=== Dashboards dos Usuários (Polimorfismo) ===");
        for (Usuario u : usuarioController.listarUsuarios()) {
            u.visualizarDashboard(); // Chama o método para visualizar o dashboard
        }

    }

    // === CRUD Fornecedores ===
    /*
     * Métodos para gerenciar fornecedores do sistema.
     * Permite cadastrar, listar, atualizar e remover fornecedores.
     */

    private static void cadastrarFornecedor() {
        System.out.println("=== Cadastro de Fornecedor ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine(); // Lê o nome do fornecedor

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine(); // Lê o telefone do fornecedor

        System.out.print("Email: ");
        String email = scanner.nextLine(); // Lê o email do fornecedor

        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine(); // Lê o CNPJ do fornecedor

        fornecedorController.adicionarFornecedor(nome, telefone, email, cnpj); // Adiciona o fornecedor
        System.out.println("Fornecedor cadastrado.");
    }

    private static void listarFornecedores() {
        List<Fornecedor> lista = fornecedorController.listarFornecedores(); // Obtém a lista de fornecedores
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
        int id = scanner.nextInt(); // Lê o ID do fornecedor a ser atualizado
        scanner.nextLine(); // Limpa o buffer

        Fornecedor f = fornecedorController.buscarPorId(id); // Busca o fornecedor pelo ID
        if (f == null) {
            System.out.println("Fornecedor não encontrado.");
            return;
        }

        System.out.print("Novo nome (" + f.getNome() + "): ");
        String nome = scanner.nextLine(); // Lê o novo nome

        System.out.print("Novo telefone (" + f.getTelefone() + "): ");
        String telefone = scanner.nextLine(); // Lê o novo telefone

        System.out.print("Novo email (" + f.getEmail() + "): ");
        String email = scanner.nextLine(); // Lê o novo email

        System.out.print("Novo CNPJ (" + f.getCnpj() + "): ");
        String cnpj = scanner.nextLine(); // Lê o novo CNPJ

        fornecedorController.atualizarFornecedor(id, nome, telefone, email, cnpj); // Atualiza o fornecedor
        System.out.println("Fornecedor atualizado.");
    }

    private static void removerFornecedor() {
        System.out.print("ID do fornecedor a remover: ");
        int id = scanner.nextInt(); // Lê o ID do fornecedor a ser removido
        scanner.nextLine(); // Limpa o buffer

        fornecedorController.removerFornecedor(id); // Remove o fornecedor
        System.out.println("Fornecedor removido.");
    }

    // === CRUD Financeiro ===
    /*
     * Métodos para gerenciar as finanças do sistema.
     * Permite registrar entradas e saídas financeiras, além de gerar relatórios.
     */
    
    private static void registrarEntrada() {
        System.out.println("=== Registrar Entrada Financeira ===");
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine(); // Lê a descrição da entrada
        System.out.print("Valor: ");
        float valor = scanner.nextFloat(); // Lê o valor da entrada
        scanner.nextLine(); // Limpa o buffer

        financeiro.registrarEntradaUser(valor, descricao); // Registra a entrada financeira
        System.out.println("Entrada registrada.");
    }

    private static void registrarSaida() {
        System.out.println("=== Registrar Saída Financeira ===");
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine(); // Lê a descrição da saída
        System.out.print("Valor: ");
        float valor = scanner.nextFloat(); // Lê o valor da saída
        scanner.nextLine(); // Limpa o buffer
        System.out.println("Esta saída já foi paga? (S/N)");
        char resp = scanner.next().charAt(0);

        boolean isPaga = false;
        if (resp == 'S' || resp == 's') {isPaga = true;}
        else if (resp == 'N' || resp == 'n') {isPaga = false;}

        financeiro.registrarSaidaUser(valor, isPaga, descricao); // Registra a saída financeira
        System.out.println("Saída registrada.");
    }

    /**
     * Lista as despesas.
     * 
     * @see com.quatrocentosquatro.storemanagement.controller.Financeiro.java
     */
    private static void verDespesas() {
        Despesa d = new Despesa();
        System.out.println("=== Lista de despesas ===");
        d.listarDespesas();
    }

    /**
     * Gera o relatório da classe financeiro na área de trabalho.
     */
    private static void verRelatorioFinanceiro() {financeiro.gerarRelatorio();}

    // === Logout ===
    /**
     * Realiza o logout do usuário atual e reinicia o sistema.
     * Limpa o usuário logado e exibe uma mensagem de sucesso.
     */
    private static void logout() {
        usuarioLogado = null; // Limpa o usuário logado
        System.out.println("Logout realizado com sucesso.");
        main(null); // Reinicia o sistema
    }

}
