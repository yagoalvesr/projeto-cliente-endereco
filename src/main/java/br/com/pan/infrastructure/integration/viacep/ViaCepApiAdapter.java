package br.com.pan.infrastructure.integration.viacep;

import br.com.pan.domain.exception.EnderecoNaoEncontracoViaCepException;
import br.com.pan.domain.model.response.endereco.viacep.ViaCepEnderecoResponse;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Slf4j
@Component
@RequiredArgsConstructor
public class ViaCepApiAdapter implements ViaCepApiPort {

    private final WebClient webClient;

    private static final String VIACEP_URL = "https://viacep.com.br/ws";

    @Override
    public ViaCepEnderecoResponse buscarEnderecoPorCep(String cep) {
        try {
            return webClient.get()
                    .uri(VIACEP_URL + "/{cep}/json", cep)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, response -> {
                        log.error("Erro ao buscar CEP {}: status {}", cep, response.statusCode());
                        return Mono.error(new RuntimeException("Erro ao buscar CEP: " + response.statusCode()));
                    })
                    .bodyToMono(ViaCepEnderecoResponse.class)
                    .timeout(Duration.ofSeconds(3))
                    .doOnError(e -> log.error("Exceção ao buscar CEP {}: {}", cep, e.getMessage()))
                    .block();
        } catch (Exception e) {
            log.error("Falha ao buscar endereço pelo CEP {}: {}", cep, e.getMessage());
            throw new EnderecoNaoEncontracoViaCepException("Falha ao buscar endereço pelo CEP: " + cep, e);
        }
    }
}