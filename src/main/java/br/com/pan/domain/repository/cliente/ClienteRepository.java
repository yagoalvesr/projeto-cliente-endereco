package br.com.pan.domain.repository.cliente;

import br.com.pan.domain.model.entity.cliente.Cliente;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {}