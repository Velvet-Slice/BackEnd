package com.velvetslice.pi_velvetslice.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastroClienteDto {
    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String cpf;

    @NotBlank
    @Size(min = 6)
    private String senha;

    @NotBlank
    @Size(min =6)
    private String confirmarSenha;

    @Size(max = 20)
    private String telefone;
}
