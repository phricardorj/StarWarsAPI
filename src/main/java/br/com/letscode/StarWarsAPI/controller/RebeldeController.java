package br.com.letscode.StarWarsAPI.controller;

import br.com.letscode.StarWarsAPI.dto.RequestRebelde;
import br.com.letscode.StarWarsAPI.model.Inventario;
import br.com.letscode.StarWarsAPI.model.Localizacao;
import br.com.letscode.StarWarsAPI.model.Rebelde;
import br.com.letscode.StarWarsAPI.model.Relatorio;
import br.com.letscode.StarWarsAPI.service.RebeldeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rebeldes")
public class RebeldeController {
    DecimalFormat fmt = new DecimalFormat("0.00");

    // Lista com TODOS - retorna Rebeldes e Traidores!
    public List<Rebelde> listaRebeldes(){
        return Rebelde.getRebeldes();
    }

    // Lista que retorna somente Rebeldes, não retorna Traidores!
    @GetMapping
    public List<Rebelde> getRebeldes(){
        return listaRebeldes().stream().filter(rebelde -> !rebelde.isTraidor()).collect(Collectors.toList());
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Rebelde cadastrar(@RequestBody @Valid RequestRebelde form){
        return RebeldeService.cadastrarRebelde(form);
    }

    @GetMapping("/{id}")
    public List<Rebelde> selecionar(@PathVariable UUID id){
        return listaRebeldes().stream().filter(rebelde -> rebelde.getId().equals(id)).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable UUID id) {
        for (Rebelde r : listaRebeldes()) {
            if (r.getId().equals(id)) {
                if(listaRebeldes().remove(r)){
                    Rebelde.setRebeldes(listaRebeldes());
                    return "Rebelde " + r.getNome().toUpperCase() + " removido com sucesso!";
                }
            }
        }
        return null;
    }

    @GetMapping("/localizacao/{id}")
    public Localizacao localizar(@PathVariable UUID id) {
        return Objects.requireNonNull(listaRebeldes().stream().filter(rebelde -> rebelde.getId().equals(id)).findFirst().orElse(null)).getLocalizacao();
    }

    @PutMapping("/localizacao/{id}")
    public Localizacao alterarLocalizacao(@PathVariable UUID id, @RequestBody Localizacao localizacao){
        Localizacao rebelLoc = Objects.requireNonNull(listaRebeldes().stream().filter(rebelde -> rebelde.getId().equals(id)).findFirst().orElse(null)).getLocalizacao();
        rebelLoc.setLatitude(localizacao.getLatitude());
        rebelLoc.setLongitude(localizacao.getLongitude());
        rebelLoc.setNome(localizacao.getNome());
        return rebelLoc;
    }

    @GetMapping("/traidores")
    public List<Rebelde> getTraidores(){
        return listaRebeldes().stream().filter(Rebelde::isTraidor).collect(Collectors.toList());
    }

    @PatchMapping("/reportar/{id}")
    public String setTraidor(@PathVariable UUID id){
        for (Rebelde r : listaRebeldes()) {
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

    @GetMapping("/relatorio")
    public Relatorio getRelatorio(){
        int numRebeldes = getRebeldes().size();
        int numTraidores = getTraidores().size();
        int total = numRebeldes + numTraidores;
        double porcentagemRebeldes = 0;
        double porcentagemTraidores = 0;

        if(total > 0) {
            porcentagemRebeldes = (numRebeldes * 100f) / total;
            porcentagemTraidores = (numTraidores * 100f) / total;
        }

        return new Relatorio(fmt.format(porcentagemRebeldes) + "%",
                fmt.format(porcentagemTraidores) + "%", itensPerdidos());
    }

    @GetMapping("/traidores/itens-perdidos")
    public int itensPerdidos() {
        int num = 0;
        for (Rebelde r : getTraidores()) {
         num += r.getInventario().getQtdAgua() + r.getInventario().getQtdMunicao()
                   + r.getInventario().getQtdComida() + r.getInventario().getQtdAgua();
        }
        return num;
    }

    @GetMapping("/inventarios")
    public List<Inventario> getInventarios(){
        List<Inventario> inventarios = new ArrayList<>();

        for (Rebelde r : getRebeldes()) {
            inventarios.add(r.getInventario());
        }

        return inventarios;
    }

}
