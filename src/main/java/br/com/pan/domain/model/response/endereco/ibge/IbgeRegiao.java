package br.com.pan.domain.model.response.endereco.ibge;

import lombok.Data;

@Data
public class IbgeRegiao {
    private Long id;
    private String sigla;
    private String nome;
}
