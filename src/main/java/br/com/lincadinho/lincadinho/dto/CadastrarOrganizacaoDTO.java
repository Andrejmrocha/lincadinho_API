package br.com.lincadinho.lincadinho.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record CadastrarOrganizacaoDTO(@NotBlank String nome, MultipartFile logo) {
}
