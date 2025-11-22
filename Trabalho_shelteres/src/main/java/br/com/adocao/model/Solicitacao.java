package br.com.adocao.model;

/**
 * Classe abstrata que serve como modelo base para todos os tipos de solicitações.
 * Ela define atributos e comportamentos comuns, como ID, solicitante e status,
 * e um método de cancelamento.
 * <p>
 * Esta classe não pode ser instanciada diretamente.
 *
 * @see SolicitacaoAdocao
 * @see SolicitacaoResgate
 */
public abstract class Solicitacao {

    private static int contador = 1;
    private int idSolicitacao;
    private User solicitante;
    private StatusSolicitacao status; //pendente, recusada, cancelada, aprovada

    /**
     * Construtor base para todas as solicitações.
     * Atribui um ID único incremental e define o status inicial como PENDENTE.
     *
     * @param solicitante O usuário que abriu a solicitação.
     */
    public Solicitacao(User solicitante){
        this.solicitante = solicitante;
        this.idSolicitacao = contador++;
        this.status = StatusSolicitacao.PENDENTE;
    }

    // --- Getters e Setters ---

    /**
     * Obtém o ID único da solicitação.
     * @return O ID da solicitação.
     */
    public int getId() { return idSolicitacao; }

    /**
     * Obtém o usuário que criou a solicitação.
     * @return O objeto User do solicitante.
     */
    public User getSolicitante() { return solicitante; }

    /**
     * Obtém o status atual da solicitação.
     * @return O enum StatusSolicitacao (PENDENTE, APROVADO, etc.).
     */
    public StatusSolicitacao getStatus() { return status; }

    /**
     * Define o status da solicitação (usado por Admins ou pelo sistema).
     * @param status O novo StatusSolicitacao.
     */
    public void setStatus(StatusSolicitacao status) { this.status = status; }

    // --- Métodos ---

    /**
     * Permite ao usuário cancelar uma solicitação.
     * A solicitação só pode ser cancelada se ainda estiver PENDENTE.
     */
    public void cancelar(){
        if(status == StatusSolicitacao.PENDENTE){
            status = StatusSolicitacao.CANCELADA;
        }
    }

    /**
     * Método abstrato que força as subclasses a fornecerem um resumo.
     * Deve retornar uma breve descrição formatada da solicitação.
     *
     * @return Uma String com o resumo da solicitação.
     */
    public abstract String resumo();
}