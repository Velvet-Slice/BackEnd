package com.velvetslice.pi_velvetslice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCli")
    private Long id;

    @NotBlank(message = "O nome do cliente não pode estar em branco.")
    @Size(max = 255)
    @Column(name = "nomeCli", nullable = false) // Mapeia para a coluna 'nomeCli'
    private String nome;

    @Size(max = 20)
    @Column(name = "telefoneCli")
    private String telefone;

    @NotBlank(message = "O CPF não pode estar em branco.")
    @Column(name = "cpfCli", nullable = false, unique = true)
    private String cpf;


    public Cliente(String nome,String telefone, String cpf, String senha) {
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
    }


}