package com.velvetslice.pi_velvetslice.controller;

import com.velvetslice.pi_velvetslice.dto.PedidoFormDto;
import com.velvetslice.pi_velvetslice.models.Pedido;
import com.velvetslice.pi_velvetslice.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*") // Permite acesso do Frontend
public class PedidoAdminController {

    @Autowired
    private PedidoService pedidoService;

    // Listar todos (GET)
    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodos() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    // Salvar novo (POST)
    @PostMapping
    public ResponseEntity<Pedido> salvar(@RequestBody PedidoFormDto dto) {
        Pedido novoPedido = pedidoService.criarPedidoAdmin(dto);
        return ResponseEntity.ok(novoPedido);
    }

    // Editar existente (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> editar(@PathVariable Long id, @RequestBody PedidoFormDto dto) {
        Pedido pedidoAtualizado = pedidoService.atualizarPedidoAdmin(id, dto);
        return ResponseEntity.ok(pedidoAtualizado);
    }

    // Excluir (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        pedidoService.excluirPedido(id);
        return ResponseEntity.noContent().build();
    }
}
