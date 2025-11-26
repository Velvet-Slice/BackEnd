package com.velvetslice.pi_velvetslice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddItemCarrinhoDto {
    private Long clienteId;
    private Long produtoId;
    private int quantidade;
}
