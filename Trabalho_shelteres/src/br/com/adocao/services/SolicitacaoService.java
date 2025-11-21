package br.com.adocao.services;

import java.util.*;
import br.com.adocao.model.*;

/**
 * Classe de serviço (Service) que gerencia a lógica de negócios
 * relacionada às solicitações.
 * Atua como um repositório central para criar, listar e atualizar
 * o status das solicitações.
 */
public class SolicitacaoService {

    private List<Solicitacao> solicitacoes = new ArrayList<>();

    /**
     * Adiciona uma nova solicitação (de qualquer tipo) ao sistema.
     *
     * @param s A solicitação a ser registrada (pode ser SolicitacaoAdocao
     * ou SolicitacaoResgate).
     */
    public void registrarSolicitacao(Solicitacao s){
        solicitacoes.add(s);
    }

    /**
     * Retorna uma lista de todas as solicitações registradas no sistema.
     *
     * @return Uma lista (List) de objetos Solicitacao.
     */
    public List<Solicitacao> listarSolicitacoes() {
        return solicitacoes;
    }

    /**
     * Filtra e retorna todas as solicitações feitas por um usuário específico.
     *
     * @param u O usuário cujas solicitações devem ser encontradas.
     * @return Uma lista de solicitações filtrada por usuário.
     */
    public List<Solicitacao> listarPorUsuario(User u){
        return solicitacoes.stream()
                .filter(solicitacao -> solicitacao.getSolicitante().equals(u))
                .toList();
    }

    /**
     * Altera o status de uma solicitação para APROVADO, com base no ID.
     *
     * @param id O ID da solicitação a ser aprovada.
     */
    public void aprovar(int id){
        solicitacoes.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .ifPresent(s -> s.setStatus(StatusSolicitacao.APROVADO));
    }

    /**
     * Altera o status de uma solicitação para REPROVADO, com base no ID.
     *
     * @param id O ID da solicitação a ser reprovada.
     */
    public void reprovar(int id){
        solicitacoes.stream()
                .filter(s-> s.getId() ==id)
                .findFirst()
                .ifPresent(s -> s.setStatus(StatusSolicitacao.REPROVADO));
    }
}