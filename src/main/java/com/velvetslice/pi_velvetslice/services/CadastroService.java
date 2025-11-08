package com.velvetslice.pi_velvetslice.services;

import com.velvetslice.pi_velvetslice.Dto.CadastroClienteDto;
import com.velvetslice.pi_velvetslice.Repository.UserClienteRepository;
import com.velvetslice.pi_velvetslice.Repository.UserFuncionarioRepository;
import com.velvetslice.pi_velvetslice.Repository.UserRepository;
import com.velvetslice.pi_velvetslice.models.User;
import com.velvetslice.pi_velvetslice.models.UserCliente;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class CadastroService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserClienteRepository userClienteRepository;

    @Autowired
    private UserFuncionarioRepository userFuncionarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Transactional
    public UserCliente cadastrarNovoCliente(CadastroClienteDto dto) {

        // 1. Validar se CPF ou Email já existem (usando o repositório PAI)
        if (userRepository.findByCpf(dto.getCpf()).isPresent()) {
            throw new RuntimeException("Erro: CPF já está cadastrado.");
        }
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Erro: E-mail já está cadastrado.");
        }

        String senhaCriptografada = passwordEncoder.encode(dto.getSenha());

        UserCliente novoUsuario = new UserCliente(
                dto.getNome(),
                dto.getEmail(),
                dto.getCpf(),
                dto.getSenha(),
                dto.getTelefone());

        return userClienteRepository.save(novoUsuario);
    }
}
