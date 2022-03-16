package br.com.letscode.StarWarsAPI.service;

import br.com.letscode.StarWarsAPI.model.Inventario;
import br.com.letscode.StarWarsAPI.model.InventarioRelatorio;
import br.com.letscode.StarWarsAPI.model.Rebelde;
import br.com.letscode.StarWarsAPI.model.Relatorio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static br.com.letscode.StarWarsAPI.service.RebeldeService.getRebeldes;
import static br.com.letscode.StarWarsAPI.service.RebeldeService.getTraidores;

@Service @Slf4j
public class RelatorioService {

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

}
