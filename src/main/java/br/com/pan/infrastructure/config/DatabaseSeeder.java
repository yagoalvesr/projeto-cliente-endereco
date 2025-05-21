package br.com.pan.infrastructure.config;

import br.com.pan.domain.model.entity.cliente.Cliente;
import br.com.pan.domain.model.entity.endereco.Endereco;
import br.com.pan.domain.repository.cliente.ClienteRepository;
import br.com.pan.domain.repository.endereco.EnderecoRepository;
import java.time.OffsetDateTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;

    public DatabaseSeeder(ClienteRepository clienteRepository, EnderecoRepository enderecoRepository) {
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Endereco endereco = new Endereco(null,
                5200050L,
                "Abadia de Goiás",
                52L,
                "Goiás",
                "72940-000",
                "Quadra 1",
                "Bairro X",
                "GO",
                "Centro-oeste",
                "",
                ""
        );

        Cliente cliente = new Cliente(
                null,
                OffsetDateTime.now(),
                OffsetDateTime.now(),
                "Yago",
                "Alves Ribeiro",
                "03423655178",
                endereco
        );

        popularEnderecos(endereco);
        popularClientes(cliente);
    }

    private void popularEnderecos(Endereco endereco) {
        if (enderecoRepository.count() == 0) {
            enderecoRepository.save(endereco);
        }
    }

    private void popularClientes(Cliente cliente) {
        if (clienteRepository.count() == 0) {
            clienteRepository.save(cliente);
        }
    }

}
