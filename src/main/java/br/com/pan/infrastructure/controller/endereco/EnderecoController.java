package br.com.pan.infrastructure.controller.endereco;

import br.com.pan.domain.model.response.endereco.EnderecoResponse;
import br.com.pan.domain.model.response.endereco.EstadoResponse;
import br.com.pan.domain.model.response.endereco.MunicipioResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/endereco")
public interface EnderecoController {

    @Operation(summary = "Listar endereços por CEP")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca por endereço por CEP concluida com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Erro ao buscar endreço por CEP"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado por CEP"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<EnderecoResponse> buscarEnderecoPorCep(@RequestParam String cep);

    @Operation(summary = "Listar estados")
    @GetMapping("/estados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca por estados concluida com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Erro ao estados municipios"),
            @ApiResponse(responseCode = "404", description = "Estados não encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<List<EstadoResponse>> buscarEstados();

    @Operation(summary = "Listar municipios")
    @GetMapping("/municipios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca por municipios por estado concluida com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Erro ao buscar municipios por estado"),
            @ApiResponse(responseCode = "404", description = "Municipios por estado não encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<List<MunicipioResponse>> buscarMunicipiosPorEstadoId(@RequestParam Long estadoId);

}