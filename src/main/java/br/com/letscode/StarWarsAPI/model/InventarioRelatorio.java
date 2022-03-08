package br.com.letscode.StarWarsAPI.model;

import lombok.Getter;

@Getter
public class InventarioRelatorio {
    private final int totalArmas;
    private final int totalAgua;
    private final int totalMunicao;
    private final int totalComida;

    public InventarioRelatorio(int totalArmas, int totalAgua, int totalMunicao, int totalComida) {
        this.totalArmas = totalArmas;
        this.totalAgua = totalAgua;
        this.totalMunicao = totalMunicao;
        this.totalComida = totalComida;
    }

}
