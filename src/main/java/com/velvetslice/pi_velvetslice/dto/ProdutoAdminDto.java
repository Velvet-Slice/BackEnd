package com.velvetslice.pi_velvetslice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoAdminDto {
    private Long id;
    private String nome;
    private BigDecimal preco;
    private String descricao;

    // Campo para receber o arquivo do input type="file"
    private MultipartFile imagem;
}