package br.com.letscode.StarWarsAPI.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Relatorio {
    private String porcentagemRebeldes;
    private String porcentagemTraidores;
    private int itensPerdidos;

    public Relatorio(String porcentagemRebeldes, String porcentagemTraidores, int itensPerdidos) {
        this.porcentagemRebeldes = porcentagemRebeldes;
        this.porcentagemTraidores = porcentagemTraidores;
        this.itensPerdidos = itensPerdidos;
    }
}