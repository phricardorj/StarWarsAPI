package br.com.letscode.StarWarsAPI.model;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Inventario {
    private int qtdArmas;
    private int qtdAgua;
    private int qtdMunicao;
    private int qtdComida;

    public Inventario(int qtdArmas, int qtdAgua, int qtdMunicao, int qtdComida) {
        this.qtdArmas = qtdArmas;
        this.qtdAgua = qtdAgua;
        this.qtdMunicao = qtdMunicao;
        this.qtdComida = qtdComida;
    }
}
