package br.com.letscode.StarWarsAPI.controller;

import br.com.letscode.StarWarsAPI.dto.RequestRebelde;
import br.com.letscode.StarWarsAPI.model.*;
import br.com.letscode.StarWarsAPI.service.RebeldeService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rebeldes")
public class RebeldeController {

    @GetMapping
    public List<Rebelde> listaRebeldes(){
        return Database.getRebeldes();
    }

    @PostMapping
    public Rebelde cadastrar(@RequestBody @Valid RequestRebelde form){
        return RebeldeService.cadastrarRebelde(form);
    }

}
