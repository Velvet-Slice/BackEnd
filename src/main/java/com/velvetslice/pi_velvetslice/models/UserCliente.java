package com.velvetslice.pi_velvetslice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "clientes")
public class UserCliente extends User {

    @Size(max = 20)
    @Column(name = "telefoneCli")
    private String telefone;

    // Construtor para o CadastroService
    public UserCliente(String nome, String email, String cpf, String senha, String telefone) {


       // Define o "role" fixo para "ROLE_CLIENTE"
        super(0L, nome, email, cpf, senha, "ROLE_CLIENTE"); // 0L para o ID, pois será gerado


        this.telefone = telefone;
    }
}