package com.velvetslice.pi_velvetslice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "itens_pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idItem")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idPed")
    @JsonBackReference
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "idProd")
    private Produto produto;

    private int quantidade;

    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;

    private BigDecimal subtotal;

    public BigDecimal getSubtotal() {
        if (subtotal == null && precoUnitario != null) {
            return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
        }
        return subtotal;
    }

}
