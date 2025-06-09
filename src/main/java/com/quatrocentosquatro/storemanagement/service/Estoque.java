package com.quatrocentosquatro.storemanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import com.quatrocentosquatro.storemanagement.model.Produto;

/**
 * Classe para verificar produtos e gerar relatórios.
 * 
 * @author João M. Chervinski
 */
public class Estoque {
    /**
     * Verifica a quantidade de produtos em estoque e retorna uma lista
     * dos produtos que estão abaixo do limite especificado.
     *
     * @param produtos (List<Produto>) - Lista de produtos a serem verificados.
     * @param limite   (int)           - Limite de quantidade abaixo do qual os produtos são considerados.
     * 
     * @return Lista de produtos com quantidade abaixo do limite.
     */
    public List<Produto> verificarQtdProdutos(List<Produto> produtos, int limite) {
        return produtos.stream()
                       .filter(p -> p.getQuantidade() <= limite)
                       .collect(Collectors.toList());
    }

    /**
     * Gera um relatório de estoque formatado em string,
     * contendo informações detalhadas sobre cada produto.
     *
     * @param produtos (List<Produto>) - Lista de produtos para gerar o relatório.
     * 
     * @return String contendo o relatório de estoque formatado.
     */
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
