package com.velvetslice.pi_velvetslice.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.velvetslice.pi_velvetslice.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPed")
    private Long id;

    @Column(name = "data_pedido", nullable = false)
    private LocalDateTime dataPedido = LocalDateTime.now();

    @Column(name = "data_entrega", nullable = false)
    private LocalDate dataEntrega = LocalDate.now().plusDays(1);

    @ManyToOne
    @JoinColumn(name = "idCli", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ItemPedido> itens = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.ABERTO;

    @Column(name = "valor_total")
    private BigDecimal valorTotal = BigDecimal.ZERO;

    public void adicionarItem(ItemPedido item) {
        item.setPedido(this);
        this.itens.add(item);
        this.valorTotal = this.valorTotal.add(item.getSubtotal());
    }

    public BigDecimal getTotal() {
        return itens.stream()
                .map(ItemPedido::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Pedido(Long id, LocalDateTime dataPedido, LocalDate dataEntrega, Cliente cliente, List<ItemPedido> itens, StatusPedido status, BigDecimal valorTotal) {
        this.id = id;
        this.dataPedido = LocalDateTime.now();
        this.dataEntrega = dataEntrega;
        this.cliente = cliente;
        this.itens = itens;
        this.status = StatusPedido.ABERTO;
        this.valorTotal = valorTotal;
    }
}
