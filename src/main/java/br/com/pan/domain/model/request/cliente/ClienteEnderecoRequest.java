package br.com.pan.domain.model.request.cliente;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Data;

@Data
public class ClienteEnderecoRequest {

    @NotNull(message = "Obrigatorio informar o campo clienteId")
    private UUID clienteId;
    @NotNull(message = "Obrigatorio informar o campo codigoIbgeCidade")
    private Long codigoIbgeCidade;
    @NotNull(message = "Obrigatorio informar o campo cidade")
    private String cidade;
    @NotNull(message = "Obrigatorio informar o campo codigoIbgeEstado")
    private Long codigoIbgeEstado;
    @NotNull(message = "Obrigatorio informar o campo estado")
    private String estado;
    @NotNull(message = "Obrigatorio informar o campo cep")
    private String cep;
    @NotNull(message = "Obrigatorio informar o campo logradouro")
    private String logradouro;
    @NotNull(message = "Obrigatorio informar o campo bairro")
    private String bairro;
    @NotNull(message = "Obrigatorio informar o campo uf")
    private String uf;
    @NotNull(message = "Obrigatorio informar o campo regiao")
    private String regiao;
    private String complemento;
    private String unidade;
}
