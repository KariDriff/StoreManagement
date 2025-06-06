package com.quatrocentosquatro.storemanagement.controller;

import com.quatrocentosquatro.storemanagement.model.Produto;
import com.quatrocentosquatro.storemanagement.service.Estoque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** 
 * Controller para gerenciar o estoque de produtos
 * 
 * @author João M. Chervinski
 */
public class GerenciarEstoque {
    private final List<Produto> produtos = new ArrayList<>();
    private final Estoque estoqueService = new Estoque();
    private final Financeiro financeiro = new Financeiro();
    private int nextId = 1;


    /**
     * Método para adicionar um novo produto ao estoque
     * Atribui um ID único ao produto e o adiciona à lista de produtos
     * 
     * @param p (Produto) - O produto a ser adicionado.
     */
    public void adicionarProduto(Produto p) {
        p.setId(nextId++);
        produtos.add(p);
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
    }

    // Método para remover um produto do estoque
    // Busca o produto pelo ID e o remove da lista de produtos
    public void removerProduto(int id) {
        produtos.removeIf(p -> p.getId() == id);
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
        }

        // Registrar saída financeira
        financeiro.comprarProdutos(totalCompra);
        System.out.printf("Total gasto: R$ %.2f\n", totalCompra);
    }
}