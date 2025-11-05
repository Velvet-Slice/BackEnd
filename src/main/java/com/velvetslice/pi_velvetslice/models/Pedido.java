package com.velvetslice.pi_velvetslice.models;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPed")
    private Long id;

    @Column(name = "data_pedido", nullable = false)
    private LocalDateTime dataPedido;

    @Column(name = "data_entrega", nullable = false)
    private LocalDate dataEntrega;

    @ManyToOne
    @JoinColumn(name = "idCli", nullable = false)
    private Cliente cliente;


    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<itemPedido> itens;


    public Pedido(Long id, LocalDateTime dataPedido, LocalDate dataEntrega, Cliente cliente, List<itemPedido> itens) {
        this.id = id;
        this.dataPedido = dataPedido;
        this.dataEntrega = dataEntrega;
        this.cliente = cliente;
        this.itens = itens;
    }
}
