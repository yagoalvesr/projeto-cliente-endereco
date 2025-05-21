package br.com.pan;

import br.com.pan.application.service.cliente.ClienteService;
import br.com.pan.domain.model.entity.cliente.Cliente;
import br.com.pan.domain.repository.cliente.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    ClienteRepository repo;

    @InjectMocks
    ClienteService service;

    @Test
    void dadoClientesCadastrados_quandoListar_entaoRetornaLista() {
        Cliente cliente = new Cliente(UUID.randomUUID(), "João", "Silva", "12345678900", null);
        when(repo.findAll()).thenReturn(List.of(cliente));

        List<Cliente> clientes = service.listarClientes();

        assertEquals(1, clientes.size());
        assertEquals("João", clientes.get(0).getNome());
    }
}