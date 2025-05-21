package br.com.pan.infrastructure.config;

import br.com.pan.domain.exception.ClienteNaoEncontradoException;
import br.com.pan.domain.exception.EnderecoNaoEncontracoViaCepException;
import br.com.pan.domain.exception.IbgeApiException;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler implements CommandLineRunner {

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<Object> handleClienteNaoEncontrado(ClienteNaoEncontradoException ex) {
        Map<String, Object> body = criarBody(HttpStatus.NOT_FOUND, "Cliente não encontrado", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(EnderecoNaoEncontracoViaCepException.class)
    public ResponseEntity<Object> handleEnderecoNaoEncontracoViaCepException(EnderecoNaoEncontracoViaCepException ex) {
        Map<String, Object> body = criarBody(HttpStatus.NOT_FOUND, "Endereço não encontrado", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(IbgeApiException.class)
    public ResponseEntity<Object> handleIbgeApiException(IbgeApiException ex) {
        Map<String, Object> body = criarBody(HttpStatus.BAD_REQUEST, "Não foi possível consluir operação", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    private static Map<String, Object> criarBody(HttpStatus status, String error, String ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", OffsetDateTime.now());
        body.put("status", status.value());
        body.put("error", error);
        body.put("message", ex);
        return body;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
