package com.velvetslice.pi_velvetslice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoCheckoutDTO {
    private Long clienteId;
    private String dataEntrega;
    private String observacoes;
}