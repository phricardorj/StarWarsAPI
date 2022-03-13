package br.com.letscode.StarWarsAPI.model;


import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

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

    public void addItem(String item, int qtdItem) {
        switch (item.toLowerCase()) {
            case "arma":
                if(this.qtdArmas >= qtdItem) {
                    this.qtdArmas += qtdItem;
                }
                break;
            case "agua":
                if(this.qtdAgua >= qtdItem) {
                    this.qtdAgua += qtdItem;
                }
                break;
            case "municao":
                if(this.qtdMunicao >= qtdItem) {
                    this.qtdMunicao += qtdItem;
                }
                break;
            case "comida":
                if(this.qtdComida >= qtdItem) {
                    this.qtdComida += qtdItem;
                }
                break;
        }
    }

    public boolean transfere(Rebelde receptor, String item, int qtdItem) {
        switch (item.toLowerCase()) {
            case "arma":
                if(this.qtdArmas >= qtdItem) {
                    this.qtdArmas -= qtdItem;
                    receptor.getInventario().addItem(item, qtdItem);
                    return true;
                }
                break;
            case "agua":
                if(this.qtdAgua >= qtdItem) {
                    this.qtdAgua -= qtdItem;
                    receptor.getInventario().addItem(item, qtdItem);
                    return true;
                }
                break;
            case "municao":
                if(this.qtdMunicao >= qtdItem) {
                    this.qtdMunicao -= qtdItem;
                    receptor.getInventario().addItem(item, qtdItem);
                    return true;
                }
                break;
            case "comida":
                if(this.qtdComida >= qtdItem) {
                    this.qtdComida -= qtdItem;
                    receptor.getInventario().addItem(item, qtdItem);
                    return true;
                }
                break;
        }

        return false;
    }


}
