package br.com.pan.application.service.endereco;

import br.com.pan.domain.model.response.endereco.EnderecoResponse;
import br.com.pan.domain.model.response.endereco.EstadoResponse;
import br.com.pan.domain.model.response.endereco.MunicipioResponse;
import br.com.pan.domain.model.response.endereco.ibge.IbgeEstado;
import br.com.pan.domain.model.response.endereco.ibge.IbgeMunicipio;
import br.com.pan.domain.model.response.endereco.viacep.ViaCepEnderecoResponse;
import br.com.pan.domain.repository.endereco.EnderecoRepository;
import br.com.pan.infrastructure.integration.ibge.IbgeApiPort;
import br.com.pan.infrastructure.integration.viacep.ViaCepApiPort;
import br.com.pan.infrastructure.mapper.endereco.EnderecoMapper;
import br.com.pan.infrastructure.mapper.endereco.EstadoMapper;
import br.com.pan.infrastructure.mapper.endereco.MunicipioMapper;
import br.com.pan.infrastructure.util.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoServiceImpl implements EnderecoService {

    private static final Long ID_ESTADO_SP = 35L;
    private static final Long ID_ESTADO_RJ = 33L;

    private final EnderecoRepository enderecoRepository;
    private final ViaCepApiPort viaCepApiPort;
    private final IbgeApiPort ibgeApiPort;

    public EnderecoResponse buscarEnderecoPorCep(String cep) {
        ViaCepEnderecoResponse enderecoViaCepResponse = viaCepApiPort.buscarEnderecoPorCep(cep);
        return EnderecoMapper.enderecoViaCepToEnderecoResponse(enderecoViaCepResponse);
    }

    public List<EstadoResponse> buscarEstados() {
        List<IbgeEstado> estadosIbgeResponse = ibgeApiPort.listarEstados();
        return ordenarListaEstados(estadosIbgeResponse);
    }

    private static List<EstadoResponse> ordenarListaEstados(List<IbgeEstado> estadosIbgeResponse) {
        EstadoResponse sp = null;
        EstadoResponse rj = null;
        List<EstadoResponse> outros = new ArrayList<>();

        for (EstadoResponse estado : EstadoMapper.toEstadoResponseList(estadosIbgeResponse)) {
            Long id = estado.getId();
            if (Objects.equals(id, ID_ESTADO_SP)) {
                sp = estado;
            } else if (Objects.equals(id, ID_ESTADO_RJ)) {
                rj = estado;
            } else {
                outros.add(estado);
            }
        }

        Util.ordenarPor(outros, EstadoResponse::getNome);

        List<EstadoResponse> resultado = new ArrayList<>();
        if (sp != null) resultado.add(sp);
        if (rj != null) resultado.add(rj);
        resultado.addAll(outros);
        return resultado;
    }

    @Override
    public List<MunicipioResponse> buscarMunicipiosPorEstadoId(Long estadoId) {
        List<IbgeMunicipio> municipiosIbgeResponse = ibgeApiPort.listarMunicipiosPorEstado(estadoId);
        return MunicipioMapper.toMunicipioResponseList(municipiosIbgeResponse);
    }

}