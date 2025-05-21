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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final EnderecoService enderecoService;

    @Override
    public ClienteResponse buscarClientePorCpf(String cpf) {
        String cpfNumerico = Util.apenasNumeros(cpf);
        log.info("Iniciando busca de cliente por CPF: {}", cpfNumerico);

        Cliente cliente = clienteRepository.findByCpf(cpfNumerico)
                .orElseThrow(() -> {
                    log.warn("Cliente não encontrado para CPF: {}", cpfNumerico);
                    return new ClienteNaoEncontradoException("Não foi possível encontrar um cliente para esse CPF");
                });

        log.info("Cliente encontrado com id: {}", cliente.getId());
        return ClienteMapper.clienteEntitytoClienteResponse(cliente);
    }

    @Override
    public void alterarEnderecoCliente(ClienteEnderecoRequest clienteEnderecoRequest) {
        log.info("Iniciando atualização de endereço para clienteId: {}", clienteEnderecoRequest.getClienteId());

        Cliente cliente = clienteRepository.findById(clienteEnderecoRequest.getClienteId())
                .orElseThrow(() -> {
                    log.warn("Cliente não encontrado para ID: {}", clienteEnderecoRequest.getClienteId());
                    return new ClienteNaoEncontradoException("Não foi possível encontrar um cliente para esse id");
                });

        EnderecoMapper.enderecoRequestToEnderecoEntity(cliente.getEndereco(), clienteEnderecoRequest);
        clienteRepository.save(cliente);

        log.info("Endereço atualizado com sucesso para clienteId: {}", cliente.getId());
    }
}