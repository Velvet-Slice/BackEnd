package com.velvetslice.pi_velvetslice.services;

import com.velvetslice.pi_velvetslice.dto.LoginUserDto;
import com.velvetslice.pi_velvetslice.exception.AutenticacaoException;
import com.velvetslice.pi_velvetslice.models.User;
import com.velvetslice.pi_velvetslice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

public class LoginService {

    @Autowired
    private UserRepository userRepository;


    public User logar(LoginUserDto dto) {

        Optional<User> userOptional = userRepository.findByEmail(dto.getEmail());

        if (userOptional.isEmpty()) {
            throw new AutenticacaoException("Email ou senha inválidos.");
        }

        User user = userOptional.get();

        if (dto.getSenha().equals(user.getSenha())) {

            return user;

        } else {
            throw new AutenticacaoException("Email ou senha inválidos.");
        }
    }
}