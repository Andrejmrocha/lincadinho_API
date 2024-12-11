package br.com.lincadinho.lincadinho.repository;

import br.com.lincadinho.lincadinho.dto.CadastrarOrganizacaoDTO;
import br.com.lincadinho.lincadinho.model.Organizacao;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class OrganizacaoRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    OrganizacaoRepository organizacaoRepository;

    @Test
    @DisplayName("Deveria devolver uma lista de organizações que estão ativas")
    void findAllByAtivoTrue() {
        CadastrarOrganizacaoDTO empresaUm = new CadastrarOrganizacaoDTO("EmpresaONE", null);
        CadastrarOrganizacaoDTO empresaDois = new CadastrarOrganizacaoDTO("EmpresaTWO", null);
        this.criarOrganizacao(empresaUm);
        this.criarOrganizacao(empresaDois);

        Pageable pageable = PageRequest.of(0, 2);
        Page<Organizacao> page = this.organizacaoRepository.findAllByAtivoTrue(pageable);
        assertThat(page.getTotalElements()).isEqualTo(2);
        assertThat(page.getContent().get(0).getNome()).isEqualTo("EmpresaONE");
    }

    private Organizacao criarOrganizacao(CadastrarOrganizacaoDTO dados) {
        Organizacao organizacao = new Organizacao(dados.nome(), "");
        this.entityManager.persist(organizacao);
        return organizacao;
    }
}