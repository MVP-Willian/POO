package br.com.adocao.model;

/**
 * Representa uma solicitação específica para o resgate de um animal.
 * Herda de Solicitacao e adiciona informações sobre o animal
 * a ser resgatado (espécie, local, descrição).
 *
 * @see Solicitacao
 */
public class SolicitacaoResgate extends Solicitacao {

    private String especie;
    private String sexo;
    private String local;
    private String descricao;

    /**
     * Cria uma nova solicitação de resgate.
     *
     * @param user O usuário que está reportando o animal.
     * @param especie A espécie do animal (ex: "Cachorro", "Gato").
     * @param sexo O sexo do animal (ex: "Macho", "Fêmea", "Não sabe").
     * @param local A localização onde o animal foi visto.
     * @param descricao Uma descrição da situação ou condição do animal.
     */
    public SolicitacaoResgate(User user, String especie, String sexo,
                              String local, String descricao){
        super(user);
        this.especie = especie;
        this.sexo = sexo;
        this.local = local;
        this.descricao = descricao;
    }

    /**
     * Gera um resumo formatado da solicitação de resgate.
     * Implementa o metodo abstrato da classe Solicitacao.
     *
     * @return Uma String formatada com detalhes do resgate.
     */
    @Override
    public String resumo(){
        return "Resgate em: " + local +
                " | Descricao: " + descricao +
                " | Usuário: " + getSolicitante().getNome() +
                " | Status: " + getStatus();
    }
}