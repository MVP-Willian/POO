package br.com.adocao.model;

public class Solicitacao {
    private String userCpf;
    private Animal animal;
    private String tipo; //resgate-adocao
    private String situacao; //recusada-aprovada-pendente

    //CONSTRUTOR
    public Solicitacao(String userCpf, Animal animal, String tipo, String situacao){
        this.userCpf = userCpf;
        this.animal = animal;
        this.tipo = tipo;
        this.situacao = situacao;
    }

    //GETTERS
    public String getUserCpf(){ return this.userCpf; }
    public Animal getAnimal(){ return this.animal; }
    public String getTipo(){ return this.tipo; }
    public String getSituacao(){ return this.situacao; }

    //SETTERS Uma solicitação, depois de criada, não deveria trocar o usuário, o animal ou o tipo.
    public void setSituacao(String situacao){ this.situacao = situacao; }

}