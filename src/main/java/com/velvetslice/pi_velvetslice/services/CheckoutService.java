package com.velvetslice.pi_velvetslice.services;

import com.velvetslice.pi_velvetslice.dto.PedidoCheckoutDTO;
import com.velvetslice.pi_velvetslice.enums.StatusPedido;
import com.velvetslice.pi_velvetslice.models.ItemPedido;
import com.velvetslice.pi_velvetslice.models.Pedido;
import com.velvetslice.pi_velvetslice.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class CheckoutService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido checkout(PedidoCheckoutDTO dto) {

        if (dto.getClienteId() == null) {
            throw new RuntimeException("ClienteId é obrigatório.");
        }

        if (dto.getDataEntrega() == null || dto.getDataEntrega().isBlank()) {
            throw new RuntimeException("Data de entrega é obrigatória.");
        }

        LocalDate dataEntrega = LocalDate.parse(dto.getDataEntrega());

        Pedido pedido = pedidoRepository
                .findByClienteIdAndStatus(dto.getClienteId(), StatusPedido.ABERTO)
                .orElseThrow(() -> new RuntimeException("Carrinho vazio."));

        LocalDate dataMinima = pedido.getDataPedido().toLocalDate().plusDays(5);

        if (dataEntrega.isBefore(dataMinima)) {
            throw new RuntimeException("A data de entrega deve ser pelo menos 5 dias após o pedido.");
        }

        pedido.setDataEntrega(dataEntrega);

        // 🔥 SALVAR OBSERVAÇÕES AQUI
        if (dto.getObservacoes() != null && !dto.getObservacoes().isBlank()) {
            pedido.setDescricao(dto.getObservacoes());
        }

        pedido.setStatus(StatusPedido.EM_NEGOCIACAO);

        return pedidoRepository.save(pedido);
    }

    public String gerarMensagemWhatsapp(Pedido pedido) {
        try {

            String numeroDestino = "5511983499053";

            DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataPedidoStr = pedido.getDataPedido().format(dataFormatter);
            String dataEntregaStr = pedido.getDataEntrega().format(dataFormatter);

            StringBuilder msg = new StringBuilder();


            msg.append("*Olá! Gostaria de confirmar meu pedido na Velvet Slice*\n\n");

            msg.append("*Pedido #").append(pedido.getId()).append("*\n");
            msg.append("*Data do Pedido:* ").append(dataPedidoStr).append("\n");
            msg.append("*Data de Entrega:* ").append(dataEntregaStr).append("\n");
            msg.append("*Status:* ").append(pedido.getStatus()).append("\n\n");

            msg.append("*Cliente:* ").append(pedido.getCliente().getNome()).append("\n");

            if (pedido.getCliente().getTelefone() != null && !pedido.getCliente().getTelefone().isEmpty()) {
                msg.append("*Contato:* ").append(pedido.getCliente().getTelefone()).append("\n");
            }
            msg.append("*CPF:* ").append(pedido.getCliente().getCpf()).append("\n\n");

            if (pedido.getDescricao() != null && !pedido.getDescricao().isBlank()) {
                msg.append("*Observações:* ").append(pedido.getDescricao()).append("\n\n");
            }
            msg.append("*Itens do Carrinho:*\n");

            for (ItemPedido item : pedido.getItens()) {
                String subtotalFormatado = String.format("%.2f", item.getSubtotal());
                msg.append("▪️ ").append(item.getProduto().getNome())
                        .append(" (x").append(item.getQuantidade()).append(")")
                        .append(" — R$ ").append(subtotalFormatado)
                        .append("\n");
            }

            String totalFormatado = String.format("%.2f", pedido.getValorTotal());
            msg.append("\n*Valor Total: R$ ").append(totalFormatado).append("*\n\n");

            msg.append("Aguardo as opções de pagamento para finalizar! Obrigado(a).");


            String mensagemCodificada = URLEncoder.encode(msg.toString(), StandardCharsets.UTF_8);

            return "https://wa.me/" + numeroDestino + "?text=" + mensagemCodificada;

        } catch (Exception e) {

            return "https://wa.me/5511983499053?text=Erro+ao+gerar+pedido";
        }
    }
}
