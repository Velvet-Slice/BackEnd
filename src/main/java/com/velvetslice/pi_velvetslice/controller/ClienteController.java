package com.velvetslice.pi_velvetslice.controller;

import com.velvetslice.pi_velvetslice.dto.ClienteAdminDto;
import com.velvetslice.pi_velvetslice.models.User;
import com.velvetslice.pi_velvetslice.services.ClienteAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes") // Ou "/admin/clientes" se preferir separar
@CrossOrigin(origins = "http://127.0.0.1:5500") // Para funcionar com seu Frontend
public class ClienteController {

    @Autowired
    private ClienteAdminService service;

    @GetMapping
    public ResponseEntity<List<User>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PostMapping
    public ResponseEntity<User> salvar(@RequestBody ClienteAdminDto dto) {
        User novoUser = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> editar(@PathVariable Long id, @RequestBody ClienteAdminDto dto) {
        try {
            User userAtualizado = service.editar(id, dto);
            return ResponseEntity.ok(userAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            service.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}