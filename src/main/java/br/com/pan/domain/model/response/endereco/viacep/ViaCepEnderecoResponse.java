package br.com.pan.domain.model.response.endereco.viacep;

import lombok.Data;

@Data
public class ViaCepEnderecoResponse {
    private String cep;
    private String logradouro;
    private String complemento;
    private String unidade;
    private String bairro;
    private String localidade;
    private String uf;
    private String estado;   // "Distrito Federal"
    private String regiao;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;
}
