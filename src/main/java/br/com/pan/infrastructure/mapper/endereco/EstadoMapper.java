package br.com.pan.infrastructure.mapper.endereco;

import br.com.pan.domain.model.response.endereco.EstadoResponse;
import br.com.pan.domain.model.response.endereco.ibge.IbgeEstado;
import java.util.Collections;
import java.util.List;

public class EstadoMapper {

    public static EstadoResponse toEstadoResponse(IbgeEstado ibgeEstado) {
        if (ibgeEstado == null) {
            return null;
        }

        EstadoResponse response = new EstadoResponse();
        response.setId(ibgeEstado.getId());
        response.setSigla(ibgeEstado.getSigla());
        response.setNome(ibgeEstado.getNome());
        return response;
    }

    public static List<EstadoResponse> toEstadoResponseList(List<IbgeEstado> estados) {
        if (estados == null) {
            return Collections.emptyList();
        }
        return estados.stream()
                .map(EstadoMapper::toEstadoResponse).toList();
    }
}