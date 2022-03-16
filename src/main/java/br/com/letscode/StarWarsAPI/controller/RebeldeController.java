package br.com.letscode.StarWarsAPI.controller;

import br.com.letscode.StarWarsAPI.dto.RequestNegociar;
import br.com.letscode.StarWarsAPI.dto.RequestRebelde;
import br.com.letscode.StarWarsAPI.model.*;
import br.com.letscode.StarWarsAPI.service.RebeldeService;
import br.com.letscode.StarWarsAPI.service.RelatorioService;
import br.com.letscode.StarWarsAPI.service.TrocaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;


@RestController
@RequestMapping("/rebeldes")
public class RebeldeController {
    @GetMapping
    public List<Rebelde> getRebeldes(){
        return RebeldeService.getRebeldes();
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Rebelde cadastrar(@RequestBody @Valid RequestRebelde form, HttpServletRequest req, HttpServletResponse resp){
        Rebelde rebeldeCadastrado = RebeldeService.cadastrarRebelde(form);
        String urlRebelde = req.getRequestURL().toString() + "/" + rebeldeCadastrado.getId().toString();
        resp.addHeader("Rebelde URL", urlRebelde);
        return rebeldeCadastrado;
    }

    @GetMapping("/{id}")
    public Rebelde selecionar(@PathVariable UUID id){
        return RebeldeService.selecionar(id);
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable UUID id) {
        return RebeldeService.deletar(id);
    }

    @GetMapping("/localizacao/{id}")
    public Localizacao localizar(@PathVariable UUID id) {
        return RebeldeService.localizar(id);
    }

    @PutMapping("/localizacao/{id}")
    public Localizacao alterarLocalizacao(@PathVariable UUID id, @RequestBody Localizacao localizacao){
        return RebeldeService.alterarLocalizacao(id, localizacao);
    }

    @GetMapping("/traidores")
    public List<Rebelde> getTraidores(){
        return RebeldeService.getTraidores();
    }

    @PatchMapping("/reportar/{id}")
    public String setTraidor(@PathVariable UUID id){
       return RebeldeService.setTraidor(id);
    }

    @GetMapping("/relatorio")
    public Relatorio getRelatorio(){
        return RelatorioService.getRelatorio();
    }

    @PutMapping("/negociar")
    public String negociar(@RequestBody @Valid RequestNegociar negociar){
        return TrocaService.negociar(negociar);
    }

}
