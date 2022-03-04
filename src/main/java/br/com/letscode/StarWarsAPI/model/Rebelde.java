package br.com.letscode.StarWarsAPI.model;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter
public class Rebelde {
    private UUID id;
    private String nome;
    private int idade;
    private Genero genero;
    private Localizacao localizacao;
    private Inventario inventario;
    private boolean Traidor = false;
    private static List<Rebelde> rebeldes = new ArrayList<>();

    public Rebelde(UUID id, String nome, int idade, Genero genero, Localizacao localizacao, Inventario inventario){
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        this.localizacao = localizacao;
        this.inventario = inventario;
    }

    public static void adiciona(Rebelde rebelde){
        Rebelde.rebeldes.add(rebelde);
    }

    public static List<Rebelde> getRebeldes() {
        return rebeldes;
    }

}
