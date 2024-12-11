package br.com.lincadinho.lincadinho.dto;


import br.com.lincadinho.lincadinho.model.Organizacao;

public record DetalharOrganizacaoDTO(Long id, String nome, String url_imagem, boolean ativo) {
    public DetalharOrganizacaoDTO(Organizacao organizacao) {
        this(organizacao.getId(), organizacao.getNome(), organizacao.getUrl_imagem(), organizacao.isAtivo());
    }
}
