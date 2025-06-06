package com.quatrocentosquatro.storemanagement.model;
// Essa classe representa uma venda no sistema de gerenciamento de loja.
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Venda {
    private int id; // Identificador único da venda
    private LocalDateTime dataHora; // Data e hora da realização da venda
    private List<ItemVenda> itens; // Lista de itens vendidos na venda
    private float total; // Valor total da venda

    /**
     * Construtor da classe Venda.
     * @param id O identificador único da venda.
     */
    public Venda(int id) {
        this.id = id;
        this.dataHora = LocalDateTime.now(); // Define a data e hora atual
        this.itens = new ArrayList<>(); // Inicializa a lista de itens
        this.total = 0; // Inicializa o total da venda
    }

    /**
     * Adiciona um item à venda e atualiza o total.
     * @param item O item a ser adicionado na venda.
     */
    public void adicionarItem(ItemVenda item) {
        itens.add(item); // Adiciona o item à lista de itens
        total += item.getSubtotal(); // Atualiza o total da venda somando o subtotal do item
    }

    /**
     * Retorna o identificador único da venda.
     * @return O identificador único da venda.
     */
    public int getId() { return id; }

    /**
     * Retorna a data e hora da realização da venda.
     * @return A data e hora da realização da venda.
     */
    public LocalDateTime getDataHora() { return dataHora; }

    /**
     * Retorna a lista de itens vendidos na venda.
     * @return A lista de itens vendidos na venda.
     */
    public List<ItemVenda> getItens() { return itens; }

    /**
     * Retorna o valor total da venda.
     * @return O valor total da venda.
     */
    public float getTotal() { return total; }
}