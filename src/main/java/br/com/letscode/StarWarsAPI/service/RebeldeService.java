package br.com.letscode.StarWarsAPI.service;


import br.com.letscode.StarWarsAPI.dto.RequestNegociar;
import br.com.letscode.StarWarsAPI.model.*;
import br.com.letscode.StarWarsAPI.dto.RequestRebelde;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RebeldeService {

    public static List<Rebelde> getRebeldes(){
        return Rebelde.getRebeldes().stream().filter(rebelde -> !rebelde.isTraidor()).collect(Collectors.toList());
    }

    public static List<Rebelde> getTraidores(){
        return Rebelde.getRebeldes().stream().filter(Rebelde::isTraidor).collect(Collectors.toList());
    }

    public static Rebelde cadastrarRebelde(RequestRebelde form){
        Genero genero = Genero.valueOf(form.getGenero().toUpperCase().trim());
        Localizacao localizacao = new Localizacao(form.getLocalizacao().getLatitude(), form.getLocalizacao().getLongitude(), form.getLocalizacao().getNome());
        Inventario inventario = new Inventario(form.getInventario().getQtdArmas(), form.getInventario().getQtdAgua(), form.getInventario().getQtdMunicao(), form.getInventario().getQtdComida());
        Rebelde rebelde = new Rebelde(UUID.randomUUID(),form.getNome(), form.getIdade(), genero, localizacao, inventario);
        Rebelde.add(rebelde);
        return rebelde;
    }

    public static List<Rebelde> selecionar(UUID id){
        return Rebelde.getRebeldes().stream().filter(rebelde -> rebelde.getId().equals(id)).collect(Collectors.toList());
    }

    public static String deletar(UUID id) {
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
        return Objects.requireNonNull(Rebelde.getRebeldes().stream().filter(rebelde -> rebelde.getId().equals(id)).findFirst().orElse(null)).getLocalizacao();
    }

    public static Localizacao alterarLocalizacao(UUID id, Localizacao localizacao){
        Localizacao rebelLoc = Objects.requireNonNull(Rebelde.getRebeldes().stream().filter(rebelde -> rebelde.getId().equals(id)).findFirst().orElse(null)).getLocalizacao();
        rebelLoc.setLatitude(localizacao.getLatitude());
        rebelLoc.setLongitude(localizacao.getLongitude());
        rebelLoc.setNome(localizacao.getNome());
        return rebelLoc;
    }

    public static String setTraidor(UUID id){
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
        Rebelde fornecedor = null;
        Rebelde receptor = null;

        for (Rebelde f : selecionar(negociar.getRebeldeFornecedor())) {
            fornecedor = f;
        }

        for (Rebelde r : selecionar(negociar.getRebeldeReceptor())) {
            receptor = r;
        }

        if (receptor == null || fornecedor == null) {
            return "Faltam informações do receptor ou fornecedor";
        }

        if (receptor.isTraidor() || fornecedor.isTraidor()) {
            return "Traidor não pode negociar!";
        }

        String itemFornecedor = negociar.getItemFornecedor();
        int qtdItemFornecedor = negociar.getQtdItemFornecedor();
        HashMap<String, Integer> itensFornecedor = new HashMap<>();
        itensFornecedor.put(itemFornecedor, qtdItemFornecedor);

        String itemReceptor = negociar.getItemReceptor();
        int qtdItemReceptor = negociar.getQtdItemReceptor();
        HashMap<String, Integer> itensReceptor = new HashMap<>();
        itensReceptor.put(itemReceptor, qtdItemReceptor);

        HashMap<String, Integer> inventarioReceptor;
        HashMap<String, Integer> inventarioFornecedor;

        try {
            inventarioReceptor = receptor.getInventario().transfere(itensReceptor, itensFornecedor);
            inventarioFornecedor = fornecedor.getInventario().transfere(itensFornecedor, itensReceptor);
        } catch (java.lang.Error e) {
            return "Erro ao realizar a operação!";
        }

        receptor.getInventario().atualizarInventario(inventarioReceptor);
        fornecedor.getInventario().atualizarInventario(inventarioFornecedor);

        return "Sucesso!";
    }

}
