package com.velvetslice.pi_velvetslice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.velvetslice.pi_velvetslice.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // ALTERAÇÃO: Mudamos de 'findByNomeIgnoreCase' para 'findFirstByNomeContainingIgnoreCase'
    // Isso gera um SQL do tipo: WHERE LOWER(nome) LIKE LOWER('%Vinicius%') LIMIT 1
    Optional<Cliente> findFirstByNomeContainingIgnoreCase(String nome);
}
