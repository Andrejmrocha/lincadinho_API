package br.com.lincadinho.lincadinho.infra.exception;

public class EmailJaExistenteException extends RuntimeException {
    public EmailJaExistenteException(String mensagem) {
        super(mensagem);
    }
}
