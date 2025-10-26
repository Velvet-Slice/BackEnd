package com.velvetslice.pi_velvetslice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jdk.jfr.Name;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.aot.generate.GeneratedTypeReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;
import java.util.Collection;


@Entity
@NoArgsConstructor
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User implements UserDetails {

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

    @CPF
    @NotBlank
    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @NotBlank
    @Column(name = "senha", nullable = false)
    private String senha;

    @NotBlank
    @Column(name = "role", nullable = false)
    private String role;

    public User(long id, String nome, String email, String cpf, String senha, String role) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        this.role = role;
    }

    //---------- * Métodos User Details * ----------//

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public String getPassword() {
        // Usaremos a senha para login
        return this.senha;
    }

    @Override
    public String getUsername() {
        // Usaremos o e-mail como "username" para login
        return this.email;
    }

    // Contas sempre ativas
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
