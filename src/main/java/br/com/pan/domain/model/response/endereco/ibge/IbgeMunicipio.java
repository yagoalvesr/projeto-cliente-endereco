package br.com.pan.domain.model.response.endereco.ibge;

import lombok.Data;

@Data
public class IbgeMunicipio {
    private Long id;
    private String nome;
    private IbgeMicrorregiao microrregiao;
}

