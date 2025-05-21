package br.com.pan.infrastructure.mapper.endereco;

import br.com.pan.domain.model.entity.endereco.Endereco;
import br.com.pan.domain.model.request.cliente.ClienteEnderecoRequest;
import br.com.pan.domain.model.response.endereco.EnderecoResponse;
import br.com.pan.domain.model.response.endereco.viacep.ViaCepEnderecoResponse;

public class EnderecoMapper {

    private EnderecoMapper() {
    }

    public static EnderecoResponse enderecoViaCepToEnderecoResponse(ViaCepEnderecoResponse viaCep) {
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

    public static EnderecoResponse enderecoEntitytoEnderecoResponse(Endereco endereco) {
        if (endereco == null) return null;

        EnderecoResponse response = new EnderecoResponse();
        response.setCep(endereco.getCep());
        response.setLogradouro(endereco.getLogradouro());
        response.setComplemento(endereco.getComplemento());
        response.setUnidade(endereco.getUnidade());
        response.setBairro(endereco.getBairro());
        response.setUf(endereco.getUf());
        response.setEstado(endereco.getEstado());
        response.setRegiao(endereco.getRegiao());
        response.setCidade(endereco.getCidade());

        return response;
    }

    public static void enderecoRequestToEnderecoEntity(Endereco endereco, ClienteEnderecoRequest request) {
        endereco.setCodigoIbgeCidade(request.getCodigoIbgeCidade());
        endereco.setCidade(request.getCidade());
        endereco.setCodigoIbgeEstado(request.getCodigoIbgeEstado());
        endereco.setEstado(request.getEstado());
        endereco.setCep(request.getCep());
        endereco.setLogradouro(request.getLogradouro());
        endereco.setBairro(request.getBairro());
        endereco.setUf(request.getUf());
        endereco.setRegiao(request.getRegiao());
        endereco.setComplemento(request.getComplemento());
        endereco.setUnidade(request.getUnidade());
    }
}
