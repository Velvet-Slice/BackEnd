package com.velvetslice.pi_velvetslice.models;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPed")
    private Long id;

    @Column(name = "data_pedido")
    private LocalDateTime dataPedido;

    @Column(name = "data_entrega")
    private LocalDate dataEntrega;

    @ManyToOne
    @JoinColumn(name = "idCli")
    private Cliente cliente;


    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<itemPedido> itens;


    protected Pedido() {}

    public Pedido(Long id, LocalDateTime dataPedido, LocalDate dataEntrega, Cliente cliente, List<itemPedido> itens) {
        this.id = id;
        this.dataPedido = dataPedido;
        this.dataEntrega = dataEntrega;
        this.cliente = cliente;
        this.itens = itens;
    }
}
