package br.com.pan.application.service.endereco;

import br.com.pan.domain.model.response.endereco.EnderecoResponse;

public interface EnderecoService {
    EnderecoResponse buscarEnderecoPorCep(String cep);
}