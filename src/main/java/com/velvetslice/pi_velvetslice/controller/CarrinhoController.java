package com.velvetslice.pi_velvetslice.controller;

import com.velvetslice.pi_velvetslice.models.ItemPedido;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.List;

@Controller
@RequestMapping("/carrinho")

public class CarrinhoController {

    //APAGAR ESTA SESSÃO QUANDO FOR CONECTAR COM O FRONT, FEITO APENAS PARA ILUSTRAÇÃO DE ITEM INSERIDO NO CHECKOUT
    @GetMapping("/adicionarTeste")
    @ResponseBody
    public String adicionarItemTeste(HttpSession session) {
        List<ItemPedido> carrinho = (List<ItemPedido>) session.getAttribute("carrinho");
        if (carrinho == null) {
            carrinho = new java.util.ArrayList<>();
        }

        ItemPedido item = new ItemPedido();
        item.setQuantidade(2);
        item.setPrecoUnitario(new java.math.BigDecimal("25.50"));

        com.velvetslice.pi_velvetslice.models.Produto produto = new com.velvetslice.pi_velvetslice.models.Produto();
        produto.setNome("Bolo de Morango");
        item.setProduto(produto);

        carrinho.add(item);
        session.setAttribute("carrinho", carrinho);

        return "Item adicionado com sucesso! Carrinho agora contém " + carrinho.size() + " item(s).";
    }

    @GetMapping("/checkout")
    public RedirectView finalizarPedido(HttpSession session) {
        List<ItemPedido> itens = (List<ItemPedido>) session.getAttribute("carrinho");

        if (itens == null || itens.isEmpty()) {
            String aviso = URLEncoder.encode(
                    "Olá! Não há itens no carrinho no momento. Por favor, adicione produtos antes de finalizar o pedido",
                    StandardCharsets.UTF_8
            );
            return new RedirectView("https://wa.me/5511964828962?text=" + aviso);
        }

        BigDecimal total = BigDecimal.ZERO;
        StringBuilder mensagem = new StringBuilder("Olá! Gostaria de finalizar meu pedido:\n");

        for (ItemPedido i : itens) {
            BigDecimal subtotal = i.getPrecoUnitario().multiply(BigDecimal.valueOf(i.getQuantidade()));
            mensagem.append("- ")
                    .append(i.getQuantidade())
                    .append("x ")
                    .append(i.getProduto().getNome())
                    .append(" (R$ ")
                    .append(subtotal)
                    .append(")\n");

            total = total.add(subtotal);
        }

        DecimalFormat df = new DecimalFormat("#,##0.00");
        mensagem.append("Total: R$ ").append(df.format(total));

        String texto = URLEncoder.encode(mensagem.toString(), StandardCharsets.UTF_8);
        String link = "https://wa.me/5511964828962?text=" + texto;

        return new RedirectView(link);
    }

}