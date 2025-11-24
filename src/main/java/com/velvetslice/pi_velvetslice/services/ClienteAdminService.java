package com.velvetslice.pi_velvetslice.services;

import com.velvetslice.pi_velvetslice.dto.ClienteAdminDto;
import com.velvetslice.pi_velvetslice.models.User;
import com.velvetslice.pi_velvetslice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteAdminService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listarTodos() {
        return userRepository.findAll();
    }

    @Transactional
    public User salvar(ClienteAdminDto dto) {
        // Verifica se já existe email ou CPF (lógica simplificada)
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado.");
        }

        User user = new User();
        user.setNome(dto.getNome());
        user.setCpf(dto.getCpf());
        user.setEmail(dto.getEmail());
        user.setSenha(dto.getSenha()); // Em produção, use BCrypt para criptografar

        return userRepository.save(user);
    }

    @Transactional
    public User editar(Long id, ClienteAdminDto dto) {
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setNome(dto.getNome());
            user.setCpf(dto.getCpf());
            user.setEmail(dto.getEmail());

            // Só atualiza a senha se vier preenchida no formulário
            if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
                user.setSenha(dto.getSenha());
            }

            return userRepository.save(user);
        } else {
            throw new RuntimeException("Cliente não encontrado com ID: " + id);
        }
    }

    @Transactional
    public void excluir(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cliente não encontrado para exclusão.");
        }
    }
}