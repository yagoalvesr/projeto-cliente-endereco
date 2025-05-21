package br.com.pan.infrastructure.controller.endereco;

import br.com.pan.domain.model.response.endereco.EnderecoResponse;
import br.com.pan.domain.model.response.endereco.EstadoResponse;
import br.com.pan.domain.model.response.endereco.MunicipioResponse;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/endereco")
public interface EnderecoController {

    @Operation(summary = "Listar endereços por CEP")
    @GetMapping
    ResponseEntity<EnderecoResponse> buscarEnderecoPorCep(@RequestParam String cep);

    @Operation(summary = "Listar estados")
    @GetMapping("/estados")
    ResponseEntity<List<EstadoResponse>> buscarEstados();

    @Operation(summary = "Listar municipios")
    @GetMapping("/municipios")
    ResponseEntity<List<MunicipioResponse>> buscarMunicipiosPorEstadoId(@RequestParam Long estadoId);

}