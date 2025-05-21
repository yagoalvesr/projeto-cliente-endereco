package br.com.pan.domain.repository.endereco;

import br.com.pan.domain.model.entity.endereco.Endereco;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {}