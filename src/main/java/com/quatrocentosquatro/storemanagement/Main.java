package main.java.com.quatrocentosquatro.storemanagement;

import model.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import main.java.com.quatrocentosquatro.storemanagement.model.Administrador;
import main.java.com.quatrocentosquatro.storemanagement.model.Fornecedor;
import main.java.com.quatrocentosquatro.storemanagement.model.Funcionario;
import main.java.com.quatrocentosquatro.storemanagement.model.Produto;
import main.java.com.quatrocentosquatro.storemanagement.model.Usuario;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Produto> produtos = new ArrayList<>();
    private static List<Usuario> usuarios = new ArrayList<>();
	private static List<Fornecedor> fornecedores = new ArrayList<>();
	private static int nextFornecedorId = 1;
    private static int nextProdutoId = 1;
    private static int nextUsuarioId = 1;

    public static void main(String[] args) {
        boolean executando = true;

        while (executando) {
            System.out.println("\n===== SISTEMA DE GERENCIAMENTO DE SUPERMERCADO =====");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Atualizar produto");
            System.out.println("4 - Remover produto");
            System.out.println("5 - Cadastrar funcionário/admin");
            System.out.println("6 - Listar usuários");
			System.out.println("7 - Atualizar usuários");
			System.out.println("8 - Remover usuários");
            System.out.println("9 - Exibir dashboards dos usuários"); // Polimorfismo
			System.out.println("10 - Cadastrar fornecedor");
			System.out.println("11 - Listar fornecedores");
			System.out.println("12 - Atualizar fornecedor");
			System.out.println("13 - Remover fornecedor");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1 -> cadastrarProduto();
                case 2 -> listarProdutos();
                case 3 -> atualizarProduto();
                case 4 -> removerProduto();
                case 5 -> cadastrarUsuario();
                case 6 -> listarUsuarios();
				case 7 -> atualizarUsuarios();
				case 8 -> removerUsuarios();
                case 9 -> exibirDashboards();
				case 10 -> cadastrarFornecedor();
				case 11 -> listarFornecedores();
				case 12 -> atualizarFornecedor();
				case 13 -> removerFornecedor();
                case 0 -> {
                    System.out.println("Encerrando o sistema...");
                    executando = false;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
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
        int qtd = scanner.nextInt(); scanner.nextLine();

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
        int peso = scanner.nextInt(); scanner.nextLine();

        Produto p = new Produto(nextProdutoId++, nome, marca, preco, qtd, lote, codigo, validade, litros, peso);
        produtos.add(p);

        System.out.println("Produto cadastrado com sucesso!");

    } catch (InputMismatchException e) {
        System.out.println("Erro: Tipo de dado inválido (ex: letras onde deveriam ser números). Cadastro cancelado.");
        scanner.nextLine(); // limpa buffer restante
    } catch (DateTimeParseException e) {
        System.out.println("Erro: Data inválida. Use o formato correto (DD-MM-YYYY). Cadastro cancelado.");
    } catch (Exception e) {
        System.out.println("Erro inesperado: " + e.getMessage());
    }
}

    private static void listarProdutos() {
        System.out.println("=== Lista de Produtos ===");
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            for (Produto p : produtos) {
                System.out.printf("ID: %d | %s (%s) | R$ %.2f | Estoque: %d | Validade: %s | Volume: %dL | Peso: %dg | Lote: %s | Código: %s\n",
                        p.getId(), p.getNome(), p.getMarca(), p.getPreco(), p.getQuantidade(),
                        p.getDataValidade(), p.getVolumeLitros(), p.getPesoGramas(), p.getLote(), p.getCodigoBarras());
            }
        }
    }

    private static void atualizarProduto() {
    System.out.println("=== Atualização de Produto ===");

    try {
        System.out.print("Informe o ID do produto a atualizar: ");
        int id = scanner.nextInt(); scanner.nextLine();

        Produto p = buscarProduto(id);
        if (p == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Novo nome (" + p.getNome() + "): ");
        String novoNome = scanner.nextLine();

        System.out.print("Nova marca (" + p.getMarca() + "): ");
        String novaMarca = scanner.nextLine();

        System.out.print("Novo preço (" + p.getPreco() + "): ");
        double novoPreco = scanner.nextDouble();

        System.out.print("Nova quantidade (" + p.getQuantidade() + "): ");
        int novaQuantidade = scanner.nextInt(); scanner.nextLine();

        // Se tudo correu bem, atualiza os dados
        p.setNome(novoNome);
        p.setMarca(novaMarca);
        p.setPreco(novoPreco);
        p.setQuantidade(novaQuantidade);

        System.out.println("Produto atualizado com sucesso!");

    } catch (InputMismatchException e) {
        System.out.println("Erro: Dado inválido digitado (ex: texto onde deveria ser número). Atualização cancelada.");
        scanner.nextLine(); // limpa buffer
    } catch (Exception e) {
        System.out.println("Erro inesperado: " + e.getMessage());
    }
}

    private static void removerProduto() {
        System.out.print("Informe o ID do produto a remover: ");
        int id = scanner.nextInt(); scanner.nextLine();
        Produto p = buscarProduto(id);
        if (p != null) {
            produtos.remove(p);
            System.out.println("Produto removido.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static Produto buscarProduto(int id) {
        return produtos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    // === CRUD Usuario ===
    private static void cadastrarUsuario() {
    System.out.println("=== Cadastro de Usuário ===");

    try {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Login: ");
        String login = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        System.out.print("Tipo (1 = Funcionário | 2 = Administrador): ");
        int tipo = scanner.nextInt(); scanner.nextLine();

        Usuario u;

        if (tipo == 1) {
            System.out.print("Cargo: ");
            String cargo = scanner.nextLine();
            u = new Funcionario(nextUsuarioId++, nome, login, senha, cargo);
        } else if (tipo == 2) {
            u = new Administrador(nextUsuarioId++, nome, login, senha);
        } else {
            System.out.println("Tipo inválido. Cadastro cancelado.");
            return;
        }

        usuarios.add(u);
        System.out.println("Usuário cadastrado com sucesso!");

    } catch (InputMismatchException e) {
        System.out.println("Erro: Tipo de dado inválido. Cadastro cancelado.");
        scanner.nextLine(); // limpa o buffer
    } catch (Exception e) {
        System.out.println("Erro inesperado: " + e.getMessage());
    }
}


    private static void listarUsuarios() {
        System.out.println("=== Lista de Usuários ===");
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            for (Usuario u : usuarios) {
                System.out.printf("ID: %d | Nome: %s | Login: %s | Tipo: %s\n",
                        u.getId(), u.getNome(), u.getLogin(),
                        (u instanceof Administrador) ? "Administrador" : "Funcionário");
            }
        }
    }

	private static void atualizarUsuario() {
    System.out.println("=== Atualização de Usuário ===");

    try {
        System.out.print("Informe o ID do usuário: ");
        int id = scanner.nextInt(); scanner.nextLine();

        Usuario u = buscarUsuario(id);
        if (u == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.print("Novo nome (" + u.getNome() + "): ");
        String novoNome = scanner.nextLine();

        System.out.print("Novo login (" + u.getLogin() + "): ");
        String novoLogin = scanner.nextLine();

        System.out.print("Nova senha: ");
        String novaSenha = scanner.nextLine();

        // Se for Funcionário, atualiza cargo
        String novoCargo = null;
        if (u instanceof Funcionario funcionario) {
            System.out.print("Novo cargo (" + funcionario.getCargo() + "): ");
            novoCargo = scanner.nextLine();

            if (novoCargo.isBlank()) {
                System.out.println("Cargo não pode ser vazio. Atualização cancelada.");
                return;
            }

            funcionario.setCargo(novoCargo);
        }

        // Validação básica
        if (novoNome.isBlank() || novoLogin.isBlank() || novaSenha.isBlank()) {
            System.out.println("Nenhum dos campos pode ser vazio. Atualização cancelada.");
            return;
        }

        // Aplicar alterações
        u.setNome(novoNome);
        u.setLogin(novoLogin);
        u.setSenha(novaSenha);

        System.out.println("Usuário atualizado com sucesso!");

    } catch (InputMismatchException e) {
        System.out.println("Erro: Entrada inválida. Atualização cancelada.");
        scanner.nextLine(); // limpar buffer
    } catch (Exception e) {
        System.out.println("Erro inesperado: " + e.getMessage());
    }
}

private static void removerUsuario() {
    System.out.println("=== Remoção de Usuário ===");

    try {
        System.out.print("Informe o ID do usuário: ");
        int id = scanner.nextInt(); scanner.nextLine();

        Usuario u = buscarUsuario(id);
        if (u == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        usuarios.remove(u);
        System.out.println("Usuário removido com sucesso!");

    } catch (InputMismatchException e) {
        System.out.println("Erro: ID inválido. Use apenas números.");
        scanner.nextLine(); // limpa buffer
    } catch (Exception e) {
        System.out.println("Erro inesperado: " + e.getMessage());
    }
}



    private static void exibirDashboards() {
        System.out.println("=== Dashboards dos Usuários (Polimorfismo) ===");
        for (Usuario u : usuarios) {
            u.visualizarDashboard();
        }
    }

	 // === CRUD Fornecedores ===
	private static void cadastrarFornecedor() {
    System.out.println("=== Cadastro de Fornecedor ===");

    try {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();

        if (nome.isBlank() || telefone.isBlank() || email.isBlank() || cnpj.isBlank()) {
            System.out.println("rro: Todos os campos são obrigatórios. Cadastro cancelado.");
            return;
        }

        Fornecedor f = new Fornecedor(nextFornecedorId++, nome, telefone, email, cnpj);
        fornecedores.add(f);

        System.out.println("Fornecedor cadastrado com sucesso!");

    } catch (InputMismatchException e) {
        System.out.println("Erro: Tipo de dado inválido. Cadastro cancelado.");
        scanner.nextLine(); // limpa buffer
    } catch (Exception e) {
        System.out.println("Erro inesperado: " + e.getMessage());
    }
}

private static void atualizarFornecedor() {
    System.out.println("=== Atualização de Fornecedor ===");

    try {
        System.out.print("Informe o ID do fornecedor: ");
        int id = scanner.nextInt(); scanner.nextLine();

        Fornecedor f = buscarFornecedor(id);
        if (f == null) {
            System.out.println("Fornecedor não encontrado.");
            return;
        }

        System.out.print("Novo nome (" + f.getNome() + "): ");
        String novoNome = scanner.nextLine();

        System.out.print("Novo telefone (" + f.getTelefone() + "): ");
        String novoTelefone = scanner.nextLine();

        System.out.print("Novo email (" + f.getEmail() + "): ");
        String novoEmail = scanner.nextLine();

        // Validação básica: não permitir campos vazios
        if (novoNome.isBlank() || novoTelefone.isBlank() || novoEmail.isBlank()) {
            System.out.println("Erro: Nenhum dos campos pode ser vazio. Atualização cancelada.");
            return;
        }

        // Atualiza os campos
        f.setNome(novoNome);
        f.setTelefone(novoTelefone);
        f.setEmail(novoEmail);

        System.out.println("Fornecedor atualizado com sucesso!");

    } catch (InputMismatchException e) {
        System.out.println("rro: Tipo de dado inválido. Atualização cancelada.");
        scanner.nextLine(); // limpa buffer
    } catch (Exception e) {
        System.out.println("Erro inesperado: " + e.getMessage());
    }
}


private static void removerFornecedor() {
    System.out.print("Informe o ID do fornecedor: ");
    int id = scanner.nextInt(); scanner.nextLine();
    Fornecedor f = buscarFornecedor(id);

    if (f != null) {
        f.setAtivo(false);
        System.out.println("Fornecedor marcado como inativo.");
    } else {
        System.out.println("Fornecedor não encontrado.");
    }
}

private static Fornecedor buscarFornecedor(int id) {
    return fornecedores.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
}

}
