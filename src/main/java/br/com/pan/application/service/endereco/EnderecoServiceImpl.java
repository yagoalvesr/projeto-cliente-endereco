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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnderecoServiceImpl implements EnderecoService {

    private static final Long ID_ESTADO_SP = 35L;
    private static final Long ID_ESTADO_RJ = 33L;

    private final ViaCepApiPort viaCepApiPort;
    private final IbgeApiPort ibgeApiPort;

    public EnderecoResponse buscarEnderecoPorCep(String cep) {
        log.info("Buscando endereço para o CEP: {}", cep);
        ViaCepEnderecoResponse enderecoViaCepResponse = viaCepApiPort.buscarEnderecoPorCep(cep);
        log.info("Endereço encontrado: cep={}, logradouro={}, localidade={}",
                enderecoViaCepResponse.getCep(), enderecoViaCepResponse.getLogradouro(), enderecoViaCepResponse.getLocalidade());
        return EnderecoMapper.enderecoViaCepToEnderecoResponse(enderecoViaCepResponse);
    }

    public List<EstadoResponse> buscarEstados() {
        log.info("Buscando lista de estados via IBGE");
        List<IbgeEstado> estadosIbgeResponse = ibgeApiPort.listarEstados();
        log.info("Total de estados retornados: {}", estadosIbgeResponse.size());
        List<EstadoResponse> estadosOrdenados = ordenarListaEstados(estadosIbgeResponse);
        log.info("Lista de estados ordenada com SP e RJ no topo");
        return estadosOrdenados;
    }

    private static List<EstadoResponse> ordenarListaEstados(List<IbgeEstado> estadosIbgeResponse) {
        EstadoResponse sp = null;
        EstadoResponse rj = null;
        List<EstadoResponse> outros = new ArrayList<>();

        for (EstadoResponse estado : EstadoMapper.toEstadoResponseList(estadosIbgeResponse)) {
            Long id = estado.getId();
            if (Objects.equals(id, ID_ESTADO_SP)) {
                sp = estado;
                log.debug("Identificado estado SP: {}", estado.getNome());
            } else if (Objects.equals(id, ID_ESTADO_RJ)) {
                rj = estado;
                log.debug("Identificado estado RJ: {}", estado.getNome());
            } else {
                outros.add(estado);
            }
        }

        Util.ordenarPor(outros, EstadoResponse::getNome);
        log.debug("Estados restantes ordenados alfabeticamente");

        List<EstadoResponse> resultado = new ArrayList<>();
        if (sp != null) resultado.add(sp);
        if (rj != null) resultado.add(rj);
        resultado.addAll(outros);
        return resultado;
    }

    @Override
    public List<MunicipioResponse> buscarMunicipiosPorEstadoId(Long estadoId) {
        log.info("Buscando municípios para estadoId={}", estadoId);
        List<IbgeMunicipio> municipiosIbgeResponse = ibgeApiPort.listarMunicipiosPorEstado(estadoId);
        log.info("Total de municípios retornados: {}", municipiosIbgeResponse.size());
        return MunicipioMapper.toMunicipioResponseList(municipiosIbgeResponse);
    }

}