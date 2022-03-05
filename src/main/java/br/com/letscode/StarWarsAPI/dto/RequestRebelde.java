package br.com.letscode.StarWarsAPI.dto;

import br.com.letscode.StarWarsAPI.model.Inventario;
import br.com.letscode.StarWarsAPI.model.Localizacao;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RequestRebelde {
    private String nome;
    private int idade;
    private String genero;
    private Localizacao localizacao;
    private Inventario inventario;
}
