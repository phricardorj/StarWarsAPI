package br.com.letscode.StarWarsAPI.controller;

import br.com.letscode.StarWarsAPI.model.Inventario;
import br.com.letscode.StarWarsAPI.model.Localizacao;
import br.com.letscode.StarWarsAPI.model.Rebelde;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rebeldes")
public class RebeldeController {

    @GetMapping
    public List<Rebelde> listaRebeldes(){
        Localizacao l1 = new Localizacao(-21.1234, -56.2345, "Galáxia de Andrômeda");
        Inventario i1 = new Inventario(2,23,4,5);
        Rebelde r1 = new Rebelde(UUID.randomUUID(),"Rebelde 1", 28, "masculino", l1, i1);

        Localizacao l2 = new Localizacao(-21.1234, -56.2345, "Galáxia de Andrômeda");
        Inventario i2 = new Inventario(5,35,2,1);
        Rebelde r2 = new Rebelde(UUID.randomUUID(),"Rebelde 2", 24, "feminino", l2, i2);

        r2.setTraidor(true);

        return Arrays.asList(r1,r2);
    }

}
