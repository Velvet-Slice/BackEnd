package com.velvetslice.pi_velvetslice.services;

import com.velvetslice.pi_velvetslice.dto.AddItemCarrinhoDto;
import com.velvetslice.pi_velvetslice.enums.StatusPedido;
import com.velvetslice.pi_velvetslice.models.*;
import com.velvetslice.pi_velvetslice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class CarrinhoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public Pedido adicionarItem(AddItemCarrinhoDto dto) {

        // Buscar cliente
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // Buscar produto
        Produto produto = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        // Buscar pedido em aberto
        Pedido pedido = pedidoRepository
                .findByClienteIdAndStatus(cliente.getId(), StatusPedido.ABERTO)
                .orElse(null);

        // Se não existir carrinho → criar um novo
        if (pedido == null) {
            pedido = new Pedido();
            pedido.setCliente(cliente);
            pedido.setStatus(StatusPedido.ABERTO);
            pedido.setDataPedido(LocalDateTime.now());
            pedido.setValorTotal(BigDecimal.ZERO);
            pedidoRepository.save(pedido);
        }

        // Criar item de pedido
        ItemPedido item = new ItemPedido();
        item.setPedido(pedido);
        item.setProduto(produto);
        item.setQuantidade(dto.getQuantidade());
        item.setPrecoUnitario(produto.getPreco()); // Pega do produto
        item.setSubtotal(produto.getPreco().multiply(BigDecimal.valueOf(dto.getQuantidade())));

        itemPedidoRepository.save(item);

        pedido.getItens().add(item);

        BigDecimal total = pedido.getItens().stream()
                .map(ItemPedido::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        pedido.setValorTotal(total);

        return pedidoRepository.save(pedido);
    }

    public Pedido verCarrinho(Long clienteId) {

        return pedidoRepository
                .findByClienteIdAndStatus(clienteId, StatusPedido.ABERTO)
                .orElseThrow(() -> new RuntimeException("Nenhum carrinho ativo encontrado."));
    }

    public void limparCarrinho(Long clienteId) {

        Pedido pedido = pedidoRepository
                .findByClienteIdAndStatus(clienteId, StatusPedido.ABERTO)
                .orElseThrow(() -> new RuntimeException("Carrinho já está vazio."));

        itemPedidoRepository.deleteAll(pedido.getItens());

        pedido.getItens().clear();

        pedido.setValorTotal(BigDecimal.ZERO);

        pedidoRepository.save(pedido);
    }



}
