package com.quatrocentosquatro.storemanagement.model;

import java.time.LocalDateTime;


// Classe que representa uma transação financeira no sistema.
// Pode ser uma entrada (receita) ou saída (despesa).
public class Financeiro {
    private int id;
    private String tipo; // "entrada" | "saida"
    private double valor;
    private String descricao;
    private LocalDateTime dataHora;

    // Construtor para criar uma nova transação financeira.
    // Recebe o ID, tipo (entrada/saída), valor e descrição.
    public Financeiro(int id, String tipo, double valor, String descricao) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
        this.dataHora = LocalDateTime.now();
    }

    // Getters
    public int getId() { return id; }
    public String getTipo() { return tipo; }
    public double getValor() { return valor; }
    public String getDescricao() { return descricao; }
    // Retorna a data e hora da transação.
    public LocalDateTime getDataHora() { return dataHora; }
}