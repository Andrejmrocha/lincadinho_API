package br.com.lincadinho.lincadinho.repository;

import br.com.lincadinho.lincadinho.model.Organizacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface OrganizacaoRepository extends JpaRepository<Organizacao, Long> {
    Page<Organizacao> findAllByAtivoTrue(Pageable paginacao);
}
