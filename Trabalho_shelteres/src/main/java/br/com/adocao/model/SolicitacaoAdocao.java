package br.com.adocao.model;

/**
 * Representa uma solicitação específica para a adoção de um animal.
 * Herda de Solicitacao e adiciona o animal que está sendo solicitado.
 *
 * @see Solicitacao
 * @see Animal
 */
public class SolicitacaoAdocao extends Solicitacao {

    private Animal animal;

    /**
     * Cria uma nova solicitação de adoção.
     *
     * @param u O usuário que deseja adotar.
     * @param a O animal que está sendo solicitado para adoção.
     */
    public SolicitacaoAdocao(User u, Animal a){
        super(u);
        this.animal = a;
    }

    /**
     * Obtém o animal associado a esta solicitação de adoção.
     * @return O objeto Animal.
     */
    public Animal getAnimal() { return animal; }

    /**
     * Gera um resumo formatado da solicitação de adoção.
     * Implementa o metodo abstrato da classe Solicitacao.
     *
     * @return Uma String formatada com detalhes da adoção.
     */
    @Override
    public String resumo() {
        return "Adoção do animal: " + animal.getNome() +
                " | Usuário: " + getSolicitante().getNome() +
                " | Status: " + getStatus();
    }
}