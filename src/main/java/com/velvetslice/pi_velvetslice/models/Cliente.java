package com.velvetslice.pi_velvetslice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCli")
    private Long id;

    @NotBlank(message = "O nome do cliente não pode estar em branco.")
    @Size(max = 255)
    @Column(name = "nomeCli", nullable = false)
    private String nome;

    @Past(message = "A data de nascimento deve ser uma data no passado.")
    @Column(name = "data_nascimentoCli")
    private LocalDate dataNascimento;

    @Size(max = 20)
    @Column(name = "telefoneCli")
    private String telefone;

    @NotBlank(message = "O CPF não pode estar em branco.")
    @Column(name = "cpfCli", nullable = false, unique = true)
    private String cpf;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    public Cliente(String nome, LocalDate dataNascimento, String telefone, String cpf) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.cpf = cpf;
    }
}