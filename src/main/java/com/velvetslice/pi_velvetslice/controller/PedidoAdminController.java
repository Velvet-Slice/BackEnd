package com.velvetslice.pi_velvetslice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.velvetslice.pi_velvetslice.dto.PedidoRequestDto;
import com.velvetslice.pi_velvetslice.models.Pedido;
import com.velvetslice.pi_velvetslice.services.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*") // Permite acesso do HTML local
public class PedidoAdminController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> listar() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Pedido> criar(@RequestBody PedidoRequestDto dto) {
        return ResponseEntity.ok(pedidoService.salvarOuAtualizar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizar(@PathVariable Long id, @RequestBody PedidoRequestDto dto) {
        // Garante que o ID do path seja usado
        PedidoRequestDto dtoAtualizado = new PedidoRequestDto(id, dto.nomeCliente(), dto.dataEntrega(), dto.descricao(), dto.status(), dto.pago());
        return ResponseEntity.ok(pedidoService.salvarOuAtualizar(dtoAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        pedidoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
