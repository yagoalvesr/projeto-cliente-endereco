package br.com.pan.infrastructure.controller.cliente;

import br.com.pan.application.service.cliente.ClienteService;
import br.com.pan.domain.model.request.cliente.ClienteEnderecoRequest;
import br.com.pan.domain.model.response.cliente.ClienteResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "Operações com clientes")
public class ClienteControllerImpl implements ClienteController {

    private final ClienteService service;

    public ResponseEntity<ClienteResponse> buscarClientePorCpf(String cpf) {
        return ResponseEntity.ok(service.buscarClientePorCpf(cpf));
    }

    public ResponseEntity<Void> alterarEnderecoCliente(ClienteEnderecoRequest clienteEnderecoRequest) {
        service.alterarEnderecoCliente(clienteEnderecoRequest);
        return ResponseEntity.noContent().build();
    }

}