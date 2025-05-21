package br.com.pan.domain.model.entity.cliente;

import br.com.pan.domain.model.entity.endereco.Endereco;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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