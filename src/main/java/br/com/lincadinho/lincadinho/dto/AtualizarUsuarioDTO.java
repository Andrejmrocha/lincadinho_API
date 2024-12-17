package br.com.lincadinho.lincadinho.dto;

import org.springframework.web.multipart.MultipartFile;

public record AtualizarUsuarioDTO(Long id, String nome, MultipartFile fotoPerfil) {
}
