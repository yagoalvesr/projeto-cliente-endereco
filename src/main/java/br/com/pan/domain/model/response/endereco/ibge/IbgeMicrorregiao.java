package br.com.pan.domain.model.response.endereco.ibge;

import lombok.Data;

@Data
public class IbgeMicrorregiao {
    private Long id;
    private String nome;
    private IbgeMesorregiao mesorregiao;
}
