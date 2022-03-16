package br.com.letscode.StarWarsAPI.model;


import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

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

    public static boolean verificaElemento(List<Troca> trocaList){
        for (Troca troca : trocaList) {
            String item = troca.getNome().trim().toLowerCase();
            return item.equals("arma") || item.equals("municao") || item.equals("comida") || item.equals("agua");
        }

        return false;
    }

    public static int getPontos(List<Troca> trocaList){
        int pontos = 0;

        for (Troca troca : trocaList) {
            switch(troca.getNome()) {
                case "arma":
                    pontos += troca.getQuantidade() * 4;
                    break;
                case "municao":
                    pontos += troca.getQuantidade() * 3;
                    break;
                case "agua":
                    pontos += troca.getQuantidade() * 2;
                    break;
                case "comida":
                    pontos += troca.getQuantidade();
                    break;
            }
        }

        return pontos;
    }


    public boolean removeItem(String nome, int quantidade){
        switch(nome) {
            case "arma":
               if(this.qtdArmas >= quantidade) {
                   this.qtdArmas -= quantidade;
                   return true;
               }
                break;
            case "municao":
                if(this.qtdMunicao >= quantidade) {
                    this.qtdMunicao -= quantidade;
                    return true;
                }
                break;
            case "agua":
                if(this.qtdAgua >= quantidade) {
                    this.qtdAgua -= quantidade;
                    return true;
                }
                break;
            case "comida":
                if(this.qtdComida >= quantidade) {
                    this.qtdComida -= quantidade;
                    return true;
                }
                break;
        }

        return false;
    }

    public boolean addItem(String nome, int quantidade) {
        switch(nome) {
            case "arma":
                this.qtdArmas += quantidade;
                return true;
            case "municao":
                this.qtdMunicao += quantidade;
                return true;
            case "agua":
                this.qtdAgua += quantidade;
                return true;
            case "comida":
                this.qtdComida += quantidade;
                return true;
        }

        return false;
    }

}
