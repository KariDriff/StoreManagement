package com.quatrocentosquatro.storemanagement.model;

import java.io.Serializable; // Importando Serializable para permitir a serialização da classe
import java.time.LocalDateTime;

/**
 * <p> Classe que representa uma transação financeira no sistema.
 * <p> Pode ser uma entrada (receita) ou saída (despesa).
 * 
 * @author João M. Chervinski
 */
public class Financeiro implements Serializable { // Implementa Serializable para permitir a serialização de objetos desta classe
    private static final long serialVersionUID = 1L; // Versão de serialização para compatibilidade

    private int id;
    private String tipo; // "entrada" | "saida"
    private float valor;
    private String descricao;
    private LocalDateTime dataHora;

    /** 
     * Construtor para criar uma nova transação financeira.
     * Recebe o ID, tipo (entrada/saída), valor e descrição.
     * 
     * @param id        (int)    - O ID da transação.
     * @param tipo      (String) - O tipo da transação.
     * @param valor     (float)  - O valor da transação.
     * @param descricao (String) - A descirção da transação.
     */
    public Financeiro(int id, String tipo, float valor, String descricao) {
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