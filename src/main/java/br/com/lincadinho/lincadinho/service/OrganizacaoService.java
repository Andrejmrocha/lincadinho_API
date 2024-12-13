package br.com.lincadinho.lincadinho.service;

import br.com.lincadinho.lincadinho.dto.CadastrarOrganizacaoDTO;
import br.com.lincadinho.lincadinho.model.organizacao.Organizacao;
import br.com.lincadinho.lincadinho.repository.OrganizacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;


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

    public Organizacao atualizarOrganizacao(String nome, MultipartFile imagem, Long id) {
        Optional<Organizacao> organizacaoOptional = organizacaoRepository.findByIdAndAtivo(id, true);
        if (organizacaoOptional.isPresent()) {
            if (imagem != null) {
                boolean imagemDeletada = (organizacaoOptional.get().getUrlImagem() == null) || awsService.deletarImagem(organizacaoOptional.get().getUrlImagem());
                if (imagemDeletada) {
                    String novaUrlImagem = awsService.uploadImagem(imagem);
                    if (novaUrlImagem != null) {
                        organizacaoOptional.get().setUrlImagem(novaUrlImagem);
                    }
                }
            }

            if (nome != null) {
                organizacaoOptional.get().setNome(nome);
            }
            return (organizacaoOptional.get());
        } else {
            throw new EntityNotFoundException("Organização não encontrada para o ID: " + id);
        }
    }

    public void excluirOrganizacao(Long id) {
        Optional<Organizacao> organizacaoOptional = organizacaoRepository.findByIdAndAtivo(id, true);
        if (organizacaoOptional.isPresent()) {
            organizacaoOptional.get().setAtivo(false);
        } else {
            throw new EntityNotFoundException("Organização não encontrada para o ID: " + id);
        }
    }

    public Organizacao buscarOrganizacao(Long id) {
        Optional<Organizacao> organizacaoOptional = organizacaoRepository.findByIdAndAtivo(id, true);
        if (organizacaoOptional.isPresent()) {
            return organizacaoOptional.get();
        } else {
            throw new EntityNotFoundException("Organização não encontrada para o ID: " + id);
        }
    }
}
