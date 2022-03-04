package br.com.letscode.StarWarsAPI.service;


import br.com.letscode.StarWarsAPI.model.*;
import br.com.letscode.StarWarsAPI.dto.RequestRebelde;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RebeldeService {

    public static Rebelde cadastrarRebelde(RequestRebelde form){
        Genero genero = Genero.valueOf(form.getGenero().toUpperCase().trim());
        Localizacao localizacao = new Localizacao(form.getLatitude(), form.getLogintude(), form.getNomeGalaxia());
        Inventario inventario = new Inventario(form.getQtdArmas(), form.getQtdAgua(), form.getQtdMunicao(), form.getQtdComida());
        Rebelde rebelde = new Rebelde(UUID.randomUUID(),form.getNome(), form.getIdade(), genero, localizacao, inventario);
        Rebelde.add(rebelde);

        return rebelde;
    }

}
