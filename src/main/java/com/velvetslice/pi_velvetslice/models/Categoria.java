package com.velvetslice.pi_velvetslice.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//teste
@Entity
@Table(name = "categorias")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCat")
    private Long id;

    @Column(name = "nomeCat", nullable = false)
    private String nome;


    public Categoria(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}