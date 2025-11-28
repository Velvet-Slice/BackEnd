package com.velvetslice.pi_velvetslice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.velvetslice.pi_velvetslice.dto.ClienteAdminDto;
import com.velvetslice.pi_velvetslice.models.Cliente;
import com.velvetslice.pi_velvetslice.models.User;
import com.velvetslice.pi_velvetslice.repository.ClienteRepository;
import com.velvetslice.pi_velvetslice.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteAdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<User> listarTodos() {
        return userRepository.findAll();
    }

    @Transactional
    public User salvar(ClienteAdminDto dto) {
        // Validação básica de duplicidade
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado.");
        }
        if (userRepository.findByCpf(dto.getCpf()).isPresent()) {
            throw new RuntimeException("CPF já cadastrado.");
        }

        // 1. Criar o Usuário (Login)
        User user = new User();
        user.setNome(dto.getNome());
        user.setCpf(dto.getCpf());
        user.setEmail(dto.getEmail());
        user.setSenha(dto.getSenha()); // Idealmente, criptografar aqui

        // 2. Criar o Cliente (Dados Pessoais vinculados)
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setCpf(dto.getCpf());
        // cliente.setTelefone(...) // Se houver no DTO futuramente

        // 3. Fazer o vínculo Bidirecional
        cliente.setUser(user);
        user.setCliente(cliente);

        // 4. Salvar (O Cascade no User salvará o Cliente)
        return userRepository.save(user);
    }

    @Transactional
    public User editar(Long id, ClienteAdminDto dto) {
        // Busca o Usuário pelo ID
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));

        // 1. Atualiza dados do Usuário
        user.setNome(dto.getNome());
        user.setCpf(dto.getCpf());
        user.setEmail(dto.getEmail());

        // Só atualiza senha se for enviada
        if (dto.getSenha() != null && !dto.getSenha().trim().isEmpty()) {
            user.setSenha(dto.getSenha());
        }

        // 2. Atualiza dados do Cliente vinculado
        Cliente cliente = user.getCliente();
        if (cliente == null) {
            // Caso raro onde existe user mas não cliente (consertar criando um)
            cliente = new Cliente();
            cliente.setUser(user);
            user.setCliente(cliente);
        }

        // Mantém sincronizado com os dados do User
        cliente.setNome(dto.getNome());
        cliente.setCpf(dto.getCpf());

        return userRepository.save(user);
    }

    @Transactional
    public void excluir(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado.");
        }
        // O CascadeType.ALL na entidade User deve remover o Cliente associado automaticamente
        userRepository.deleteById(id);
    }
}
