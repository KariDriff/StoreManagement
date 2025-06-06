package com.quatrocentosquatro.storemanagement.model;
import java.time.LocalDate;

public class Produto {
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
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public String getLote() { return lote; }
    public void setLote(String lote) { this.lote = lote; }

    public String getCodigoBarras() { return codigoBarras; }
    public void setCodigoBarras(String codigoBarras) { this.codigoBarras = codigoBarras; }

    public LocalDate getDataValidade() { return dataValidade; }
    public void setDataValidade(LocalDate dataValidade) { this.dataValidade = dataValidade; }

    public int getVolumeLitros() { return volumeLitros; }
    public void setVolumeLitros(int volumeLitros) { this.volumeLitros = volumeLitros; }

    public int getPesoGramas() { return pesoGramas; }
    public void setPesoGramas(int pesoGramas) { this.pesoGramas = pesoGramas; }
}