package br.com.pan.application.service.endereco;

import br.com.pan.domain.model.response.endereco.EnderecoResponse;
import br.com.pan.domain.model.response.endereco.EstadoResponse;
import br.com.pan.domain.model.response.endereco.ibge.IbgeEstado;
import br.com.pan.domain.model.response.endereco.viacep.ViaCepEnderecoResponse;
import br.com.pan.domain.repository.endereco.EnderecoRepository;
import br.com.pan.infrastructure.integration.ibge.IbgeApiPort;
import br.com.pan.infrastructure.integration.viacep.ViaCepApiPort;
import br.com.pan.infrastructure.mapper.EnderecoMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoServiceImpl implements EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final ViaCepApiPort viaCepApiPort;
    private final IbgeApiPort ibgeApiPort;


    public EnderecoResponse buscarEnderecoPorCep(String cep) {
        ViaCepEnderecoResponse viaCepResponse = viaCepApiPort.buscarEnderecoPorCep(cep);
        return EnderecoMapper.fromViaCepEnderecoResponse(viaCepResponse);
    }

    public List<EstadoResponse> buscarEstados() {
        List<IbgeEstado> estados = ibgeApiPort.listarEstados();
        return EnderecoMapper.fromViaCepEnderecoResponse(viaCepResponse);
    }
}