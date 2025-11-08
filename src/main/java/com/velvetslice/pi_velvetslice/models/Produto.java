package com.velvetslice.pi_velvetslice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal; // Importação correta para dinheiro

@Entity
@Table(name = "produtos")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProd")
    private Long id;

    @NotBlank(message = "O nome do produto não pode estar em branco.")
    @Size(max = 255, message = "O nome do produto não pode exceder 255 caracteres.")
    @Column(name = "nomeProd", nullable = false)
    private String nome;

    @Column(name = "imagem_url")
    private String imagemUrl;

    @Column(name = "descricaoProd", columnDefinition = "TEXT")
    private String descricao;

    @NotNull(message = "O preço não pode ser nulo.")
    @Positive(message = "O preço deve ser um valor positivo.")
    @Column(name = "precoProd", nullable = false)
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "idCat")
    private Categoria categoria;


    public Produto(String nome, String imagemUrl, String descricao, BigDecimal preco, Categoria categoria) {
        this.nome = nome;
        this.imagemUrl = imagemUrl;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }
}