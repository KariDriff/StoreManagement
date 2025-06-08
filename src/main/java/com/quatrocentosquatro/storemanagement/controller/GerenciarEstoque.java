package com.quatrocentosquatro.storemanagement.controller;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.quatrocentosquatro.storemanagement.model.Produto;
import com.quatrocentosquatro.storemanagement.service.Estoque;

/** 
 * Controller para gerenciar o estoque de produtos
 * 
 * @author João M. Chervinski
 */
public class GerenciarEstoque {
    private List<Produto> produtos;
    private final String ARQUIVO = "src/main/java/com/quatrocentosquatro/storemanagement/database/estoque.db";
    private final Estoque estoqueService = new Estoque();
    private final Financeiro financeiro = new Financeiro();
    private int nextId = 1;
    private final DateTimeFormatter formataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private final String caminhoLog = "src/main/java/com/quatrocentosquatro/storemanagement/logs/estoque.log";

    /**
     * Usado para puxar a data e horário atual do sistema.
     * 
     * @return A data e hora atual no formato dd/MM/yyyy, hh:mm:ss
     */
    private String agora() {return LocalDateTime.now().format(formataHora);}

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

    /**
     * <p> Método para carregar a lista de produtos de um arquivo
     * <p> Utiliza deserialização para recuperar os objetos de produtos
     */
    private void salvarProdutos() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            out.writeObject(produtos);
        } catch (IOException e) {
            System.out.println("Erro ao salvar produtos: " + e.getMessage());
        }
    }

    /**
     * <p> Método para carregar a lista de produtos de um arquivo
     * <p> Utiliza deserialização para recuperar os objetos de produtos
     * 
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
     * <p> Método para adicionar um novo produto ao estoque
     * <p> Atribui um ID único ao produto e o adiciona à lista de produtos
     * 
     * @param p (Produto) - O produto a ser adicionado.
     */
    public void adicionarProduto(Produto p) {
        p.setId(nextId++);
        produtos.add(p);

        String log = "[" + agora() + "] Produto de ID " + nextId + " foi adicionado.";
        registrarOperacoes(log);
        salvarProdutos();
    }

    /**
     * <p> Método para buscar um produto pelo ID.
     * <p> Retorna o produto correspondente ou null se não encontrado.
     * 
     * @param id (int) - O ID fornecido.
     * 
     * @return O produto procurado.
     */
    public Produto buscarPorId(int id) {
        return produtos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    /**
     * <p> Método para atualizar as informações de um produto existente
     * <p> Busca o produto pelo ID e atualiza seus atributos com os novos valores
     * 
     * @param id   (int)     - O ID do produto a ser atualizado.
     * @param novo (Produto) - O novo objeto que substituirá o antigo.
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
        String log = "[" + agora() + "] Produto de ID " + id + " foi atualizado.";
        registrarOperacoes(log);
        salvarProdutos();
    }

    /**
     * <p> Método para remover um produto do estoque.
     * <p> Busca o produto pelo ID e o remove da lista de produtos.
     * 
     * @param id (int) - O ID do produto a ser removido.
     */
    public void removerProduto(int id) {
        produtos.removeIf(p -> p.getId() == id);
        String log = "[" + agora() + "] Produto de ID " + id + " foi removido.";
        registrarOperacoes(log);
        salvarProdutos();
    }

    /**
     * <p> Métodos para listar produtos.
     * <p> Retorna a lista completa de produtos no estoque.
     * 
     * @return A lista de produtos.
     */
    public List<Produto> listarProdutos() {return produtos;}

    /**
     * <p> Método para listar produtos com estoque baixo
     * <p> Recebe um limite e retorna uma lista de produtos cuja quantidade é menor ou igual ao limite
     * 
     * @param limite (int) - O limite de estoque.
     * 
     * @return A lista de produtos com estoque igual ou menor que o limite.
     */
    public List<Produto> listarProdutosComBaixoEstoque(int limite) {
        return estoqueService.verificarQtdProdutos(produtos, limite);
    }

    /** 
     * <p> Método para gerar um relatório do estoque
     * <p> Utiliza o serviço de estoque para formatar e retornar um relatório dos produtos
     * 
     * @return O relatório.
     */
    public String gerarRelatorioEstoque() {
        return estoqueService.gerarRelatorioEstoque(produtos);
    }

    /**
     * Integração com Financeiro
     * 
     * @param scanner       (Scanner) - Entrada do usuário.
     * @param limiteEstoque (int)     - Para listar os produtos com estoque igual ou menor que.
     */
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

        String log = "[" + agora() + "] Compra de produtos realizada.";
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
}