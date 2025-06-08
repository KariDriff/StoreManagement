package com.quatrocentosquatro.storemanagement.model;

import java.io.Serializable; // Importando Serializable para permitir a serialização da classe
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Essa classe representa uma venda no sistema de gerenciamento de loja.
 * 
 * @author João M. Chervinski
 */

public class Venda implements Serializable { // Implementa Serializable para permitir a serialização de objetos desta classe
    private static final long serialVersionUID = 1L; // Versão de serialização para compatibilidade

    private int id; // Identificador único da venda
    private LocalDateTime dataHora; // Data e hora da realização da venda
    private List<ItemVenda> itens; // Lista de itens vendidos na venda
    private float total; // Valor total da venda

    /**
     * Construtor da classe Venda.
     * 
     * @param id (int) - O ID da venda.
     */
    public Venda(int id) {
        this.id = id;
        this.dataHora = LocalDateTime.now(); // Define a data e hora atual
        this.itens = new ArrayList<>(); // Inicializa a lista de itens
        this.total = 0; // Inicializa o total da venda
    }

    /**
     * Adiciona um item à venda e atualiza o total.
     * 
     * @param item (ItemVenda) - O item a ser adicionado na venda.
     */
    public void adicionarItem(ItemVenda item) {
        itens.add(item); // Adiciona o item à lista de itens
        total += item.getSubtotal(); // Atualiza o total da venda somando o subtotal do item
    }

    /* Getters */

    public int getId() { return id; }

    public LocalDateTime getDataHora() { return dataHora; }

    public List<ItemVenda> getItens() { return itens; }

    public float getTotal() { return total; }
}