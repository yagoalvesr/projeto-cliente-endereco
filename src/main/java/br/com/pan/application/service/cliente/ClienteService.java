package br.com.pan.application.service.cliente;

import br.com.pan.domain.repository.cliente.ClienteRepository;
import br.com.pan.domain.model.entity.cliente.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepo;

    public List<Cliente> listarClientes() {
        return clienteRepo.findAll();
    }
}