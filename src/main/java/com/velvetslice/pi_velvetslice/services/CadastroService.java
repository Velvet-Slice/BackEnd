package com.velvetslice.pi_velvetslice.services;

import com.velvetslice.pi_velvetslice.dto.CadastroUserDto;
import com.velvetslice.pi_velvetslice.exception.AutenticacaoException;
import com.velvetslice.pi_velvetslice.models.Cliente;
import com.velvetslice.pi_velvetslice.models.User;
import com.velvetslice.pi_velvetslice.repository.ClienteRepository;
import com.velvetslice.pi_velvetslice.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public User cadastrarNovoCliente(CadastroUserDto dto) {

        if (userRepository.findByCpf(dto.getCpf()).isPresent()) {
            throw new AutenticacaoException("Erro: CPF já está cadastrado.");
        }
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new AutenticacaoException("Erro: E-mail já está cadastrado.");
        }
        if (!dto.getSenha().equals(dto.getConfirmarSenha())) {
            throw new AutenticacaoException("Erro: As senhas digitadas são diferentes.");
        }

        // 1. Criar usuário
        User novoUsuario = new User(
                dto.getNome(),
                dto.getEmail(),
                dto.getCpf(),
                dto.getSenha()
        );

        novoUsuario = userRepository.save(novoUsuario);

        // 3. Criar cliente vinculado automaticamente
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setCpf(dto.getCpf());
        cliente.setUser(novoUsuario);
        cliente.setTelefone(null);
        cliente.setDataNascimento(null);

        novoUsuario.setCliente(cliente);

        clienteRepository.save(cliente);

        return novoUsuario;
    }
}