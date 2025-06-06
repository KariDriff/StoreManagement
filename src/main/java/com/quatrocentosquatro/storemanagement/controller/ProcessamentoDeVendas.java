package com.quatrocentosquatro.storemanagement.controller;

// Importações necessárias
import com.quatrocentosquatro.storemanagement.model.Produto;
import com.quatrocentosquatro.storemanagement.model.Venda;
import com.quatrocentosquatro.storemanagement.model.ItemVenda;
import java.util.*;

// Controller para processar vendas
// Permite registrar vendas, adicionar itens, atualizar estoque e registrar entradas financeiras
public class ProcessamentoDeVendas {
    private List<Venda> vendas = new ArrayList<>();
    private int nextVendaId = 1;
    private GerenciarEstoque estoqueController;
    private Financeiro financeiro;

    // Construtor que recebe o controller de estoque e o serviço financeiro
    // Isso permite que o processamento de vendas interaja com o estoque e registre entradas financeiras
    public ProcessamentoDeVendas(GerenciarEstoque estoqueController, Financeiro financeiro) {
        this.estoqueController = estoqueController;
        this.financeiro = financeiro;
    }

    /**
     * Método para realizar uma venda.
     * Solicita ao usuário os detalhes da venda, como produtos, quantidades e descontos.
     * Atualiza o estoque e registra a entrada financeira.
     *
     * @param scanner Scanner para ler entradas do usuário.
     */
    public void realizarVenda(Scanner scanner) {
        Venda venda = new Venda(nextVendaId++);
        boolean adicionandoItens = true;

        while (adicionandoItens) {
            System.out.print("ID do produto a vender: ");
            int idProduto = scanner.nextInt();
            Produto p = estoqueController.buscarPorId(idProduto);

            if (p == null) {
                System.out.println("Produto não encontrado.");
                continue;
            }

            System.out.printf("Estoque disponível: %d\n", p.getQuantidade());
            System.out.print("Quantidade a vender: ");
            int qtd = scanner.nextInt();
            if (qtd > p.getQuantidade()) {
                System.out.println("Estoque insuficiente.");
                continue;
            }

            System.out.print("Desconto a aplicar (R$): ");
            double desconto = scanner.nextDouble();
            scanner.nextLine(); // limpar buffer

            double precoUnitario = p.getPreco();
            ItemVenda item = new ItemVenda(p, qtd, precoUnitario, desconto);
            venda.adicionarItem(item);

            // Atualiza estoque
            p.setQuantidade(p.getQuantidade() - qtd);

            System.out.print("Adicionar outro item? (s/n): ");
            String opcao = scanner.nextLine();
            adicionandoItens = opcao.equalsIgnoreCase("s");
        }

        

// Finalizar venda
vendas.add(venda);

System.out.print("Qual o método de pagamento? (1-Dinheiro, 2-Cartão de Crédito, 3-Cartão de Débito, 4-PIX): ");
int formaPagamento = scanner.nextInt();
scanner.nextLine(); // Consume newline left-over
String formaStr;
switch (formaPagamento) {
    case 1:
        formaStr = "Dinheiro";
        break;
    case 2:
        formaStr = "Cartão de Crédito";
        break;
    case 3:
        formaStr = "Cartão de Débito";
        break;
    case 4:
        formaStr = "PIX";
        break;
    default:
        formaStr = "Desconhecido";
}

financeiro.registrarEntrada(venda.getTotal(), "Pagamento via " + formaStr);

System.out.println("Venda registrada com sucesso!");
        System.out.printf("Total da venda: R$ %.2f\n", venda.getTotal());
    }

    /**
     * Método para listar todas as vendas registradas.
     * Exibe o histórico de vendas com detalhes dos itens vendidos.
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
}

