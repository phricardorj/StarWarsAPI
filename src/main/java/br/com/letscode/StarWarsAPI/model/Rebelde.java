package br.com.letscode.StarWarsAPI.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class Rebelde {
    private UUID id;
    private String nome;
    private int idade;
    private String genero;
    private Localizacao localizacao;
    private Inventario inventario;
    private boolean Traidor = false;

    public Rebelde(UUID id, String nome, int idade, String genero, Localizacao localizacao, Inventario inventario){
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        this.localizacao = localizacao;
        this.inventario = inventario;
    }

}
