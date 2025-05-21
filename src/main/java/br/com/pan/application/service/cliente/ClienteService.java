package br.com.pan.application.service.cliente;

import br.com.pan.domain.model.request.cliente.ClienteEnderecoRequest;
import br.com.pan.domain.model.response.cliente.ClienteResponse;

public interface ClienteService {
    ClienteResponse buscarClientePorCpf(String cpf);
    void alterarEnderecoCliente(ClienteEnderecoRequest clienteEnderecoRequest);
}