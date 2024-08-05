package Gatio.dtos;

import Gatio.enums.TipoProduto;
import de.huxhorn.sulky.ulid.ULID;

public class ProdutoDto {
    private ULID.Value id;
    private String nome;
    private double preco;
    private TipoProduto tipoProduto;

    public ProdutoDto(String nome, double preco, TipoProduto tipoProduto) {
        this.id = new ULID().nextValue();
        this.nome = nome;
        this.preco = preco;
        this.tipoProduto = tipoProduto;
    }

    public ULID.Value getId() {
        return id;
    }

    public void setId(ULID.Value id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }
}
