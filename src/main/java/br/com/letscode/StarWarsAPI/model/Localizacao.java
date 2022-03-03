package br.com.letscode.StarWarsAPI.model;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Localizacao {
    private double latitude;
    private double longitude;
    private String nome;

    public Localizacao(double latitude, double longitude, String nome){
        this.latitude = latitude;
        this.longitude = longitude;
        this.nome = nome;
    }
}
