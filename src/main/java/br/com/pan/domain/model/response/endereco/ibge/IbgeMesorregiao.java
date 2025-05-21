package br.com.pan.domain.model.response.endereco.ibge;

import lombok.Data;

@Data
public class IbgeMesorregiao {
    private Long id;
    private String nome;
    private IbgeUf UF;
}
