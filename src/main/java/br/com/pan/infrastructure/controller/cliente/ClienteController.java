package br.com.pan.infrastructure.controller.cliente;

import br.com.pan.domain.model.request.cliente.ClienteEnderecoRequest;
import br.com.pan.domain.model.response.cliente.ClienteResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/cliente")
public interface ClienteController {

    @Operation(summary = "Buscar cliente por CPF")
    @GetMapping("/{cpf}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca por cliente concluida com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Erro ao buscar cliente"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<ClienteResponse> buscarClientePorCpf(@PathVariable String cep);

    @Operation(summary = "Alterar endereço de um cliente")
    @PutMapping("/endereco")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Edição concluida com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Erro ao editar cliente"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<Void> alterarEnderecoCliente(@RequestBody ClienteEnderecoRequest clienteEnderecoRequest);

}