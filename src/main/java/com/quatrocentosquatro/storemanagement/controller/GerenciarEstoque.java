package com.quatrocentosquatro.storemanagement.controller;

import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

import com.quatrocentosquatro.storemanagement.model.Produto;
import com.quatrocentosquatro.storemanagement.service.Estoque;

/**
 * Classe responsável por gerenciar o estoque de produtos
 * 
 * @author João M. Chervinski
 * @author Kaio A. Souza
 */
public class GerenciarEstoque {
    private static GerenciarEstoque instancia;// Instância singleton da classe

    private List<Produto> produtos;
    private final String ARQUIVO = "estoque.db";
    private final Estoque estoqueService = new Estoque();
    private final Financeiro financeiro = new Financeiro();
    private int nextId = 1;
    private final String caminhoLog = "src/main/java/com/quatrocentosquatro/storemanagement/logs/Estoque.log";
    private final DateTimeFormatter formataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


    /**
     * <p> Construtor da classe GerenciarEstoque.
     * <p> Inicializa a lista de produtos e carrega os dados do arquivo.
     */
    public GerenciarEstoque() {
    this.produtos = carregarProdutos();
    this.nextId = produtos.stream()
                          .mapToInt(Produto::getId)
                          .max()
                          .orElse(0) + 1;
    }

    // Método para carregar a lista de produtos de um arquivo
    // Utiliza deserialização para recuperar os objetos de produtos
    private void salvarProdutos() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            out.writeObject(produtos);
        } catch (IOException e) {
            System.out.println("Erro ao salvar produtos: " + e.getMessage());
        }
    }

    /**
     * Método para carregar a lista de produtos de um arquivo
     * Utiliza deserialização para recuperar os objetos de produtos
     * @return List<Produto> - Lista de produtos carregados do arquivo
     */
    @SuppressWarnings("unchecked")
    private List<Produto> carregarProdutos() {
        File file = new File(ARQUIVO);
        if (!file.exists())
            return new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Produto>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar produtos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

     /**
     * Usado para puxar a data e horário atual do sistema.
     * 
     * @return A data e hora atual no formato dd/MM/yyyy, hh:mm:ss
     */
    private String agora() {
        return LocalDateTime.now().format(formataHora);
    }

    /**
     * Adiciona um novo produto ao estoque e atribui um ID.
     */
    public void adicionarProduto(Produto p) {
        p.setId(nextId++);
        produtos.add(p);
        String log = "[" + agora() + "] Um novo produto com o ID " + nextId + " foi adicionado";
        salvarProdutos();
        registrarOperacoes(log);
    }

    /**
     * Busca um produto pelo seu ID
     * 
     * @param id (int) - O ID fornecido.
     * 
     * @return O produto com o ID correspondente.
     */
    public Produto buscarPorId(int id) {
        return produtos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    /**
     * Atualiza os dados de um produto existente.
     * 
     * @param id   (int)     - O ID do produto a ser atualizado.
     * @param novo (Produto) - O objeto Produto com novas informações.
     */
    public void atualizarProduto(int id, Produto novo) {
        Produto atual = buscarPorId(id);
        if (atual != null) {
            atual.setNome(novo.getNome());
            atual.setMarca(novo.getMarca());
            atual.setPreco(novo.getPreco());
            atual.setQuantidade(novo.getQuantidade());
            atual.setLote(novo.getLote());
            atual.setCodigoBarras(novo.getCodigoBarras());
            atual.setDataValidade(novo.getDataValidade());
            atual.setVolumeLitros(novo.getVolumeLitros());
            atual.setPesoGramas(novo.getPesoGramas());
        }
        String log = "[" + agora() + "] O produto de ID " + id + " foi atualizado.";
        registrarOperacoes(log);
    }

    /**
     * Remove um produto do estoque pelo ID
     * 
     * @param id (int) - O ID do produto a ser removido.
     */
    public void removerProduto(int id) {
        produtos.removeIf(p -> p.getId() == id);
        String log = "[" + agora() + "] O produto de ID " + id + " foi removido.";
        salvarProdutos();
        registrarOperacoes(log);
    }

    /** 
     * @return A lista de todos os produtos
     */
    public List<Produto> listarProdutos() {
        return produtos;
    }

    /**
     * @param limite (int) - O limite para determinar o "baixo estoque".
     * 
     * @return Produtos com quantidade abaixo do limite informado
     */
    public List<Produto> listarProdutosComBaixoEstoque(int limite) {
        return estoqueService.verificarQtdProdutos(produtos, limite);
    }

    /**
     * Gera um relatório do estoque atual
     * 
     * @return O relatório.
     */
    public String gerarRelatorioEstoque() {
        return estoqueService.gerarRelatorioEstoque(produtos);
    }

    /**
     * Permite a compra/reposição de produtos com estoque baixo
     * 
     * @param scanner       (Scanner) - Entrada do usuário.
     * @param limiteEstoque (int)     - O limite para determinar o "baixo estoque".
     */
    public void comprarProdutos(Scanner scanner, int limiteEstoque) {
        List<Produto> baixos = listarProdutosComBaixoEstoque(limiteEstoque);
        if (baixos.isEmpty()) {
            System.out.println("Nenhum produto com estoque baixo.");
            return;
        }

        System.out.println("=== Reposição de Estoque ===");
        float totalCompra = 0;

        // Para cada produto com estoque baixo, solicita quantidade e preço de compra
        for (Produto p : baixos) {
            System.out.printf("ID: %d | Nome: %s | Qtd Atual: %d\n", p.getId(), p.getNome(), p.getQuantidade());
            System.out.print("Quantidade para reabastecer: ");
            int qtdNova = scanner.nextInt(); scanner.nextLine();

            System.out.print("Preço de compra unitário: ");
            float precoUnitario = scanner.nextFloat(); scanner.nextLine();

            // Atualiza a quantidade do produto e soma ao total da compra
            p.setQuantidade(p.getQuantidade() + qtdNova);
            totalCompra += precoUnitario * qtdNova;

            System.out.println("Produto atualizado: " + p.getNome());
        }

        // Registra a compra no financeiro e exibe o total gasto
        financeiro.comprarProdutos(totalCompra);
        System.out.printf("Total gasto: R$ %.2f\n", totalCompra);

        String log = "[" + agora() + "] Produtos foram comprados num total de " + totalCompra;
        salvarProdutos();
        registrarOperacoes(log);
    }
 
    /**
     * Registra as operações feitas na classe em seu log
     * 
     * @param acao (String) - A operação feita.
     */
    private void registrarOperacoes(String acao) {
        try {
            FileWriter escreverAcao = new FileWriter(caminhoLog, true); // Modo append = true
            escreverAcao.append(acao).append("\n");
            escreverAcao.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível registrar ação no financeiro:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static GerenciarEstoque getInstancia() {
        if (instancia == null) {instancia = new GerenciarEstoque();}
        return instancia;
    }
}
