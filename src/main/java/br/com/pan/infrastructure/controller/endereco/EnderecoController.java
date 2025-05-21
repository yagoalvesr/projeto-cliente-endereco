package br.com.pan.infrastructure.controller.endereco;

import br.com.pan.domain.model.response.endereco.EnderecoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/endereco")
public interface EnderecoController {

    ResponseEntity<EnderecoResponse> buscarEnderecoPorCep(@RequestParam String cep);
}