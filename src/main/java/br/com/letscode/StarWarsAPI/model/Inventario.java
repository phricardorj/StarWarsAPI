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

    public HashMap<String, Integer> transfere(HashMap<String, Integer> itensParaEnviar, HashMap<String, Integer> itensParaReceber) {
        HashMap<String, Integer> novoInventario = new HashMap<>();
        novoInventario.put("arma", this.qtdArmas);
        novoInventario.put("agua", this.qtdAgua);
        novoInventario.put("municao", this.qtdMunicao);
        novoInventario.put("comida", this.qtdComida);

        boolean falhou = false;
        int pontosParaEnviar = 0;
        int pontosParaReceber = 0;

        for (String itemParaEnviar : itensParaEnviar.keySet()) {
            pontosParaEnviar = calcularPontos(itensParaEnviar, pontosParaEnviar, itemParaEnviar);

            boolean encontrado = false;
            for (String itemInventario : novoInventario.keySet()) {
                if (itemParaEnviar.equals(itemInventario)) {
                    int quantidadeAnterior = novoInventario.get(itemParaEnviar);
                    int novaQuantidade = quantidadeAnterior - itensParaEnviar.get(itemParaEnviar);

                    if (novaQuantidade < 0) {
                        falhou = true;
                    }

                    novoInventario.put(itemParaEnviar, novaQuantidade);
                    encontrado = true;
                }
            }

            if (!encontrado) {
                falhou = true;
            }
        }

        for (String itemParaReceber : itensParaReceber.keySet()) {
            pontosParaReceber = calcularPontos(itensParaReceber, pontosParaReceber, itemParaReceber);

            boolean encontrado = false;
            for (String itemInventario : novoInventario.keySet()) {
                if (itemParaReceber.equals(itemInventario)) {
                    int quantidadeAnterior = novoInventario.get(itemParaReceber);
                    int novaQuantidade = quantidadeAnterior + itensParaReceber.get(itemParaReceber);
                    novoInventario.put(itemParaReceber, novaQuantidade);
                    encontrado = true;
                }
            }
            if (!encontrado) {
                int novaQuantidade = itensParaReceber.get(itemParaReceber);
                novoInventario.put(itemParaReceber, novaQuantidade);
            }
        }

        if (!falhou && pontosParaEnviar == pontosParaReceber) {
            return novoInventario;
        }
        throw new Error("Falha na transação");
    }

    private int calcularPontos(HashMap<String, Integer> itens, int pontos, String item) {
        switch (item) {
            case "arma":
                pontos += 4 * itens.get(item);
                break;
            case "municao":
                pontos += 3 * itens.get(item);
                break;
            case "agua":
                pontos += 2 * itens.get(item);
                break;
            case "comida":
                pontos += itens.get(item);
                break;
        }
        return pontos;
    }

    public void atualizarInventario(HashMap<String, Integer> novoInventario) {
        for (String item : novoInventario.keySet()) {
            switch (item) {
                case "arma":
                    this.qtdArmas = novoInventario.get(item);
                    break;
                case "municao":
                    this.qtdMunicao = novoInventario.get(item);
                    break;
                case "agua":
                    this.qtdAgua = novoInventario.get(item);
                    break;
                case "comida":
                    this.qtdComida = novoInventario.get(item);
                    break;
                default:
                    throw new Error("Erro ao atualizar");
            }
        }
    }

}
