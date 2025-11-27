package com.velvetslice.pi_velvetslice.controller;

import com.velvetslice.pi_velvetslice.dto.ProdutoAdminDto;
import com.velvetslice.pi_velvetslice.models.Produto;
import com.velvetslice.pi_velvetslice.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public ResponseEntity<List<Produto>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Produto> salvar(@ModelAttribute ProdutoAdminDto dto) {
        try {
            Produto novoProduto = service.salvar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Produto> editar(@PathVariable Long id, @ModelAttribute ProdutoAdminDto dto) {
        try {
            Produto produtoAtualizado = service.editar(id, dto);
            return ResponseEntity.ok(produtoAtualizado);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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