package br.com.pan.infrastructure.controller.endereco;

import br.com.pan.application.service.endereco.EnderecoService;
import br.com.pan.domain.model.response.endereco.EnderecoResponse;
import br.com.pan.domain.model.response.endereco.EstadoResponse;
import br.com.pan.domain.model.response.endereco.MunicipioResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Endereços", description = "Operações com endereços")
public class EnderecoControllerImpl implements EnderecoController{

    private final EnderecoService service;

    public ResponseEntity<EnderecoResponse> buscarEnderecoPorCep(@RequestParam String cep) {
        return ResponseEntity.ok(service.buscarEnderecoPorCep(cep));
    }

    public ResponseEntity<List<EstadoResponse>> buscarEstados() {
        return ResponseEntity.ok(service.buscarEstados());
    }

    public ResponseEntity<List<MunicipioResponse>> buscarMunicipiosPorEstadoId(@RequestParam Long estadoId) {
        return ResponseEntity.ok(service.buscarMunicipiosPorEstadoId(estadoId));
    }
}