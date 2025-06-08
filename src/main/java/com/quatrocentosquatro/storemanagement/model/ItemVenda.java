package com.quatrocentosquatro.storemanagement.model;
import java.io.Serializable; // Importando Serializable para permitir a serialização da classe
// Essa classe representa a venda de um produto.

public class ItemVenda implements Serializable { // Implementa Serializable para permitir a serialização de objetos desta classe
    private static final long serialVersionUID = 1L; // Versão de serialização para compatibilidade

    private Produto produto; // Produto associado a este item de venda.
    private int quantidade; // Quantidade vendida deste item.
    private double precoUnitario; // Preço unitário do produto neste item de venda.
    private double desconto; // Desconto aplicado a este item de venda.

    /**
     * Construtor da classe ItemVenda.
     * @param produto Produto associado a este item de venda.
     * @param quantidade Quantidade vendida deste item.
     * @param precoUnitario Preço unitário do produto neste item de venda.
     * @param desconto Desconto aplicado a este item de venda.
     */
    public ItemVenda(Produto produto, int quantidade, double precoUnitario, double desconto) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.desconto = desconto;
    }

    /**
     * Calcula o subtotal do item de venda, considerando o preço unitário, a quantidade e o desconto.
     * @return O subtotal do item de venda.
     */
    public double getSubtotal() {
        return (precoUnitario * quantidade) - desconto;
    }

    /**
     * Retorna o produto associado a este item de venda.
     * @return O produto associado a este item de venda.
     */
    public Produto getProduto() { return produto; }
    /**
     * Retorna a quantidade vendida deste item.
     * @return A quantidade vendida deste item.
     */
    public int getQuantidade() { return quantidade; }
    /**
     * Retorna o preço unitário do produto neste item de venda.
     * @return O preço unitário do produto neste item de venda.
     */
    public double getPrecoUnitario() { return precoUnitario; }
    /**
     * Retorna o desconto aplicado a este item de venda.
     * @return O desconto aplicado a este item de venda.
     */
    public double getDesconto() { return desconto; }
}