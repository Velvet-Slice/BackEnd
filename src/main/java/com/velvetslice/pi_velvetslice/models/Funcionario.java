package com.velvetslice.pi_velvetslice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "funcionarios")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFuncionario")
    private Long id;

    @NotBlank(message = "O nome do funcionário не pode estar em branco.")
    @Size(max = 255)
    @Column(name = "nomeFunc", nullable = false)
    private String nome;

    @NotBlank(message = "O CPF не pode estar em branco.")
    @Column(name = "cpfFunc", nullable = false, unique = true)
    private String cpf;

    @Size(max = 20)
    @Column(name = "telefoneFunc")
    private String telefone;

    @NotBlank(message = "O email не pode estar em branco.")
    @Email(message = "O formato do email é inválido.")
    @Size(max = 255)
    @Column(name = "emailFunc", nullable = false, unique = true)
    private String email;

    public Funcionario(String nome, String cpf, String telefone, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
    }


}