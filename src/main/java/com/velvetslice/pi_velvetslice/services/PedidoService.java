package com.velvetslice.pi_velvetslice.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.velvetslice.pi_velvetslice.dto.PedidoDto;
import com.velvetslice.pi_velvetslice.dto.PedidoFormDto;
import com.velvetslice.pi_velvetslice.exception.ProdutoNulloException;
import com.velvetslice.pi_velvetslice.models.Cliente;
import com.velvetslice.pi_velvetslice.models.Pedido;
import com.velvetslice.pi_velvetslice.repository.ClienteRepository;
import com.velvetslice.pi_velvetslice.repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository; // Necessário para vincular o cliente

    // Método existente (mantido)
    public PedidoDto save(Pedido pedido) {
        if (pedido == null) {
            throw new ProdutoNulloException("Nenhum produto encontrado.");
        }
        pedidoRepository.save(pedido);
        return new PedidoDto(pedido.getId());
    }

    // Método existente (mantido)
    public List<Pedido> getPedidos() {
        return pedidoRepository.findAll();
    }

    // --- NOVOS MÉTODOS PARA O ADMIN ---
    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }
// ... (imports e autowired mantidos)

    // ... (imports mantidos)
    public Pedido criarPedidoAdmin(PedidoFormDto dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + dto.getClienteId()));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setDataEntrega(dto.getDataEntrega());
        pedido.setDescricao(dto.getDescricao());
        pedido.setStatus(dto.getStatus());

        // Salva Valor
        if (dto.getValorTotal() != null) {
            pedido.setValorTotal(dto.getValorTotal());
        } else {
            pedido.setValorTotal(BigDecimal.ZERO);
        }

        // Salva Status de Pagamento (NOVO)
        pedido.setPago(dto.isPago());

        return pedidoRepository.save(pedido);
    }

    public Pedido atualizarPedidoAdmin(Long id, PedidoFormDto dto) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if (dto.getClienteId() != null) {
            Cliente cliente = clienteRepository.findById(dto.getClienteId())
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
            pedido.setCliente(cliente);
        }

        pedido.setDataEntrega(dto.getDataEntrega());
        pedido.setDescricao(dto.getDescricao());
        pedido.setStatus(dto.getStatus());

        if (dto.getValorTotal() != null) {
            pedido.setValorTotal(dto.getValorTotal());
        }

        // Atualiza Status de Pagamento (NOVO)
        pedido.setPago(dto.isPago());

        return pedidoRepository.save(pedido);
    }

    public void excluirPedido(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido não encontrado");
        }
        pedidoRepository.deleteById(id);
    }

}
