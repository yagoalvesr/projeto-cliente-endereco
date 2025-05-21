package br.com.pan.infrastructure.integration.ibge;

import br.com.pan.domain.exception.IbgeApiException;
import br.com.pan.domain.model.response.endereco.ibge.IbgeEstado;
import br.com.pan.domain.model.response.endereco.ibge.IbgeMunicipio;
import br.com.pan.domain.model.response.endereco.viacep.ViaCepEnderecoResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class IbgeApiAdapter implements IbgeApiPort {

    private final WebClient webClient;

    private static final String IBGE_ESTADOS_URL = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";
    private static final String IBGE_MUNICIPIOS_URL = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/{id}/municipios";

    @Override
    public List<IbgeEstado> listarEstados() {
        try {
            return webClient.get()
                    .uri(IBGE_ESTADOS_URL)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, response -> {
                        log.error("Erro ao buscar estados: status {}", response.statusCode());
                        return Mono.error(new IbgeApiException("Erro ao buscar estados: " + response.statusCode()));
                    })
                    .bodyToFlux(IbgeEstado.class)
                    .collectList()
                    .doOnError(e -> log.error("Exceção ao buscar estados: {}", e.getMessage()))
                    .block();
        } catch (Exception e) {
            log.error("Falha ao buscar estados: {}", e.getMessage());
            throw new IbgeApiException("Falha ao buscar estados", e);
        }
    }

    @Override
    public List<IbgeMunicipio> listarMunicipiosPorEstado(Long estadoId) {
        try {
            return webClient.get()
                    .uri(IBGE_MUNICIPIOS_URL, estadoId)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, response -> {
                        log.error("Erro ao buscar municípios do estado {}: status {}", estadoId, response.statusCode());
                        return Mono.error(new IbgeApiException("Erro ao buscar municípios: " + response.statusCode()));
                    })
                    .bodyToFlux(IbgeMunicipio.class)
                    .collectList()
                    .doOnError(e -> log.error("Exceção ao buscar municípios do estado {}: {}", estadoId, e.getMessage()))
                    .block();
        } catch (Exception e) {
            log.error("Falha ao buscar municípios do estado {}: {}", estadoId, e.getMessage());
            throw new IbgeApiException("Falha ao buscar municípios do estado: " + estadoId, e);
        }
    }

}