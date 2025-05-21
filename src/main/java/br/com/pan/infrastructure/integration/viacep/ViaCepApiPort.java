package br.com.pan.infrastructure.integration.viacep;

import br.com.pan.domain.model.response.endereco.viacep.ViaCepEnderecoResponse;

public interface ViaCepApiPort {
    ViaCepEnderecoResponse buscarEnderecoPorCep(String cep);
}