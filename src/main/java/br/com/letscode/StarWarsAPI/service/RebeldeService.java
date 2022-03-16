package br.com.letscode.StarWarsAPI.service;


import br.com.letscode.StarWarsAPI.model.*;
import br.com.letscode.StarWarsAPI.dto.RequestRebelde;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

}
