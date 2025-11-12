package com.velvetslice.pi_velvetslice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@NoArgsConstructor
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column
    private String nome;

    @Email(message = "Email inválido")
    @NotBlank
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @CPF(message = "Cpf inválido")
    @NotBlank
    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @NotBlank
    @Column(name = "senha", nullable = false)
    private String senha;


    public User(String nome, String email, String cpf, String senha) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
    }
}


