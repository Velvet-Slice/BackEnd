package com.velvetslice.pi_velvetslice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.velvetslice.pi_velvetslice.dto.ClienteAdminDto;
import com.velvetslice.pi_velvetslice.models.User;
import com.velvetslice.pi_velvetslice.services.ClienteAdminService;

@RestController
@RequestMapping("/admin/clientes")
@CrossOrigin(origins = "*") // Permite acesso de qualquer origem (ajuste para produção)
public class ClienteAdminController {

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
            return ResponseEntity.badRequest().build(); // Retorna 400 com erro
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
