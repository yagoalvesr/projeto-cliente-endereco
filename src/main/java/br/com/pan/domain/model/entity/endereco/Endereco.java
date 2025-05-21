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
    private Long codigoIbgeCidade;
    private String nomeCidade;
    private Long codigoIbgeEstado;
    private String nomeEstado;
    private String cep;
    private String logradouro;
    private String bairro;
    private String uf;
    private String regiao;
    private String complemento;
    private String unidade;
}