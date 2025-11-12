package com.velvetslice.pi_velvetslice.controller;

import com.velvetslice.pi_velvetslice.dto.ProdutoDto;
import com.velvetslice.pi_velvetslice.models.Produto;
import com.velvetslice.pi_velvetslice.repository.ProdutoRepository;
import com.velvetslice.pi_velvetslice.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//teste
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Produto> findAll(){
        return service.getProdutos();
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> save(@RequestBody Produto produto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(produto));
    }

    
}
