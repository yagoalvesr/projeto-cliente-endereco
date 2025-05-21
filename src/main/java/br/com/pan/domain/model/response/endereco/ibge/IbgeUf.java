package br.com.pan.domain.model.response.endereco.ibge;

import lombok.Data;

@Data
public class IbgeUf {
    private Long id;
    private String sigla;
    private String nome;
    private IbgeRegiao regiao;
}
