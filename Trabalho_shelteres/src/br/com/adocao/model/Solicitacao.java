package br.com.adocao.model;

public class Solicitacao {
    private String user;
    private Animal animal;
    private String tipo;
    private String situacao;

    //CONSTRUTOR
    public Solicitacao(String user, Animal animal, String tipo, String situacao){
        this.user = user;
        this.animal = animal;
        this.tipo = tipo;
        this.situacao = situacao;
    }

    //GETTERS
    public String getUser(){ return this.user; }
    public Animal getAnimal(){ return this.animal; }
    public String getTipo(){ return this.tipo; }
    public String getSituacao(){ return this.situacao; }

    //SETTERS Uma solicitação, depois de criada, não deveria trocar o usuário, o animal ou o tipo.
    public void setSituacao(String situacao){ this.situacao = situacao; }

}