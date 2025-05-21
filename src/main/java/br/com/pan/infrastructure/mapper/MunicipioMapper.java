package br.com.pan.infrastructure.mapper;

import br.com.pan.domain.model.response.endereco.MunicipioResponse;
import br.com.pan.domain.model.response.endereco.ibge.IbgeMunicipio;
import java.util.Collections;
import java.util.List;

public class MunicipioMapper {

    public static MunicipioResponse toMunicipioResponse(IbgeMunicipio municipio) {
        MunicipioResponse response = new MunicipioResponse();
        response.setId(municipio.getId());
        response.setNome(municipio.getNome());

        return response;
    }

    public static List<MunicipioResponse> toMunicipioResponseList(List<IbgeMunicipio> municipios) {
        if (municipios == null) {
            return Collections.emptyList();
        }
        return municipios.stream()
                .map(MunicipioMapper::toMunicipioResponse).toList();
    }
}