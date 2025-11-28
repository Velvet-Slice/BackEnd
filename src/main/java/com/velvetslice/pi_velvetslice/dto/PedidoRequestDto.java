package com.velvetslice.pi_velvetslice.dto;

import java.time.LocalDate;

import com.velvetslice.pi_velvetslice.enums.StatusPedido;

// Record: recurso do Java moderno para classes imutáveis de dados
public record PedidoRequestDto(
        Long id,
        String nomeCliente, // Vamos buscar o cliente pelo nome ou ID
        LocalDate dataEntrega,
        String descricao,
        StatusPedido status,
        boolean pago // Campo para o checkbox
        ) {

}
