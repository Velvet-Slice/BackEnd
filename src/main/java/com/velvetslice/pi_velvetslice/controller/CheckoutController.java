package com.velvetslice.pi_velvetslice.controller;

import com.velvetslice.pi_velvetslice.models.Pedido;
import com.velvetslice.pi_velvetslice.services.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pedidos")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @PostMapping("/checkout")
    public ResponseEntity<Map<String, Object>> checkout(
            @RequestParam Long clienteId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataEntrega
    ) {

        Pedido pedido = checkoutService.checkout(clienteId, dataEntrega);

        String whatsappUrl = checkoutService.gerarMensagemWhatsapp(pedido);

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("pedido", pedido);
        resposta.put("whatsappUrl", whatsappUrl);

        return ResponseEntity.ok(resposta);
    }

}
