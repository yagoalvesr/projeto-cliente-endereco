package br.com.pan.application.service.cliente;

import br.com.pan.application.service.endereco.EnderecoService;
import br.com.pan.domain.exception.ClienteNaoEncontradoException;
import br.com.pan.domain.model.entity.cliente.Cliente;
import br.com.pan.domain.model.entity.endereco.Endereco;
import br.com.pan.domain.model.request.cliente.ClienteEnderecoRequest;
import br.com.pan.domain.model.response.cliente.ClienteResponse;
import br.com.pan.domain.repository.cliente.ClienteRepository;
import br.com.pan.infrastructure.mapper.cliente.ClienteMapper;
import br.com.pan.infrastructure.mapper.endereco.EnderecoMapper;
import br.com.pan.infrastructure.util.Util;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {
    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private EnderecoService enderecoService;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Test
    void deveBuscarClientePorCpf() {
        String cpf = "123.456.789-00";
        String cpfNumerico = "12345678900";

        Cliente cliente = new Cliente();
        cliente.setId(UUID.randomUUID());
        cliente.setCpf(cpfNumerico);

        ClienteResponse clienteResponse = new ClienteResponse();
        clienteResponse.setCpf(cpfNumerico);
        clienteResponse.setNomeCompleto("João da Silva");

        when(clienteRepository.findByCpf(cpfNumerico)).thenReturn(Optional.of(cliente));

        try (MockedStatic<Util> utilMock = mockStatic(Util.class);
             MockedStatic<ClienteMapper> mapperMock = mockStatic(ClienteMapper.class)) {

            utilMock.when(() -> Util.apenasNumeros(cpf)).thenReturn(cpfNumerico);
            mapperMock.when(() -> ClienteMapper.clienteEntitytoClienteResponse(cliente)).thenReturn(clienteResponse);

            ClienteResponse resultado = clienteService.buscarClientePorCpf(cpf);

            assertEquals(cpfNumerico, resultado.getCpf());
            assertEquals("João da Silva", resultado.getNomeCompleto());
        }
    }

    @Test
    void deveLancarExcecao_QuandoClienteNaoEncontradoPorCpf() {
        String cpf = "000.000.000-00";
        String cpfNumerico = "00000000000";

        when(clienteRepository.findByCpf(cpfNumerico)).thenReturn(Optional.empty());

        try (MockedStatic<Util> utilMock = mockStatic(Util.class)) {
            utilMock.when(() -> Util.apenasNumeros(cpf)).thenReturn(cpfNumerico);

            assertThrows(ClienteNaoEncontradoException.class, () -> {
                clienteService.buscarClientePorCpf(cpf);
            });
        }
    }

    @Test
    void deveAlterarEnderecoDoCliente() {
        UUID clienteId = UUID.randomUUID();
        ClienteEnderecoRequest request = new ClienteEnderecoRequest();
        request.setClienteId(clienteId);

        Endereco endereco = new Endereco();
        Cliente cliente = new Cliente();
        cliente.setId(clienteId);
        cliente.setEndereco(endereco);

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente));

        try (MockedStatic<EnderecoMapper> enderecoMapperMock = mockStatic(EnderecoMapper.class)) {
            enderecoMapperMock.when(() -> EnderecoMapper.enderecoRequestToEnderecoEntity(endereco, request)).thenCallRealMethod(); // ou apenas mock

            clienteService.alterarEnderecoCliente(request);

            verify(clienteRepository).save(cliente);
        }
    }

    @Test
    void deveLancarExcecao_QuandoClienteNaoEncontradoPorId() {
        UUID clienteId = UUID.randomUUID();
        ClienteEnderecoRequest request = new ClienteEnderecoRequest();
        request.setClienteId(clienteId);

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.empty());

        assertThrows(ClienteNaoEncontradoException.class, () -> {
            clienteService.alterarEnderecoCliente(request);
        });
    }
}
