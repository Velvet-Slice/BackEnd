package com.velvetslice.pi_velvetslice.repository;

import com.velvetslice.pi_velvetslice.enums.StatusPedido;
import com.velvetslice.pi_velvetslice.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Optional<Pedido> findByClienteIdAndStatus(Long clienteId, StatusPedido status);

}
