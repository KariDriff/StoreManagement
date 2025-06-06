package main.java.com.quatrocentosquatro.storemanagement.model;

import java.time.LocalDateTime;

public class Financeiro {
    private int id;
    private String tipo; // "entrada" | "saida"
    private double valor;
    private String descricao;
    private LocalDateTime dataHora;

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
    public LocalDateTime getDataHora() { return dataHora; }
}