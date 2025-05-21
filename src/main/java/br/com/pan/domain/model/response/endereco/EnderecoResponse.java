package br.com.pan.domain.model.response.endereco;

import lombok.Data;

@Data
public class EnderecoResponse {
    private String cep;
    private String logradouro;
    private String complemento;
    private String unidade;
    private String bairro;
    private String localidade;
    private String uf;
    private String estado;
    private String regiao;
}
