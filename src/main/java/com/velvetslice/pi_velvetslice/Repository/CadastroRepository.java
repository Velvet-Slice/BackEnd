package com.velvetslice.pi_velvetslice.repository;

import com.velvetslice.pi_velvetslice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CadastroRepository extends JpaRepository <User, Long>{

    Optional<User> findByEmail(String email);

    Optional<User> findBypassword(String password);
}
