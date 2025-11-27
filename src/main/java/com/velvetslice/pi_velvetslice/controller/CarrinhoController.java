package com.velvetslice.pi_velvetslice.controller;

import com.velvetslice.pi_velvetslice.dto.AddItemCarrinhoDto;
import com.velvetslice.pi_velvetslice.models.ItemPedido;
import com.velvetslice.pi_velvetslice.models.Pedido;
import com.velvetslice.pi_velvetslice.services.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/carrinho")

public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @PostMapping("/adicionar")
    public ResponseEntity<Pedido> adicionarItem(@RequestBody AddItemCarrinhoDto dto) {
        Pedido pedido = carrinhoService.adicionarItem(dto);
        return ResponseEntity.ok(pedido);
    }

    @GetMapping("/ver")
    public ResponseEntity<Pedido> verCarrinho(@RequestParam Long clienteId) {
        Pedido pedido = carrinhoService.verCarrinho(clienteId);
        return ResponseEntity.ok(pedido);
    }

    @DeleteMapping("/limpar")
    public ResponseEntity<String> limparCarrinho(@RequestParam Long clienteId) {
        carrinhoService.limparCarrinho(clienteId);
        return ResponseEntity.ok("Carrinho limpo com sucesso.");
    }

    @PutMapping("/item/{id}")
    public ResponseEntity<ItemPedido> atualizarQuantidade(@PathVariable Long id, @RequestBody Map<String, Integer> payload) {
        ItemPedido itemAtualizado = carrinhoService.atualizarQuantidade(id, payload.get("quantidade"));
        return ResponseEntity.ok(itemAtualizado);
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<Void> removerItem(@PathVariable Long id) {
        carrinhoService.removerItem(id);
        return ResponseEntity.noContent().build();
    }

}