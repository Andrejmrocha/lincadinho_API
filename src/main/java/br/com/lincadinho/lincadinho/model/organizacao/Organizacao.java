package br.com.lincadinho.lincadinho.model.organizacao;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

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

    public Organizacao(String nome, String url) {
        this.nome = nome;
        this.urlImagem = url;
        this.ativo = true;
    }
}
