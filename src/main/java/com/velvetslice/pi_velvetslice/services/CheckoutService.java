package com.velvetslice.pi_velvetslice.services;

import com.velvetslice.pi_velvetslice.dto.WhatsAppMessageDTO;
import com.velvetslice.pi_velvetslice.enums.StatusPedido;
import com.velvetslice.pi_velvetslice.models.Pedido;
import com.velvetslice.pi_velvetslice.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class CheckoutService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido checkout(Long clienteId, LocalDate dataEntrega) {

        Pedido pedido = pedidoRepository
                .findByClienteIdAndStatus(clienteId, StatusPedido.ABERTO)
                .orElseThrow(() -> new RuntimeException("Carrinho vazio."));


        LocalDate dataMinima = pedido.getDataPedido().toLocalDate().plusDays(5);

        if (dataEntrega.isBefore(dataMinima)) {
            throw new RuntimeException("A data de entrega deve ser pelo menos 5 dias após o pedido.");
        }

        pedido.setDataEntrega(dataEntrega);

        pedido.setStatus(StatusPedido.EM_NEGOCIACAO);

        return pedidoRepository.save(pedido);
    }

    public String gerarMensagemWhatsapp(Pedido pedido) {

        String numeroDestino = "5511956679056";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String dataPedidoFormatada = pedido.getDataPedido()
                .toLocalDate()
                .format(formatter);

        String dataEntregaFormatada = pedido.getDataEntrega()
                .format(formatter);

        StringBuilder msg = new StringBuilder();
        msg.append(" *Novo Pedido*%0A%0A");
        msg.append(" *Número:* ").append(pedido.getId()).append("%0A");
        msg.append(" *Cliente:* ").append(pedido.getCliente().getNome()).append("%0A");
        msg.append(" *Data do Pedido:* ").append(dataPedidoFormatada).append("%0A");
        msg.append(" *Data da Entrega:* ").append(dataEntregaFormatada).append("%0A");
        msg.append(" *Status:* ").append(pedido.getStatus().toString()).append("%0A%0A");

        msg.append(" *Itens do Pedido:*%0A");

        pedido.getItens().forEach(item -> {
            msg.append("- ")
                    .append(item.getProduto().getNome())
                    .append(" (x").append(item.getQuantidade()).append(") - R$ ")
                    .append(item.getSubtotal())
                    .append("%0A");
        });

        msg.append("%0A *Total:* R$ ").append(pedido.getValorTotal()).append("%0A");

        return "https://wa.me/" + numeroDestino + "?text=" + msg;
    }
}
