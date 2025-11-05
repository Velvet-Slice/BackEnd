package com.velvetslice.pi_velvetslice.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "itens_pedido")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class itemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idItem")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idPed", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "idProd", nullable = false)
    private Produto produto;

    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    @Column(name = "preco_unitario", nullable = false)
    private BigDecimal precoUnitario;

    public itemPedido(Long id, Pedido pedido, Produto produto, int quantidade, BigDecimal precoUnitario) {
        this.id = id;
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }
}