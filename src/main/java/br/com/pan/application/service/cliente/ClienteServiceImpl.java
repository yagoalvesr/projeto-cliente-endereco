package br.com.pan.application.service.cliente;

import br.com.pan.domain.exception.ClienteNaoEncontradoException;
import br.com.pan.domain.model.entity.cliente.Cliente;
import br.com.pan.domain.model.response.cliente.ClienteResponse;
import br.com.pan.domain.repository.cliente.ClienteRepository;
import br.com.pan.infrastructure.mapper.cliente.ClienteMapper;
import br.com.pan.infrastructure.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public ClienteResponse buscarClientePorCpf(String cpf) {
        Cliente cliente = clienteRepository.findByCpf(Util.apenasNumeros(cpf)).orElseThrow(() -> new ClienteNaoEncontradoException("Não foi possível encontrar um cliente para esse CPF"));
        return ClienteMapper.clienteEntitytoClienteResponse(cliente);
    }
}