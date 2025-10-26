package com.velvetslice.pi_velvetslice.Repository;

import com.velvetslice.pi_velvetslice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    // Método para checar se o e-mail já existe na tabela 'usuarios' //
    Optional<User> findByEmail(String email);

    // Método para checar se o CPF já existe na tabela 'usuarios' //
    Optional<User> findByCpf(String cpf);
}
