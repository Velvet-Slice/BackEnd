package com.velvetslice.pi_velvetslice.services;

import com.velvetslice.pi_velvetslice.Dto.LoginUserDto;
import com.velvetslice.pi_velvetslice.Exception.AutenticacaoException; // Use sua exceção
import com.velvetslice.pi_velvetslice.Repository.UserRepository;
import com.velvetslice.pi_velvetslice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
//teste
@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User logar(LoginUserDto dto) {

        // Explicação para alguem que não conhece o Optional. Ele é um tipo de "caixa" que pode conter um valor
        // ou estar vazia.
        //// Usamos .isEmpty() ou .isPresent() para checar a caixa antes de usá-la.
        Optional<User> userOptional = userRepository.findByEmail(dto.getEmail());

        if (userOptional.isEmpty()) {
            // Se o e-mail não existe, jogue o erro.
            throw new AutenticacaoException("Email ou senha inválidos.");
        }

        User user = userOptional.get();

        // O passwordEncoder.matches() compara a senha pura do DTO
        // com a senha CRIPTOGRAFADA do banco.
        if (passwordEncoder.matches(dto.getSenha(), user.getSenha())) {

            // Se as senhas batem, "libera" (retorna o usuário)
            return user;

        } else {
            throw new AutenticacaoException("Email ou senha inválidos.");
        }
    }
}