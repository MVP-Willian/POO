package br.com.adocao.services;

import java.util.ArrayList;
import java.util.List;
import br.com.adocao.model.Resgate;

public class ResgateService {
    private List<Resgate> resgates = new ArrayList<>();

    public void registra(Resgate resgate){
        resgates.add(resgate);
    }

    public List<Resgate> listarSolicitacoes(){
        return resgates.stream()
                .filter(r -> r.getSituacao().equals("solicitado"))
                .toList();
    }
}
