package br.com.lincadinho.lincadinho.service;

import br.com.lincadinho.lincadinho.dto.CadastrarOrganizacaoDTO;
import br.com.lincadinho.lincadinho.dto.DetalharOrganizacaoDTO;
import br.com.lincadinho.lincadinho.model.Organizacao;
import br.com.lincadinho.lincadinho.repository.OrganizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class OrganizacaoService {
    @Autowired
    private OrganizacaoRepository organizacaoRepository;

    public Organizacao criarOrganizacao(CadastrarOrganizacaoDTO dados) {
        String url = null;

        if (dados.logo() != null) {
            url = this.uploadImagem(dados.logo());
        }

        Organizacao organizacao = new Organizacao(dados.nome(), url);
        return organizacaoRepository.save(organizacao);
    }

    private String uploadImagem(MultipartFile imagem) {
        return "";
    }
}
