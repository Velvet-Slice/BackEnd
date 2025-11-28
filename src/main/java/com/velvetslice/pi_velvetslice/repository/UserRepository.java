package com.velvetslice.pi_velvetslice.repository;

import com.velvetslice.pi_velvetslice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Locale;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByCpf(String cpf);

    Optional<User> findBySenha(String senha);

    Optional<User> findByEmail(String email);
}
