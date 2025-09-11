package br.com.adocao.services;

import java.util.ArrayList;
import java.util.List;
import br.com.adocao.model.Adocao;

public class AdocaoService {

    private List<Adocao> adocoes = new ArrayList<>();

    public void registrar(Adocao adocao){
        adocoes.add(adocao);
    }

    public List<Adocao> listarPendentes() {
        return adocoes.stream()
                .filter(a -> a.getSituacao().equals("pendente"))
                .toList();
    }
}