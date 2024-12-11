package br.com.lincadinho.lincadinho.dto;

import br.com.lincadinho.lincadinho.model.Organizacao;

public record DadosListagemOrganizacaoDTO(Long id, String nome, String url_imagem) {
    public DadosListagemOrganizacaoDTO(Organizacao organizacao) {
        this(organizacao.getId(), organizacao.getNome(), organizacao.getUrl_imagem());
    }
}
