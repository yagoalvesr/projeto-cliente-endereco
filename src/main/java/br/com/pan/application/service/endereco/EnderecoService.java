package br.com.pan.application.service.endereco;

import br.com.pan.domain.model.response.endereco.EnderecoResponse;
import br.com.pan.domain.model.response.endereco.EstadoResponse;
import br.com.pan.domain.model.response.endereco.MunicipioResponse;
import java.util.List;

public interface EnderecoService {
    EnderecoResponse buscarEnderecoPorCep(String cep);
    List<EstadoResponse> buscarEstados();
    List<MunicipioResponse> buscarMunicipiosPorEstadoId(Long estadoId);
}