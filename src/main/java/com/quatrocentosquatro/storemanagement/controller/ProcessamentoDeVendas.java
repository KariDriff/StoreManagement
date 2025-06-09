package com.quatrocentosquatro.storemanagement.controller;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.JOptionPane;

import com.quatrocentosquatro.storemanagement.model.ItemVenda;
import com.quatrocentosquatro.storemanagement.model.Produto;
import com.quatrocentosquatro.storemanagement.model.Venda;

/**
 * Controller para processar vendas.
 * Permite registrar vendas, adicionar itens, atualizar estoque e registrar entradas financeiras.
 * 
 * @author João M. Chervinski
 * @author Kaio A. Souza
 */
public class ProcessamentoDeVendas {
    private List<Venda> vendas;
    private final String ARQUIVO = "src/main/java/com/quatrocentosquatro/storemanagement/database/vendas.db"; // Caminho do arquivo onde as vendas serão salvas
    private int nextVendaId = 1;
    private GerenciarEstoque estoqueController;
    private Financeiro financeiro;
    private final String caminhoLog = "src/main/java/com/quatrocentosquatro/storemanagement/logs/ProcessamentoDeVendas.log";
    private final DateTimeFormatter formataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    /** 
     * <p> Construtor que recebe o controller de estoque e o serviço financeiro.
     * <p> Isso permite que o processamento de vendas interaja com o estoque e registre entradas financeiras.
     * <p> Carrega as vendas do arquivo e inicializa o próximo ID de venda.
     * 
     * @param estoqueController (GerenciarEstoque) - Objeto estoque.
     * @param financeiro        (Financeiro) - Objeto financeiro.
     */
    public ProcessamentoDeVendas(GerenciarEstoque estoqueController, Financeiro financeiro) {
        this.estoqueController = estoqueController;
        this.financeiro = financeiro;
        this.vendas = carregarVendas();
        this.nextVendaId = vendas.stream()
                                 .mapToInt(Venda::getId)
                                 .max()
                                 .orElse(0) + 1;
    }

