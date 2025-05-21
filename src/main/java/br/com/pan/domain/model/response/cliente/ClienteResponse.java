package br.com.pan.domain.model.response.cliente;

import br.com.pan.domain.model.response.endereco.EnderecoResponse;
import java.util.UUID;
import lombok.Data;

@Data
public class ClienteResponse {
    private UUID id;
    private String nomeCompleto;
    private String cpf;
    private EnderecoResponse endereco;
}
