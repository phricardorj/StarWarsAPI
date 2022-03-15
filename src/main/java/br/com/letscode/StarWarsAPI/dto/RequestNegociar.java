package br.com.letscode.StarWarsAPI.dto;

import br.com.letscode.StarWarsAPI.model.Troca;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter @Setter
public class RequestNegociar {
    private UUID rebeldeReceptor;
    private UUID rebeldeFornecedor;
    private List<Troca> itensReceptor;
    private List<Troca> itensFornecedor;
}
