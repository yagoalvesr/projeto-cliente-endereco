package br.com.pan.infrastructure.controller.cliente;

import br.com.pan.application.service.cliente.ClienteService;
import br.com.pan.application.service.endereco.EnderecoServiceImpl;
import br.com.pan.domain.model.response.cliente.ClienteResponse;
import br.com.pan.domain.model.response.endereco.EnderecoResponse;
import br.com.pan.domain.model.response.endereco.EstadoResponse;
import br.com.pan.domain.model.response.endereco.MunicipioResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "Operações com clientes")
public class ClienteControllerImpl implements ClienteController {

    private final ClienteService service;

    public ResponseEntity<ClienteResponse> buscarClientePorCpf(@RequestParam String cpf) {
        return ResponseEntity.ok(service.buscarClientePorCpf(cpf));
    }

}