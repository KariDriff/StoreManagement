package main.java.com.quatrocentosquatro.storemanagement.model;
// Essa classe representa a venda de um produto.

public class ItemVenda {
    private Produto produto;
    private int quantidade;
    private double precoUnitario;
    private double desconto;

    public ItemVenda(Produto produto, int quantidade, double precoUnitario, double desconto) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.desconto = desconto;
    }

    public double getSubtotal() {
        return (precoUnitario * quantidade) - desconto;
    }

    public Produto getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }
    public double getPrecoUnitario() { return precoUnitario; }
    public double getDesconto() { return desconto; }
}