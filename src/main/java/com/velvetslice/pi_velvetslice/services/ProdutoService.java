package com.velvetslice.pi_velvetslice.services;

import com.velvetslice.pi_velvetslice.dto.ProdutoAdminDto;
import com.velvetslice.pi_velvetslice.models.Produto;
import com.velvetslice.pi_velvetslice.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    private final Path rootLocation = Paths.get("imagens-upload");

    public ProdutoService() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível criar o diretório de imagens!");
        }
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto salvar(ProdutoAdminDto dto) throws IOException {
        Produto produto = new Produto();
        atualizarDadosProduto(produto, dto);
        return produtoRepository.save(produto);
    }

    public Produto editar(Long id, ProdutoAdminDto dto) throws IOException {
        Optional<Produto> produtoOpt = produtoRepository.findById(id);
        if (produtoOpt.isPresent()) {
            Produto produto = produtoOpt.get();
            atualizarDadosProduto(produto, dto);
            return produtoRepository.save(produto);
        }
        throw new RuntimeException("Produto não encontrado");
    }

    public void excluir(Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Produto não encontrado");
        }
    }

    private void atualizarDadosProduto(Produto produto, ProdutoAdminDto dto) throws IOException {
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setDescricao(dto.getDescricao());

        if (dto.getImagem() != null && !dto.getImagem().isEmpty()) {
            String nomeArquivo = salvarImagem(dto.getImagem());
            produto.setImagemUrl("/imagens-upload/" + nomeArquivo);
        }
    }

    private String salvarImagem(MultipartFile file) throws IOException {
        String nomeArquivo = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Files.copy(file.getInputStream(), this.rootLocation.resolve(nomeArquivo), StandardCopyOption.REPLACE_EXISTING);
        return nomeArquivo;
    }
}