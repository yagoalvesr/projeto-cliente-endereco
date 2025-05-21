package br.com.pan.application.service.cliente;

import br.com.pan.application.service.endereco.EnderecoService;
import br.com.pan.domain.exception.ClienteNaoEncontradoException;
import br.com.pan.domain.model.entity.cliente.Cliente;
import br.com.pan.domain.model.request.cliente.ClienteEnderecoRequest;
import br.com.pan.domain.model.response.cliente.ClienteResponse;
import br.com.pan.domain.repository.cliente.ClienteRepository;
import br.com.pan.infrastructure.mapper.cliente.ClienteMapper;
import br.com.pan.infrastructure.mapper.endereco.EnderecoMapper;
import br.com.pan.infrastructure.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final EnderecoService enderecoService;

    @Override
    public ClienteResponse buscarClientePorCpf(String cpf) {
        Cliente cliente = clienteRepository.findByCpf(Util.apenasNumeros(cpf)).orElseThrow(() -> new ClienteNaoEncontradoException("Não foi possível encontrar um cliente para esse CPF"));
        return ClienteMapper.clienteEntitytoClienteResponse(cliente);
    }

    @Override
    public void alterarEnderecoCliente(ClienteEnderecoRequest clienteEnderecoRequest) {
        Cliente cliente = clienteRepository.findById(clienteEnderecoRequest.getClienteId()).orElseThrow(() -> new ClienteNaoEncontradoException("Não foi possível encontrar um cliente para esse id"));
        EnderecoMapper.enderecoRequestToEnderecoEntity(cliente.getEndereco(), clienteEnderecoRequest);
        clienteRepository.save(cliente);
    }
}