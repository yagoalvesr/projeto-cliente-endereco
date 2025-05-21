package br.com.pan.application.service.endereco;

import br.com.pan.domain.model.response.endereco.EnderecoResponse;
import br.com.pan.domain.model.response.endereco.viacep.ViaCepEnderecoResponse;
import br.com.pan.domain.repository.endereco.EnderecoRepository;
import br.com.pan.infrastructure.integration.ibge.IbgeApiPort;
import br.com.pan.infrastructure.integration.viacep.ViaCepApiPort;
import br.com.pan.infrastructure.mapper.endereco.EnderecoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnderecoServiceTest {
    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private ViaCepApiPort viaCepApiPort;

    @Mock
    private IbgeApiPort ibgeApiPort;

    @InjectMocks
    private EnderecoServiceImpl enderecoService;

    @Test
    void deveBuscarEnderecoPorCep() {
        String cep = "01001-000";

        ViaCepEnderecoResponse viaCepResponse = new ViaCepEnderecoResponse();
        viaCepResponse.setCep(cep);
        viaCepResponse.setLogradouro("Praça da Sé");

        EnderecoResponse enderecoResponse = new EnderecoResponse();
        enderecoResponse.setCep(cep);
        enderecoResponse.setLogradouro("Praça da Sé");

        when(viaCepApiPort.buscarEnderecoPorCep(cep)).thenReturn(viaCepResponse);

        try (MockedStatic<EnderecoMapper> mockedMapper = mockStatic(EnderecoMapper.class)) {
            mockedMapper.when(() -> EnderecoMapper.enderecoViaCepToEnderecoResponse(viaCepResponse)).thenReturn(enderecoResponse);

            EnderecoResponse result = enderecoService.buscarEnderecoPorCep(cep);

            assertEquals("01001-000", result.getCep());
            assertEquals("Praça da Sé", result.getLogradouro());
        }
    }
}
