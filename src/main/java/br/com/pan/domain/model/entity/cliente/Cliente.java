package br.com.pan.domain.model.entity.cliente;

import br.com.pan.domain.model.entity.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue
    private UUID id;
    private String nome;
    private String sobrenome;
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
}