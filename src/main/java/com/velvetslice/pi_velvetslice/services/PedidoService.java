package com.velvetslice.pi_velvetslice.services;


import com.velvetslice.pi_velvetslice.dto.PedidoDto;
import com.velvetslice.pi_velvetslice.exception.ProdutoNulloException;
import com.velvetslice.pi_velvetslice.models.Pedido;
import com.velvetslice.pi_velvetslice.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//teste
@Service
public class 'PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public PedidoDto save(Pedido pedido) {
        if (pedido == null) throw new ProdutoNulloException("Nenhum produto encontrado.");
        pedidoRepository.save(pedido);
        return new PedidoDto(pedido.getId());
    }

    public List<Pedido> getPedidos() {return pedidoRepository.findAll();}
}
