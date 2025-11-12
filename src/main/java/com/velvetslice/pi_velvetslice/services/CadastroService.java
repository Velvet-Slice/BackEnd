package com.velvetslice.pi_velvetslice.services;

import com.velvetslice.pi_velvetslice.Dto.CadastroClienteDto;
import com.velvetslice.pi_velvetslice.Repository.UserRepository;
import com.velvetslice.pi_velvetslice.models.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//teste
@Service
public class CadastroService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRepository userClienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Transactional
    public User cadastrarNovoCliente(CadastroClienteDto dto) {

        // 1. Validar se CPF ou Email já existem (usando o repositório PAI)
        if (userRepository.findByCpf(dto.getCpf()).isPresent()) {
            throw new RuntimeException("Erro: CPF já está cadastrado.");
        }
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Erro: E-mail já está cadastrado.");
        }
        if (!dto.getSenha().equals(dto.getConfirmarSenha())){
            throw new RuntimeException("Erro: As senhas digitadas são diferentes.");
        }

        String senhaCriptografada = passwordEncoder.encode(dto.getSenha());

        User novoUsuario = new User(
                dto.getNome(),
                dto.getEmail(),
                dto.getCpf(),
                senhaCriptografada
        );

        return userClienteRepository.save(novoUsuario);
    }
}
