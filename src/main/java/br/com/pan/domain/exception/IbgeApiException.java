package br.com.pan.domain.exception;

public class IbgeApiException extends RuntimeException {
    public IbgeApiException(String message) {
        super(message);
    }

    public IbgeApiException(String message, Throwable cause) {
        super(message, cause);
    }
}