package Gatio.dtos;

import de.huxhorn.sulky.ulid.ULID;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import Gatio.enums.StatusComanda;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ComandaDto {
    private ULID.Value id;
    private int numero;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataFechamento;
    private StatusComanda status;
    private List<PedidoDto> pedidos = new ArrayList<>();

    // Construtor padrão
    public ComandaDto() {
    }

    // Construtor com parâmetros
    @JsonCreator
    public ComandaDto(@JsonProperty("numero") int numero) {
        this.numero = numero;
    }

    // Getters e Setters

    @JsonProperty("id")
    public ULID.Value getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(ULID.Value id) {
        this.id = id;
    }

    @JsonProperty("numero")
    public int getNumero() {
        return numero;
    }

    @JsonProperty("numero")
    public void setNumero(int numero) {
        this.numero = numero;
    }

    @JsonProperty("dataAbertura")
    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    @JsonProperty("dataAbertura")
    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    @JsonProperty("dataFechamento")
    public LocalDateTime getDataFechamento() {
        return dataFechamento;
    }

    @JsonProperty("dataFechamento")
    public void setDataFechamento(LocalDateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    @JsonProperty("status")
    public StatusComanda getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(StatusComanda status) {
        this.status = status;
    }

    @JsonProperty("pedidos")
    public List<PedidoDto> getPedidos() {
        return pedidos;
    }

    @JsonProperty("pedidos")
    public void setPedidos(List<PedidoDto> pedidos) {
        this.pedidos = pedidos;
    }
}
