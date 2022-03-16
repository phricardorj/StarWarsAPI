package br.com.letscode.StarWarsAPI.service;


import br.com.letscode.StarWarsAPI.dto.RequestNegociar;
import br.com.letscode.StarWarsAPI.model.*;
import br.com.letscode.StarWarsAPI.dto.RequestRebelde;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service @Slf4j
public class RebeldeService {

    public static List<Rebelde> getRebeldes(){
        log.info("Listando todos Rebeldes cadastrados!");
        return Rebelde.getRebeldes().stream().filter(rebelde -> !rebelde.isTraidor()).collect(Collectors.toList());
    }

    public static List<Rebelde> getTraidores(){
        log.info("Listando todos Rebeldes traidores!");
        return Rebelde.getRebeldes().stream().filter(Rebelde::isTraidor).collect(Collectors.toList());
    }

    public static Rebelde cadastrarRebelde(RequestRebelde form){
        log.info("Rebelde cadastrado!");
        Genero genero = Genero.valueOf(form.getGenero().toUpperCase().trim());
        Localizacao localizacao = new Localizacao(form.getLocalizacao().getLatitude(), form.getLocalizacao().getLongitude(), form.getLocalizacao().getNome());
        Inventario inventario = new Inventario(form.getInventario().getQtdArmas(), form.getInventario().getQtdAgua(), form.getInventario().getQtdMunicao(), form.getInventario().getQtdComida());
        Rebelde rebelde = new Rebelde(UUID.randomUUID(),form.getNome(), form.getIdade(), genero, localizacao, inventario);
        Rebelde.add(rebelde);
        return rebelde;
    }

    public static List<Rebelde> selecionar(UUID id){
        log.info("Retornando Rebelde pela sua ID!");
        return Rebelde.getRebeldes().stream().filter(rebelde -> rebelde.getId().equals(id)).collect(Collectors.toList());
    }

    public static String deletar(UUID id) {
        log.info("Rebelde deletado!");
        for (Rebelde r : Rebelde.getRebeldes()) {
            if (r.getId().equals(id)) {
                if(Rebelde.getRebeldes().remove(r)){
                    Rebelde.setRebeldes(Rebelde.getRebeldes());
                    return "Rebelde " + r.getNome().toUpperCase() + " removido com sucesso!";
                }
            }
        }
        return null;
    }

    public static Localizacao localizar(UUID id) {
        log.info("Retornando localizacao do Rebelde!");
        return Objects.requireNonNull(Rebelde.getRebeldes().stream().filter(rebelde -> rebelde.getId().equals(id)).findFirst().orElse(null)).getLocalizacao();
    }

    public static Localizacao alterarLocalizacao(UUID id, Localizacao localizacao){
        log.info("Localizacao rebelde alterada!");
        Localizacao rebelLoc = Objects.requireNonNull(Rebelde.getRebeldes().stream().filter(rebelde -> rebelde.getId().equals(id)).findFirst().orElse(null)).getLocalizacao();
        rebelLoc.setLatitude(localizacao.getLatitude());
        rebelLoc.setLongitude(localizacao.getLongitude());
        rebelLoc.setNome(localizacao.getNome());
        return rebelLoc;
    }

    public static String setTraidor(UUID id){
        log.info("Rebelde reportado!");
        for (Rebelde r : Rebelde.getRebeldes()) {
            if (r.getId().equals(id)) {
                int numDenuncias = r.getNumDenuncias();
                r.setNumDenuncias(numDenuncias += 1);
                if(numDenuncias == 3){
                    r.setTraidor(true);
                    return r.getNome().toUpperCase() + " é um traidor!";
                } else if (numDenuncias > 3) {
                    return "Não é possível reportar " + r.getNome().toUpperCase() + ", porque ele já é um Traidor!";
                } else {
                    return  r.getNome().toUpperCase() + " recebeu " + numDenuncias + " denuncia(s)!";
                }
            }
        }
        return null;
    }

    public static Relatorio getRelatorio(){
        log.info("Retornando relatorio!");
        DecimalFormat fmt = new DecimalFormat("0.0");
        int numRebeldes = getRebeldes().size();
        int numTraidores = getTraidores().size();
        int total = numRebeldes + numTraidores;
        double porcentagemRebeldes = 0;
        double porcentagemTraidores = 0;

        if(total > 0) {
            porcentagemRebeldes = (numRebeldes * 100f) / total;
            porcentagemTraidores = (numTraidores * 100f) / total;
        }

        InventarioRelatorio inventarioRelatorio = new InventarioRelatorio(
                getInventarios().stream().map(Inventario::getQtdArmas).reduce(0, Integer::sum),
                getInventarios().stream().map(Inventario::getQtdAgua).reduce(0, Integer::sum),
                getInventarios().stream().map(Inventario::getQtdMunicao).reduce(0, Integer::sum),
                getInventarios().stream().map(Inventario::getQtdComida).reduce(0, Integer::sum),
                itensPerdidos()
        );

        return new Relatorio(fmt.format(porcentagemRebeldes) + "%",
                fmt.format(porcentagemTraidores) + "%", inventarioRelatorio);
    }

    public static int itensPerdidos() {
        int num = 0;
        for (Rebelde r : getTraidores()) {
            num += r.getInventario().getQtdAgua() + r.getInventario().getQtdMunicao()
                    + r.getInventario().getQtdComida() + r.getInventario().getQtdAgua();
        }
        return num;
    }

    public static List<Inventario> getInventarios(){
        List<Inventario> inventarios = new ArrayList<>();

        for (Rebelde r : getRebeldes()) {
            inventarios.add(r.getInventario());
        }

        return inventarios;
    }

    public static String negociar(RequestNegociar negociar){
       List<Troca> itensFornecedor = negociar.getItensFornecedor();
       List<Troca> itensReceptor = negociar.getItensReceptor();
       int pontosFornecedor = 0;
       int pontosReceptor = 0;
       boolean mesmoItem = false; // serve para verificar se há item igual entre os dois
       boolean nomeErrado = false; // serve para verificar se o nome do item está errado

        for (Troca trocaFornecedor : itensFornecedor) {
            for (Troca trocaReceptor : itensReceptor) {
                if (verificaElemento(trocaFornecedor.getNome()) && verificaElemento(trocaReceptor.getNome())){
                    if (Objects.equals(trocaFornecedor.getNome(), trocaReceptor.getNome())) {
                        mesmoItem = true;
                        break;
                    }
                } else {
                    nomeErrado = true;
                }
            }
        }

        if(!nomeErrado) {
            if(!mesmoItem) {
                for (Troca trocaFornecedor : itensFornecedor) {
                    pontosFornecedor += getPoints(trocaFornecedor.getNome(), trocaFornecedor.getQuantidade());
                }

                for (Troca trocaReceptor : itensReceptor) {
                    pontosReceptor += getPoints(trocaReceptor.getNome(), trocaReceptor.getQuantidade());
                }

                if(pontosFornecedor == pontosReceptor) {
                    System.out.println("Podem trocar!");
                } else {
                    System.out.println("Troca incompativel! Verificar pontos!");
                }
            } else {
                System.out.println("Nao faz sentido trocar por itens iguais!");
            }
        } else {
            System.out.println("Item para troca não existe!");
        }

       return null;
    }

    private static int getPoints(String nome, int quantidade) {
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

    private static boolean verificaElemento(String nome){
       String item = nome.trim().toLowerCase();
       return item.equals("arma") || item.equals("municao") || item.equals("comida") || item.equals("agua");
    }

}
