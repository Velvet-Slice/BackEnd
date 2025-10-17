package com.velvetslice.pi_velvetslice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal; // Importação correta para dinheiro

@Entity
@Table(name = "produtos") // Mapeia esta classe para a tabela 'produtos' do seu SQL
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProd") // Mapeia para a coluna 'idProd'
    private Long id;

    @NotBlank(message = "O nome do produto não pode estar em branco.")
    @Size(max = 255, message = "O nome do produto não pode exceder 255 caracteres.")
    @Column(name = "nomeProd", nullable = false) // Mapeia para 'nomeProd' e exige que não seja nulo no DB
    private String nome;

    @Column(name = "imagem_url") // Mapeia para 'imagem_url'
    private String imagemUrl; // Renomeado para seguir a convenção Java (camelCase)

    @Column(name = "descricaoProd", columnDefinition = "TEXT") // Mapeia para 'descricaoProd' e especifica o tipo TEXT
    private String descricao;

    @NotNull(message = "O preço não pode ser nulo.")
    @Positive(message = "O preço deve ser um valor positivo.")
    @Column(name = "precoProd", nullable = false) // Mapeia para 'precoProd'
    private BigDecimal preco; // CORRIGIDO: Sempre use BigDecimal para dinheiro


    @ManyToOne
    @JoinColumn(name = "idCat") // Mapeia a chave estrangeira 'idCat'
    private Categoria categoria;


    protected Produto() {}


    public Produto(String nome, String imagemUrl, String descricao, BigDecimal preco, Categoria categoria) {
        this.nome = nome;
        this.imagemUrl = imagemUrl;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }

}