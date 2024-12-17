package br.com.lincadinho.lincadinho.controller;

import br.com.lincadinho.lincadinho.dto.AtualizarUsuarioDTO;
import br.com.lincadinho.lincadinho.dto.DetalharUsuarioDTO;
import br.com.lincadinho.lincadinho.model.usuario.Usuario;
import br.com.lincadinho.lincadinho.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity detalharUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarUsuario(id);
        return ResponseEntity.ok(new DetalharUsuarioDTO(usuario));
    }

    @PutMapping(consumes = "multipart/form-data")
    @Transactional
    public ResponseEntity atualizarDadosUsuario(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "foto", required = false)MultipartFile foto,
            @RequestParam(value = "id") Long id
            ) {
        AtualizarUsuarioDTO atualizarUsuarioDTO = new AtualizarUsuarioDTO(id, nome, foto);
        Usuario usuarioAtualizado = usuarioService.atualizarDadosUsuario(atualizarUsuarioDTO);
        return ResponseEntity.ok().body(new DetalharUsuarioDTO(usuarioAtualizado));
    }

}
