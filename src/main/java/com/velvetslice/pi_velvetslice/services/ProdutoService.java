package com.velvetslice.pi_velvetslice.services;

import com.velvetslice.pi_velvetslice.dto.ProdutoDto;
import com.velvetslice.pi_velvetslice.exception.ProdutoNulloException;
import com.velvetslice.pi_velvetslice.models.Produto;
import com.velvetslice.pi_velvetslice.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//teste
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoDto save(Produto produto){
        if (produto == null) throw new ProdutoNulloException("Nenhum produto encontrado.");
        produtoRepository.save(produto);
        return new ProdutoDto(produto.getNome());
    }

    public List<Produto> getProdutos(){
        return produtoRepository.findAll();
    }
}
