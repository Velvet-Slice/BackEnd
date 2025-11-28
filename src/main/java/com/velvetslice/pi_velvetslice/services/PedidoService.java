package com.velvetslice.pi_velvetslice.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.velvetslice.pi_velvetslice.dto.PedidoRequestDto;
import com.velvetslice.pi_velvetslice.models.Cliente;
import com.velvetslice.pi_velvetslice.models.Pedido;
import com.velvetslice.pi_velvetslice.repository.ClienteRepository;
import com.velvetslice.pi_velvetslice.repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido salvarOuAtualizar(PedidoRequestDto dto) {
        Pedido pedido;

        if (dto.id() != null) {
            pedido = pedidoRepository.findById(dto.id())
                    .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        } else {
            pedido = new Pedido();
            pedido.setDataPedido(LocalDateTime.now());
        }

        pedido.setDataEntrega(dto.dataEntrega());
        pedido.setDescricao(dto.descricao());
        pedido.setStatus(dto.status());

        // --- LÓGICA DE BUSCA MELHORADA ---
        String nomeBusca = dto.nomeCliente().trim();

        // Tenta buscar usando o "CONTÉM" (mais flexível)
        Optional<Cliente> clienteOpt = clienteRepository.findFirstByNomeContainingIgnoreCase(nomeBusca);

        if (clienteOpt.isPresent()) {
            pedido.setCliente(clienteOpt.get());
        } else {
            // --- BLOCO DE DIAGNÓSTICO (DEBUG) ---
            System.out.println("=== ERRO: CLIENTE NÃO ENCONTRADO ===");
            System.out.println("Nome pesquisado: '" + nomeBusca + "'");
            System.out.println("=== LISTA DE CLIENTES DISPONÍVEIS NO BANCO ===");

            List<Cliente> todos = clienteRepository.findAll();
            if (todos.isEmpty()) {
                System.out.println(">> O BANCO DE DADOS DE CLIENTES ESTÁ VAZIO! <<");
            } else {
                for (Cliente c : todos) {
                    // Imprime ID e Nome entre aspas para vermos espaços ocultos
                    System.out.println("ID: " + c.getId() + " | Nome: '" + c.getNome() + "'");
                }
            }
            System.out.println("==============================================");

            throw new RuntimeException("Cliente não encontrado: " + nomeBusca + ". Verifique o console do VS Code para detalhes.");
        }
        // -------------------------------------

        return pedidoRepository.save(pedido);
    }

    public void excluir(Long id) {
        pedidoRepository.deleteById(id);
    }
}
