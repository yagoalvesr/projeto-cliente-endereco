package br.com.pan.infrastructure.controller.endereco;

import br.com.pan.application.service.endereco.EnderecoServiceImpl;
import br.com.pan.domain.model.response.endereco.EnderecoResponse;
import br.com.pan.domain.model.response.endereco.EstadoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endereco")
@RequiredArgsConstructor
@Tag(name = "Endereços", description = "Operações com endereços")
public class EnderecoControllerImpl implements EnderecoController{

    private final EnderecoServiceImpl service;

    @Operation(summary = "Listar endereços por CEP")
    @GetMapping
    public ResponseEntity<EnderecoResponse> buscarEnderecoPorCep(@RequestParam String cep) {
        return ResponseEntity.ok(service.buscarEnderecoPorCep(cep));
    }

    @Operation(summary = "Listar estados")
    @GetMapping
    public ResponseEntity<List<EstadoResponse>> buscarEstados() {
        return ResponseEntity.ok(service.buscarEstados());
    }
}