package com.velvetslice.pi_velvetslice.repository;

import com.velvetslice.pi_velvetslice.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
