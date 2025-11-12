package com.velvetslice.pi_velvetslice.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginUserDto {
 @NotBlank
    private String email;

 @NotBlank
    private String senha;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
}
