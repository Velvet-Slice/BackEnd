package com.velvetslice.pi_velvetslice.models;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "itens_pedido") // Mapeia para a nova tabela 'itens_pedido'
public class itemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idItem")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idPed") // Liga ao Pedido
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "idProd") // Liga ao Produto
    private Produto produto;

    @Column(name = "quantidade")
    private int quantidade;

    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;

    protected itemPedido() {}

    public itemPedido(Long id, Pedido pedido, Produto produto, int quantidade, BigDecimal precoUnitario) {
        this.id = id;
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }
}