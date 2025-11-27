package com.velvetslice.pi_velvetslice.controller;

import com.velvetslice.pi_velvetslice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.velvetslice.pi_velvetslice.dto.CadastroUserDto;
import com.velvetslice.pi_velvetslice.dto.LoginUserDto;
import com.velvetslice.pi_velvetslice.services.CadastroService;
import com.velvetslice.pi_velvetslice.services.LoginService;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://127.0.0.1:5500", methods = {RequestMethod.POST, RequestMethod.OPTIONS})
public class AuthCadastroLoginController {

    @Autowired
    private CadastroService cadastroService;


    @Autowired
    private LoginService loginService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarCliente(@Valid @RequestBody CadastroUserDto cadastroClienteDto) {
        cadastroService.cadastrarNovoCliente(cadastroClienteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente cadastrado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginCliente(@Valid @RequestBody LoginUserDto loginUserDto) {
        User user = loginService.logar(loginUserDto);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login efetuado com sucesso");
        response.put("userId", user.getId());
        response.put("nome", user.getNome());
        response.put("email", user.getEmail());
        if (user.getCliente() != null) {
            response.put("clienteId", user.getCliente().getId());
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
