package br.com.lincadinho.lincadinho.service;

import br.com.lincadinho.lincadinho.dto.CadastrarOrganizacaoDTO;
import br.com.lincadinho.lincadinho.dto.DetalharOrganizacaoDTO;
import br.com.lincadinho.lincadinho.model.Organizacao;
import br.com.lincadinho.lincadinho.repository.OrganizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class OrganizacaoService {

    @Autowired
    private OrganizacaoRepository organizacaoRepository;

    @Autowired
    private AWSService awsService;

    public Organizacao criarOrganizacao(CadastrarOrganizacaoDTO dados) {
        String url = null;

        if (dados.logo() != null) {
            url = this.awsService.uploadImagem(dados.logo());
        }

        Organizacao organizacao = new Organizacao(dados.nome(), url);
        return organizacaoRepository.save(organizacao);
    }

    public Page<Organizacao> listarOrganizacoes(Pageable paginacao) {
        return organizacaoRepository.findAllByAtivoTrue(paginacao);
    }
}
