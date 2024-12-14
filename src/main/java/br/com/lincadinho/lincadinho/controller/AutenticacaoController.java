package br.com.lincadinho.lincadinho.controller;

import br.com.lincadinho.lincadinho.dto.DetalharUsuarioDTO;
import br.com.lincadinho.lincadinho.dto.DetalharUsuarioDTO;
import br.com.lincadinho.lincadinho.dto.LoginDTO;
import br.com.lincadinho.lincadinho.dto.RegistrarUsuarioDTO;
import br.com.lincadinho.lincadinho.model.usuario.Usuario;
import br.com.lincadinho.lincadinho.service.AutenticacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("autenticacao")
public class AutenticacaoController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody @Valid RegistrarUsuarioDTO dados, UriComponentsBuilder uriBuilder) {
        Usuario usuario = autenticacaoService.registrar(dados);
        var uri = uriBuilder.path("autenticacao/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalharUsuarioDTO(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO dados) {
        String token = autenticacaoService.login(dados);
        return ResponseEntity.ok(new tokenDTO(token));
    }

    private record tokenDTO(String token) {
    }
}
