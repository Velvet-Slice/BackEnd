package com.velvetslice.pi_velvetslice.controller;

import com.velvetslice.pi_velvetslice.models.Produto;
import com.velvetslice.pi_velvetslice.models.ItemPedido;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.view.RedirectView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarrinhoControllerTest {

    private CarrinhoController controller;
    private HttpSession session;

    @BeforeEach
    void setUp() {
        controller = new CarrinhoController();
        session = new MockHttpSession();
    }

    @Test
    void deveAdicionarItemAoCarrinho() {
        // Cria um item fictício para o carrinho
        List<ItemPedido> carrinho = new ArrayList<>();

        ItemPedido item = new ItemPedido();
        item.setQuantidade(2);
        item.setPrecoUnitario(new BigDecimal("25.50"));

        Produto produto = new Produto();
        produto.setNome("Bolo de Morango");
        item.setProduto(produto);

        carrinho.add(item);
        session.setAttribute("carrinho", carrinho);

        // Executa o método de checkout
        RedirectView redirect = controller.finalizarPedido(session);

        // Verifica se o link gerado contém as informações esperadas
        String url = redirect.getUrl();
        System.out.println("URL gerada: " + url); // debug
        assertTrue(url.contains("Bolo+de+Morango"));
        assertTrue(url.contains("Total"));
        assertTrue(url.contains("wa.me"));
    }

    @Test
    void deveRetornarAvisoSeCarrinhoVazio() {
        RedirectView redirect = controller.finalizarPedido(session);
        String url = redirect.getUrl();
        System.out.println("URL gerada: " + url); // debug

        // Verifica se redireciona para o WhatsApp com mensagem de aviso
        assertTrue(url.contains("N%C3%A3o+h%C3%A1+itens+no+carrinho"));
    }
}
