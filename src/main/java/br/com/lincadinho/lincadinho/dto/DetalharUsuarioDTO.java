package br.com.lincadinho.lincadinho.dto;

import br.com.lincadinho.lincadinho.model.usuario.Usuario;

public record DetalharUsuarioDTO(Long id, String nome, String email, String role, String foto_url, DetalharOrganizacaoDTO organizacaoDTO) {
    public DetalharUsuarioDTO(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getRole().name(), usuario.getFoto_url() != null ? usuario.getFoto_url() : "" ,usuario.getOrganizacao() != null ? new DetalharOrganizacaoDTO(usuario.getOrganizacao()) : null);
    }
}