    /**
     * <p> Método para salvar a lista de vendas em um arquivo.
     * <p> Utiliza serialização para armazenar os objetos de venda.
     */
    private void salvarVendas() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            out.writeObject(vendas);
        } catch (IOException e) {
            System.out.println("Erro ao salvar vendas: " + e.getMessage());
        }
        String log = "[" + agora() + "] A lista de Vendas foi salva.";
        registrarOperacoes(log);
    }

    /**
     * <p> Método para carregar a lista de vendas de um arquivo.
     * <p> Utiliza deserialização para recuperar os objetos de venda.
     */
    @SuppressWarnings("unchecked")
    private List<Venda> carregarVendas() {
        File file = new File(ARQUIVO);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Venda>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar vendas: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Usado para puxar a data e horário atual do sistema.
     * 
     * @return A data e hora atual no formato dd/MM/yyyy, hh:mm:ss
     */
    private String agora() {return LocalDateTime.now().format(formataHora);}

    /**
     * <p> Método para realizar uma venda via console.
     * <p> Solicita ao usuário os detalhes da venda, como produtos, quantidades e descontos.
     * <p> Atualiza o estoque e registra a entrada financeira.
     *
     * @param scanner (Scanner) - Para ler entradas do usuário.
     */
    public void realizarVenda(Scanner scanner) {
        // Cria uma nova venda com um ID único
        Venda venda = new Venda(nextVendaId++);
        boolean adicionandoItens = true;

        // Loop para adicionar itens à venda
        while (adicionandoItens) {
            System.out.print("ID do produto a vender: ");
            int idProduto = scanner.nextInt();
            Produto p = (Produto) estoqueController.buscarPorId(idProduto);

            // Verifica se o produto existe
            if (p == null) {
                System.out.println("Produto não encontrado.");
                continue;
            }

            System.out.printf("Estoque disponível: %d\n", p.getQuantidade());
            System.out.print("Quantidade a vender: ");
            int qtd = scanner.nextInt();

            // Verifica se há estoque suficiente
            if (qtd > p.getQuantidade()) {
                System.out.println("Estoque insuficiente.");
                continue;
            }

            System.out.print("Desconto a aplicar (R$): ");
            double desconto = scanner.nextDouble();
            scanner.nextLine(); // Limpa o buffer do scanner

            double precoUnitario = p.getPreco();
            // Cria o item da venda e adiciona à venda
            ItemVenda item = new ItemVenda(p, qtd, precoUnitario, desconto);
            venda.adicionarItem(item);

            // Atualiza o estoque do produto
            p.setQuantidade(p.getQuantidade() - qtd);

            System.out.print("Adicionar outro item? (s/n): ");
            String opcao = scanner.nextLine();
            adicionandoItens = opcao.equalsIgnoreCase("s");
        }

        // Adiciona a venda à lista de vendas
        vendas.add(venda);

        // Solicita a forma de pagamento
        System.out.print("Qual o método de pagamento? (1-Dinheiro, 2-Cartão de Crédito, 3-Cartão de Débito, 4-PIX): ");
        int formaPagamento = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer
        String formaStr;
        switch (formaPagamento) {
            case 1: formaStr = "Dinheiro"; break;
            case 2: formaStr = "Cartão de Crédito"; break;
            case 3: formaStr = "Cartão de Débito"; break;
            case 4: formaStr = "PIX"; break;
            default: formaStr = "Desconhecido";
        }

        // Registra a entrada financeira referente à venda
        financeiro.registrarEntrada(venda.getTotal(), "Pagamento via " + formaStr);

        System.out.println("Venda registrada com sucesso!");
        System.out.printf("Total da venda: R$ %.2f\n", venda.getTotal());

        // Registra a operação no log e salva as vendas
        String log = "[" + agora() + "] Venda realizada no valor de " + venda.getTotal() + " utilizando " + formaStr;
        registrarOperacoes(log);
        salvarVendas();
    }

    /**
     * <p> Método para finalizar a venda via interface gráfica.
     * <p> Atualiza lista de vendas, registra entrada financeira e salva vendas.
     * 
     * @param venda          (Venda)  - Venda já montada com itens e total.
     * @param formaPagamento (String) - que representa a forma de pagamento (ex: "Dinheiro", "PIX", etc.).
     */
    public void finalizarVendaGUI(Venda venda, String formaPagamento) {
        vendas.add(venda);
        financeiro.registrarEntrada(venda.getTotal(), "Pagamento via " + formaPagamento);
        registrarOperacoes("[" + agora() + "] Venda realizada no valor de " + venda.getTotal() + " via " + formaPagamento);
        salvarVendas();
    }

    /**
     * <p> Método para listar todas as vendas registradas.
     * <p> Exibe o histórico de vendas com detalhes dos itens vendidos.
     */
    public void listarVendas() {
        System.out.println("=== HISTÓRICO DE VENDAS ===");
        for (Venda v : vendas) {
            System.out.printf("Venda ID %d | %s | Total: R$ %.2f\n",
                    v.getId(), v.getDataHora(), v.getTotal());
            for (ItemVenda item : v.getItens()) {
                System.out.printf("  - %s | Qtd: %d | Subtotal: R$ %.2f\n",
                        item.getProduto().getNome(), item.getQuantidade(), item.getSubtotal());
            }
        }
    }

    /**
     * Registra as operações feitas na classe em seu log
     * 
     * @param acao (String) - A operação feita.
     */
    private void registrarOperacoes(String acao) {
        try {
            FileWriter escreverAcao = new FileWriter(caminhoLog, true);
            escreverAcao.append(acao).append("\n");
            escreverAcao.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível registrar ação no Gerenciar Usuários:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método de acesso para o controller de estoque.
     * <p>
     * Atualmente não implementado.
     * 
     * @return (Object) - Controller de estoque.
     * @throws UnsupportedOperationException Sempre que chamado.
     */
    public Object getGerenciarEstoque() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
