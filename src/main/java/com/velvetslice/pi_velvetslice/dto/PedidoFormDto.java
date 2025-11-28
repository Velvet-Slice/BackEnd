package com.velvetslice.pi_velvetslice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.velvetslice.pi_velvetslice.enums.StatusPedido;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoFormDto {

    private Long clienteId;
    private LocalDate dataEntrega;
    private String descricao;
    private StatusPedido status;
    private boolean pago;

    // Novo campo adicionado
    private BigDecimal valorTotal;
}
