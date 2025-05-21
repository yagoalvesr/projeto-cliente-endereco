package br.com.pan.infrastructure.mapper.cliente;

import br.com.pan.domain.model.entity.cliente.Cliente;
import br.com.pan.domain.model.response.cliente.ClienteResponse;
import br.com.pan.domain.model.response.endereco.EnderecoResponse;
import br.com.pan.infrastructure.mapper.endereco.EnderecoMapper;

public class ClienteMapper {

    private ClienteMapper() {
    }

    public static ClienteResponse clienteEntitytoClienteResponse(Cliente cliente) {
        if (cliente == null) return null;

        ClienteResponse response = new ClienteResponse();
        response.setId(cliente.getId());
        response.setCpf(cliente.getCpf());

        String nomeCompleto = String.format("%s %s", cliente.getNome(), cliente.getSobrenome()).trim();
        response.setNomeCompleto(nomeCompleto);

        EnderecoResponse enderecoResponse = EnderecoMapper.enderecoEntitytoEnderecoResponse(cliente.getEndereco());
        response.setEndereco(enderecoResponse);

        return response;
    }
}
