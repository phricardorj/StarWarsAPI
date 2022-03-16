package br.com.letscode.StarWarsAPI.model;


import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

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

    public static int getPoints(String nome, int quantidade) {
        int pontos = 0;

        switch(nome) {
            case "arma":
                pontos += quantidade * 4;
                break;
            case "municao":
                pontos += quantidade * 3;
                break;
            case "agua":
                pontos += quantidade * 2;
                break;
            case "comida":
                pontos += quantidade;
                break;
        }

        return pontos;
    }

    public static boolean verificaElemento(String nome){
        String item = nome.trim().toLowerCase();
        return item.equals("arma") || item.equals("municao") || item.equals("comida") || item.equals("agua");
    }

}
