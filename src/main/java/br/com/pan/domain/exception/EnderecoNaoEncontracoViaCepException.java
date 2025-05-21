package br.com.pan.domain.exception;

public class EnderecoNaoEncontracoViaCepException extends RuntimeException {
    public EnderecoNaoEncontracoViaCepException(String mensagem) {
        super(mensagem);
    }

    public EnderecoNaoEncontracoViaCepException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
