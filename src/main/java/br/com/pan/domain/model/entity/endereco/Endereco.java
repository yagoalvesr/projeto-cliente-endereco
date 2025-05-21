package br.com.pan.domain.model.entity.endereco;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

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