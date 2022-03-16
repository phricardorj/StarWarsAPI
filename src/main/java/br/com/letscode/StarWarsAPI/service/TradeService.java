package br.com.letscode.StarWarsAPI.service;

import br.com.letscode.StarWarsAPI.dto.RequestNegociar;
import br.com.letscode.StarWarsAPI.model.Inventario;
import br.com.letscode.StarWarsAPI.model.Rebelde;
import br.com.letscode.StarWarsAPI.model.Troca;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.letscode.StarWarsAPI.service.RebeldeService.selecionar;

@Service
public class TradeService {

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
