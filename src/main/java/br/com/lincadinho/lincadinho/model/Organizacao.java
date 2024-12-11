package br.com.lincadinho.lincadinho.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String url_imagem;
    private boolean ativo;

    public Organizacao(String nome, String url) {
        this.nome = nome;
        this.url_imagem = url;
        this.ativo = true;
    }

}
