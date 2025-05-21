package br.com.pan.application.service.endereco;

import br.com.pan.domain.model.entity.cliente.Cliente;
import br.com.pan.domain.repository.cliente.ClienteRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final ClienteRepository clienteRepo;

    public List<Cliente> listarClientes() {
        return clienteRepo.findAll();
    }
}