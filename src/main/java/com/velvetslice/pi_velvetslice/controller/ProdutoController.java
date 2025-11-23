package com.velvetslice.pi_velvetslice.controller;

import com.velvetslice.pi_velvetslice.dto.ProdutoAdminDto;
import com.velvetslice.pi_velvetslice.models.Produto;
import com.velvetslice.pi_velvetslice.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public ResponseEntity<List<Produto>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // Nota: Usamos @ModelAttribute aqui para suportar Upload de Arquivos via
    // FormData
    @PostMapping(consumes = { "multipart/form-data" })
    public ResponseEntity<Produto> salvar(@ModelAttribute ProdutoAdminDto dto) {
        try {
            Produto novoProduto = service.salvar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Para edição com arquivo, o método PUT via HTML form às vezes é problemático.
    // Vamos aceitar POST neste endpoint específico ou configurar o JS para enviar
    // corretamente.
    // Padrão REST com FormData geralmente é feito via POST com campo _method ou
    // direto PUT se o cliente suportar.
    // Para simplificar, faremos via POST mapeado na URL com ID, ou usamos o JS para
    // forçar PUT.
    // O Spring aceita @ModelAttribute no PUT se configurado, mas vamos usar uma
    // abordagem robusta:
    @PutMapping(value = "/{id}", consumes = { "multipart/form-data" })
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