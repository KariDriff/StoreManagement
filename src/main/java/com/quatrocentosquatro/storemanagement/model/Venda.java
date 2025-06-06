package com.quatrocentosquatro.storemanagement.model;
// Essa classe representa uma venda no sistema de gerenciamento de loja.
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Venda {
    private int id;
    private LocalDateTime dataHora;
    private List<ItemVenda> itens;
    private double total;

    public Venda(int id) {
        this.id = id;
        this.dataHora = LocalDateTime.now();
        this.itens = new ArrayList<>();
        this.total = 0;
    }

    public void adicionarItem(ItemVenda item) {
        itens.add(item);
        total += item.getSubtotal();
    }

    public int getId() { return id; }
    public LocalDateTime getDataHora() { return dataHora; }
    public List<ItemVenda> getItens() { return itens; }
    public double getTotal() { return total; }
}