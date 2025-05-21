package br.com.pan.infrastructure.controller;

import br.com.pan.application.service.cliente.ClienteService;
import br.com.pan.domain.model.entity.cliente.Cliente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endereco")
@RequiredArgsConstructor
@Tag(name = "Endereços", description = "Operações com endereços")
public class EnderecoController {

    private final ClienteService service;

    @Operation(summary = "Listar todos os clientes")
    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        return ResponseEntity.ok(service.listarClientes());
    }
}