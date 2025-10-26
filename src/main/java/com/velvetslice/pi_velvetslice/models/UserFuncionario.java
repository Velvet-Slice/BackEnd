package com.velvetslice.pi_velvetslice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "funcionarios")
public class UserFuncionario extends User {

    @Column(name = "matricula", unique = true)
    private String matricula;

    public UserFuncionario(String nome, String email, String cpf, String senha, String matricula) {

        // Define o "role" fixo para "ROLE_ADMIN" (ou "ROLE_FUNCIONARIO")
        super(0L, nome, email, cpf, senha, "ROLE_ADMIN");

        this.matricula = matricula;
    }
}