package com.quatrocentosquatro.storemanagement.model;
import java.time.LocalDate;
import java.io.Serializable; // Importando Serializable para permitir a serialização da classe

/**
 * Classe que representa um produto no sistema de gerenciamento de loja.
 */
public class Produto implements Serializable { // Implementa Serializable para permitir a serialização de objetos desta classe
    private static final long serialVersionUID = 1L; // Versão de serialização para compatibilidade

    private int id;
    private String nome;
    private String marca;
    private double preco;
    private int quantidade;
    private String lote;
    private String codigoBarras;
    private LocalDate dataValidade;
    private int volumeLitros;
    private int pesoGramas;

    /**
     * Construtor da classe Produto.
     * @param id O ID do produto.
     * @param nome O nome do produto.
     * @param marca A marca do produto.
     * @param preco O preço do produto.
     * @param quantidade A quantidade em estoque do produto.
     * @param lote O lote do produto.
     * @param codigoBarras O código de barras do produto.
     * @param dataValidade A data de validade do produto.
     * @param volumeLitros O volume em litros do produto.
     * @param pesoGramas O peso em gramas do produto.
     */
    public Produto(int id, String nome, String marca, double preco, int quantidade, String lote,
                   String codigoBarras, LocalDate dataValidade, int volumeLitros, int pesoGramas) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.preco = preco;
        this.quantidade = quantidade;
        this.lote = lote;
        this.codigoBarras = codigoBarras;
        this.dataValidade = dataValidade;
        this.volumeLitros = volumeLitros;
        this.pesoGramas = pesoGramas;
    }

    // Getters e Setters

    /**
     * Retorna o ID do produto.
     * @return O ID do produto.
     */
    public int getId() { return id; }

    /**
     * Define o ID do produto.
     * @param id O ID do produto.
     */
    public void setId(int id) { this.id = id; }

    /**
     * Retorna o nome do produto.
     * @return O nome do produto.
     */
    public String getNome() { return nome; }

    /**
     * Define o nome do produto.
     * @param nome O nome do produto.
     */
    public void setNome(String nome) { this.nome = nome; }

    /**
     * Retorna a marca do produto.
     * @return A marca do produto.
     */
    public String getMarca() { return marca; }

    /**
     * Define a marca do produto.
     * @param marca A marca do produto.
     */
    public void setMarca(String marca) { this.marca = marca; }

    /**
     * Retorna o preço do produto.
     * @return O preço do produto.
     */
    public double getPreco() { return preco; }

    /**
     * Define o preço do produto.
     * @param preco O preço do produto.
     */
    public void setPreco(double preco) { this.preco = preco; }

    /**
     * Retorna a quantidade em estoque do produto.
     * @return A quantidade em estoque do produto.
     */
    public int getQuantidade() { return quantidade; }

    /**
     * Define a quantidade em estoque do produto.
     * @param quantidade A quantidade em estoque do produto.
     */
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    /**
     * Retorna o lote do produto.
     * @return O lote do produto.
     */
    public String getLote() { return lote; }

    /**
     * Define o lote do produto.
     * @param lote O lote do produto.
     */
    public void setLote(String lote) { this.lote = lote; }

    /**
     * Retorna o código de barras do produto.
     * @return O código de barras do produto.
     */
    public String getCodigoBarras() { return codigoBarras; }

    /**
     * Define o código de barras do produto.
     * @param codigoBarras O código de barras do produto.
     */
    public void setCodigoBarras(String codigoBarras) { this.codigoBarras = codigoBarras; }

    /**
     * Retorna a data de validade do produto.
     * @return A data de validade do produto.
     */
    public LocalDate getDataValidade() { return dataValidade; }

    /**
     * Define a data de validade do produto.
     * @param dataValidade A data de validade do produto.
     */
    public void setDataValidade(LocalDate dataValidade) { this.dataValidade = dataValidade; }

    /**
     * Retorna o volume em litros do produto.
     * @return O volume em litros do produto.
     */
    public int getVolumeLitros() { return volumeLitros; }

    /**
     * Define o volume em litros do produto.
     * @param volumeLitros O volume em litros do produto.
     */
    public void setVolumeLitros(int volumeLitros) { this.volumeLitros = volumeLitros; }

    /**
     * Retorna o peso em gramas do produto.
     * @return O peso em gramas do produto.
     */
    public int getPesoGramas() { return pesoGramas; }

    /**
     * Define o peso em gramas do produto.
     * @param pesoGramas O peso em gramas do produto.
     */
    public void setPesoGramas(int pesoGramas) { this.pesoGramas = pesoGramas; }
}