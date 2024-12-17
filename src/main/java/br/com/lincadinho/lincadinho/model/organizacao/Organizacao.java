package br.com.lincadinho.lincadinho.model.organizacao;


import br.com.lincadinho.lincadinho.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Organizacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String urlImagem;
    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "usuario_administrador_id")
    private Usuario administrador;

    public Organizacao(String nome, String url, Usuario usuarioAdministrador) {
        this.nome = nome;
        this.urlImagem = url;
        this.ativo = true;
        this.administrador = usuarioAdministrador;
    }
}
