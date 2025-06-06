package main.java.com.quatrocentosquatro.storemanagement.service;

import main.java.com.quatrocentosquatro.storemanagement.model.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class Estoque {

    public List<Produto> verificarQtdProdutos(List<Produto> produtos, int limite) {
        return produtos.stream()
                .filter(p -> p.getQuantidade() <= limite)
                .collect(Collectors.toList());
    }

    public String gerarRelatorioEstoque(List<Produto> produtos) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== RELATÓRIO DE ESTOQUE ===\n");
        for (Produto p : produtos) {
            sb.append(String.format("ID: %d | %s | Qtd: %d | Vencimento: %s\n",
                    p.getId(), p.getNome(), p.getQuantidade(),
                    p.getDataValidade()));
        }
        return sb.toString();
    }
}
