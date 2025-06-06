package com.quatrocentosquatro.storemanagement.controller;

import com.quatrocentosquatro.storemanagement.model.Produto;
import com.quatrocentosquatro.storemanagement.model.Venda;
import com.quatrocentosquatro.storemanagement.model.ItemVenda;

import java.util.*;

public class ProcessamentoDeVendas {
    private List<Venda> vendas = new ArrayList<>();
    private int nextVendaId = 1;
    private GerenciarEstoque estoqueController;
    private Financeiro financeiro;

    public ProcessamentoDeVendas(GerenciarEstoque estoqueController, Financeiro financeiro) {
        this.estoqueController = estoqueController;
        this.financeiro = financeiro;
    }

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

        vendas.add(venda);

        // Registrar entrada financeira
        financeiro.registrarEntrada(venda.getTotal(), "");

        System.out.println("Venda registrada com sucesso!");
        System.out.printf("Total da venda: R$ %.2f\n", venda.getTotal());
    }

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

