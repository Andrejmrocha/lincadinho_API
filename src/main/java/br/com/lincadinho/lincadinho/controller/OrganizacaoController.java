package br.com.lincadinho.lincadinho.controller;

import br.com.lincadinho.lincadinho.dto.CadastrarOrganizacaoDTO;
import br.com.lincadinho.lincadinho.dto.DadosListagemOrganizacaoDTO;
import br.com.lincadinho.lincadinho.dto.DetalharOrganizacaoDTO;
import br.com.lincadinho.lincadinho.model.organizacao.Organizacao;
import br.com.lincadinho.lincadinho.service.OrganizacaoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;



@RestController
@RequestMapping("organizacao")
public class OrganizacaoController {

    @Autowired
    private OrganizacaoService organizacaoService;

    @PostMapping(consumes = "multipart/form-data")
    @Transactional
    public ResponseEntity criarOrganizacao(
            @RequestParam("nome") String nome,
            @RequestParam(value = "imagem", required = false)MultipartFile imagem,
            UriComponentsBuilder uriComponentsBuilder) {
        CadastrarOrganizacaoDTO cadastrarOrganizacaoDTO = new CadastrarOrganizacaoDTO(nome, imagem);
        Organizacao organizacao = organizacaoService.criarOrganizacao(cadastrarOrganizacaoDTO);
        var uri = uriComponentsBuilder.path("organizacao/{id}").buildAndExpand(organizacao.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalharOrganizacaoDTO(organizacao));
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<DadosListagemOrganizacaoDTO>>> listarOrganizacoes(@PageableDefault(sort = {"nome"}) Pageable paginacao, PagedResourcesAssembler<DadosListagemOrganizacaoDTO> assembler) {
        var page = organizacaoService.listarOrganizacoes(paginacao).map(DadosListagemOrganizacaoDTO::new);
        PagedModel<EntityModel<DadosListagemOrganizacaoDTO>> pagedModel = assembler.toModel(page);
        return ResponseEntity.ok(pagedModel);
    }

    @PutMapping(consumes = "multipart/form-data")
    @Transactional
    public ResponseEntity atualizarOrganizacao(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "imagem", required = false) MultipartFile imagem,
            @RequestParam("id") Long id) {
        var organizacao = organizacaoService.atualizarOrganizacao(nome, imagem, id);
        return ResponseEntity.ok(new DetalharOrganizacaoDTO(organizacao));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirOrganizacao(@PathVariable Long id) {
        organizacaoService.excluirOrganizacao(id);
        return ResponseEntity.noContent().build();
    }
}
