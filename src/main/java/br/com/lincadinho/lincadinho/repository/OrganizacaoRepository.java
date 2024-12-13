package br.com.lincadinho.lincadinho.repository;

import br.com.lincadinho.lincadinho.model.organizacao.Organizacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface OrganizacaoRepository extends JpaRepository<Organizacao, Long> {
    Page<Organizacao> findAllByAtivoTrue(Pageable paginacao);

    Optional<Organizacao> findByIdAndAtivo(Long id, Boolean ativo);
}
