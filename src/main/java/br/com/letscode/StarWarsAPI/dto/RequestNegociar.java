package br.com.letscode.StarWarsAPI.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class RequestNegociar {
    private UUID rebeldeReceptor;
    private UUID rebeldeFornecedor;
    private String item;
    private int qtdItem;
}
