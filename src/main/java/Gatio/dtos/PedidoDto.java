package Gatio.dtos;

import java.time.LocalDateTime;

import de.huxhorn.sulky.ulid.ULID;

public class PedidoDto {
    private ULID.Value id;
    private LocalDateTime dataPedido;
    private ProdutoDto produto;
    private int quantidade;
    private double total;

     public PedidoDto(ProdutoDto produto, int quantidade) {
        this.id = new ULID().nextValue();
        this.dataPedido = LocalDateTime.now();
        this.produto = produto;
        this.quantidade = quantidade;
        this.total = calcularTotal();
    }

    public ULID.Value getId() {
        return id;
    }

    public void setId(ULID.Value id) {
        this.id = id;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public ProdutoDto getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    private double calcularTotal() {
        return produto.getPreco() * quantidade;
    }
}
