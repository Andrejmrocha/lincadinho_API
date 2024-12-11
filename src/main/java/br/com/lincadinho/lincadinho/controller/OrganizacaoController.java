package br.com.lincadinho.lincadinho.controller;

import br.com.lincadinho.lincadinho.dto.CadastrarOrganizacaoDTO;
import br.com.lincadinho.lincadinho.dto.DetalharOrganizacaoDTO;
import br.com.lincadinho.lincadinho.model.Organizacao;
import br.com.lincadinho.lincadinho.service.OrganizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("organizacao")
public class OrganizacaoController {

    @Autowired
    private OrganizacaoService organizacaoService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity criarOrganizacao(
            @RequestParam("nome") String nome,
            @RequestParam(value = "imagem", required = false)MultipartFile imagem,
            UriComponentsBuilder uriComponentsBuilder) {
        CadastrarOrganizacaoDTO cadastrarOrganizacaoDTO = new CadastrarOrganizacaoDTO(nome, imagem);
        Organizacao organizacao = organizacaoService.criarOrganizacao(cadastrarOrganizacaoDTO);
        var uri = uriComponentsBuilder.path("organizacao/{id}").buildAndExpand(organizacao.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalharOrganizacaoDTO(organizacao));
    }
}
