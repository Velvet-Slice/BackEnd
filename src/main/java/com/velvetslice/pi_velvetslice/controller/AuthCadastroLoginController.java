package com.velvetslice.pi_velvetslice.controller;


import com.velvetslice.pi_velvetslice.Dto.CadastroClienteDto;
import com.velvetslice.pi_velvetslice.Dto.LoginUserDto;
import com.velvetslice.pi_velvetslice.Repository.UserRepository;
import com.velvetslice.pi_velvetslice.services.CadastroService;
import com.velvetslice.pi_velvetslice.services.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//teste
@RestController
@RequestMapping("/cadastroUsuarios")
public class AuthCadastroLoginController {

    @Autowired
    private UserRepository cadastroRepository;

    @Autowired
    private CadastroService cadastroService;

    @Autowired
    private LoginService loginService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarCliente(@Valid @RequestBody CadastroClienteDto cadastroClienteDto){
        cadastroService.cadastrarNovoCliente(cadastroClienteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente cadastrado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginCliente(@Valid @RequestBody LoginUserDto loginUserDto){
        loginService.logar(loginUserDto);

        return ResponseEntity.status(HttpStatus.OK).body("Login efetuado com sucesso");


    }

}