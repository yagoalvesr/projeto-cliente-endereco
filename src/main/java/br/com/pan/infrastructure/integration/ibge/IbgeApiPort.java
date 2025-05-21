package br.com.pan.infrastructure.integration.ibge;

import br.com.pan.domain.model.response.endereco.ibge.IbgeEstado;
import br.com.pan.domain.model.response.endereco.ibge.IbgeMunicipio;
import java.util.List;

public interface IbgeApiPort {
    List<IbgeEstado> listarEstados();
    List<IbgeMunicipio> listarMunicipiosPorEstado(Long estadoId);
}