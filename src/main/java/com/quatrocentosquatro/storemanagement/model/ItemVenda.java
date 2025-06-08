package com.quatrocentosquatro.storemanagement.model;

import java.io.Serializable; // Importando Serializable para permitir a serialização da classe

/**
 * Essa classe representa a venda de um produto.
 * 
 * @author João M. Chervinski
 */
public class ItemVenda implements Serializable { // Implementa Serializable para permitir a serialização de objetos desta classe
    private static final long serialVersionUID = 1L; // Versão de serialização para compatibilidade

    private Produto produto; // Produto associado a este item de venda.
    private int quantidade; // Quantidade vendida deste item.
    private double precoUnitario; // Preço unitário do produto neste item de venda.
    private double desconto; // Desconto aplicado a este item de venda.

    /**
     * Construtor da classe ItemVenda.
     * 
     * @param produto       (Produto) - Produto associado a este item de venda.
     * @param quantidade    (int)     - Quantidade vendida deste item.
     * @param precoUnitario (double)  - Preço unitário do produto neste item de venda.
     * @param desconto      (double)  - Desconto aplicado a este item de venda.
     */
    public ItemVenda(Produto produto, int quantidade, double precoUnitario, double desconto) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.desconto = desconto;
    }

    /* Getters */

    // Calcula o subtotal do item de venda, considerando o preço unitário, a quantidade e o desconto.
    public double getSubtotal() {return (precoUnitario * quantidade) - desconto;}

    public Produto getProduto() { return produto; }

    public int getQuantidade() { return quantidade; }

    public double getPrecoUnitario() { return precoUnitario; }
 
    public double getDesconto() { return desconto; }
}