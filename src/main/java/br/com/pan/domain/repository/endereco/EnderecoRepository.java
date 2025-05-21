package br.com.pan.domain.repository.endereco;

import br.com.pan.domain.model.entity.endereco.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {}