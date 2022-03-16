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

    public static Rebelde selecionar(UUID id){
        log.info("Retornando Rebelde pela sua ID!");
        return Objects.requireNonNull(Rebelde.getRebeldes().stream().filter(rebelde -> rebelde.getId().equals(id)).findFirst().orElse(null));
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
       int pontosFornecedor = Inventario.getPontos(itensFornecedor);
       int pontosReceptor = Inventario.getPontos(itensReceptor);

       if (Inventario.verificaElemento(itensFornecedor) && Inventario.verificaElemento(itensReceptor)) {
           if (pontosFornecedor == pontosReceptor){
               Rebelde fornecedor = selecionar(negociar.getRebeldeFornecedor());
               Rebelde receptor = selecionar(negociar.getRebeldeReceptor());

               for (Troca troca : itensFornecedor) {
                   receptor.getInventario().removeItem(troca.getNome(), troca.getQuantidade());
                   fornecedor.getInventario().addItem(troca.getNome(), troca.getQuantidade());
               }

               for (Troca troca : itensReceptor) {
                   fornecedor.getInventario().removeItem(troca.getNome(), troca.getQuantidade());
                   receptor.getInventario().addItem(troca.getNome(), troca.getQuantidade());
               }

               return "Sucesso: Troca efetuada!";
           } else {
               return "Erro: Pontos dos itens nao sao iguais!";
           }
       } else {
           return "Erro: Item nao encontrado!";
       }
    }

}
