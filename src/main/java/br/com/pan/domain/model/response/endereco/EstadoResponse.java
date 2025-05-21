package br.com.pan.domain.model.response.endereco;

import lombok.Data;

@Data
public class EstadoResponse {
    private Long id;
    private String sigla;
    private String nome;
}
