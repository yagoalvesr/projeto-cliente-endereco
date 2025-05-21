package br.com.pan.infrastructure.mapper;

import br.com.pan.domain.model.response.endereco.EnderecoResponse;
import br.com.pan.domain.model.response.endereco.viacep.ViaCepEnderecoResponse;

public class EnderecoMapper {

    private EnderecoMapper() {
    }

    public static EnderecoResponse fromViaCepEnderecoResponse(ViaCepEnderecoResponse viaCep) {
        EnderecoResponse response = new EnderecoResponse();
        response.setCep(viaCep.getCep());
        response.setLogradouro(viaCep.getLogradouro());
        response.setComplemento(viaCep.getComplemento());
        response.setUnidade(viaCep.getUnidade());
        response.setBairro(viaCep.getBairro());
        response.setLocalidade(viaCep.getLocalidade());
        response.setUf(viaCep.getUf());
        response.setEstado(viaCep.getEstado());
        response.setRegiao(viaCep.getRegiao());
        return response;
    }
}
