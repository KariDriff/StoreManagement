package com.quatrocentosquatro.storemanagement.controller;

import com.quatrocentosquatro.storemanagement.model.Produto;
import com.quatrocentosquatro.storemanagement.service.Estoque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

/** 
 * Controller para gerenciar o estoque de produtos
 * 
 * @author João M. Chervinski
 */

public class GerenciarEstoque {
    private List<Produto> produtos;
    private final String ARQUIVO = "estoque.db";
    private final Estoque estoqueService = new Estoque();
    private final Financeiro financeiro = new Financeiro();
    private int nextId = 1;

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
     * Método para adicionar um novo produto ao estoque
     * Atribui um ID único ao produto e o adiciona à lista de produtos
     * @param p (Produto) - O produto a ser adicionado.
     */
    public void adicionarProduto(Produto p) {
        p.setId(nextId++);
        produtos.add(p);
        salvarProdutos();
    }

    // Método para buscar um produto pelo ID
    // Retorna o produto correspondente ou null se não encontrado
    public Produto buscarPorId(int id) {
        return produtos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    // Método para atualizar as informações de um produto existente
    // Busca o produto pelo ID e atualiza seus atributos com os novos valores
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
        salvarProdutos();
    }

    // Método para remover um produto do estoque
    // Busca o produto pelo ID e o remove da lista de produtos
    public void removerProduto(int id) {
        produtos.removeIf(p -> p.getId() == id);
        salvarProdutos();
    }

    // Métodos para listar produtos
    // Retorna a lista completa de produtos no estoque
    public List<Produto> listarProdutos() {
        return produtos;
    }

    // Método para listar produtos com estoque baixo
    // Recebe um limite e retorna uma lista de produtos cuja quantidade é menor ou igual ao limite
    public List<Produto> listarProdutosComBaixoEstoque(int limite) {
        return estoqueService.verificarQtdProdutos(produtos, limite);
    }

    // Método para gerar um relatório do estoque
    // Utiliza o serviço de estoque para formatar e retornar um relatório dos produtos
    public String gerarRelatorioEstoque() {
        return estoqueService.gerarRelatorioEstoque(produtos);
    }

    // Integração com Financeiro
    public void comprarProdutos(Scanner scanner, int limiteEstoque) {
        List<Produto> baixos = listarProdutosComBaixoEstoque(limiteEstoque);
        if (baixos.isEmpty()) {
            System.out.println("Nenhum produto com estoque baixo.");
            return;
        }

        System.out.println("=== Reposição de Estoque ===");
        float totalCompra = 0;

        for (Produto p : baixos) {
            System.out.printf("ID: %d | Nome: %s | Qtd Atual: %d\n", p.getId(), p.getNome(), p.getQuantidade());
            System.out.print("Quantidade para reabastecer: ");
            int qtdNova = scanner.nextInt(); scanner.nextLine();

            System.out.print("Preço de compra unitário: ");
            float precoUnitario = scanner.nextFloat(); scanner.nextLine();

            p.setQuantidade(p.getQuantidade() + qtdNova);
            totalCompra += precoUnitario * qtdNova;

            System.out.println("Produto atualizado: " + p.getNome());
            System.out.printf("Nova quantidade: %d | Preço unitário: R$ %.2f\n", p.getQuantidade(), precoUnitario);
            salvarProdutos();
        }

        // Registrar saída financeira
        financeiro.comprarProdutos(totalCompra);
        System.out.printf("Total gasto: R$ %.2f\n", totalCompra);
    }
}