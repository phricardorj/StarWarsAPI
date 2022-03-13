package br.com.letscode.StarWarsAPI.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Relatorio {
    private String porcentagemRebeldes;
    private String porcentagemTraidores;
    private InventarioRelatorio inventarioRelatorio;

    public Relatorio(String porcentagemRebeldes, String porcentagemTraidores, InventarioRelatorio inventarioRelatorio) {
        this.porcentagemRebeldes = porcentagemRebeldes;
        this.porcentagemTraidores = porcentagemTraidores;
        this.inventarioRelatorio = inventarioRelatorio;
    }
}