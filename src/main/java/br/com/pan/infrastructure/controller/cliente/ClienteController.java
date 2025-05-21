package br.com.pan.infrastructure.controller.cliente;

import br.com.pan.domain.model.response.cliente.ClienteResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/cliente")
public interface ClienteController {

    @Operation(summary = "Buscar cliente por CPF")
    @GetMapping("/{cpf}")
    ResponseEntity<ClienteResponse> buscarClientePorCpf(@PathVariable String cep);

}