package br.com.pan.domain.repository.cliente;

import br.com.pan.domain.model.entity.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {}