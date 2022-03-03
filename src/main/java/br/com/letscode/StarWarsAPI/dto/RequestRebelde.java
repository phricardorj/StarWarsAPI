package br.com.letscode.StarWarsAPI.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RequestRebelde {
    private String nome;
    private int idade;
    private double latitude;
    private double logintude;
    private String genero;
    private String nomeGalaxia;
    private int qtdArmas, qtdAgua, qtdMunicao, qtdComida;
}
