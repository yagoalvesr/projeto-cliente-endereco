package br.com.pan.domain.model.entity.endereco;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    @Id
    @GeneratedValue
    private UUID id;
    private Long codigoCidade;
    private String nomeCidade;
    private Long codigoEstado;
    private String nomeEstado;
    private String cep;
}