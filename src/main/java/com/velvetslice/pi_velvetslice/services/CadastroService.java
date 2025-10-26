package com.velvetslice.pi_velvetslice.services;

import com.velvetslice.pi_velvetslice.Dto.CadastroClienteDto;
import com.velvetslice.pi_velvetslice.Repository.UserClienteRepository;
import com.velvetslice.pi_velvetslice.Repository.UserFuncionarioRepository;
import com.velvetslice.pi_velvetslice.Repository.UserRepository;
import com.velvetslice.pi_velvetslice.models.UserCliente;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

        // 2. Criptografar a Senha
        String senhaCriptografada = passwordEncoder.encode(dto.getSenha());

        // 3. Criar a nova entidade UserCliente
        // (Usando o construtor que criamos em UserCliente)
        UserCliente novoCliente = new UserCliente(
                dto.getNome(),
                dto.getEmail(),
                dto.getCpf(),
                senhaCriptografada, // Salva a senha já criptografada
                dto.getTelefone()
        );

        // 4. Salvar o novo cliente (usando o repositório FILHO)
        // O JPA (com a estratégia JOINED) vai salvar os dados
        // nas duas tabelas ('usuarios' e 'clientes') automaticamente.
        return userClienteRepository.save(novoCliente);
    }
}
