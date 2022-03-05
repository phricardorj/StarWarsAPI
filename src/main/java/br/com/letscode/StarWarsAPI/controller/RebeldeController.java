package br.com.letscode.StarWarsAPI.controller;

import br.com.letscode.StarWarsAPI.dto.RequestRebelde;
import br.com.letscode.StarWarsAPI.model.Localizacao;
import br.com.letscode.StarWarsAPI.model.Rebelde;
import br.com.letscode.StarWarsAPI.service.RebeldeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rebeldes")
public class RebeldeController {

    @GetMapping
    public List<Rebelde> listaRebeldes(){
        return Rebelde.getRebeldes();
    }

    @GetMapping("/listarloc/{id}")
    public Localizacao listarLoc(@PathVariable UUID id) {
        return listaRebeldes().stream().filter(rebelde -> rebelde.getId().equals(id)).findFirst().get().getLocalizacao();
    }

    @PatchMapping("/atualizar/{id}")@ResponseStatus(HttpStatus.CREATED)
    public Localizacao listarLoc(@PathVariable UUID id, @RequestBody Localizacao localizacao){
        Localizacao rebelLoc = listaRebeldes().stream().filter(rebelde -> rebelde.getId().equals(id)).findFirst().get().getLocalizacao();
        rebelLoc.setLatitude(localizacao.getLatitude());
        rebelLoc.setLongitude(localizacao.getLongitude());
        rebelLoc.setNome(localizacao.getNome());
        return rebelLoc;
    }


    @PostMapping
    public Rebelde cadastrar(@RequestBody @Valid RequestRebelde form){
        return RebeldeService.cadastrarRebelde(form);
    }

    @GetMapping("/{id}")
    public List<Rebelde> selecionar(@PathVariable UUID id){
        return listaRebeldes().stream().filter(rebelde -> rebelde.getId().equals(id)).collect(Collectors.toList());
    }

}
