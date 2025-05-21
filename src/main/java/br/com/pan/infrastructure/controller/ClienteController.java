package br.com.pan.infrastructure.controller;

import br.com.pan.application.service.cliente.ClienteService;
import br.com.pan.domain.model.entity.cliente.Cliente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "Operações com clientes")
public class ClienteController {

    private final ClienteService service;

    @Operation(summary = "Listar todos os clientes")
    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        return ResponseEntity.ok(service.listarClientes());
    }
}