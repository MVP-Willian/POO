package br.com.adocao.model;

public class Adocao{
    private User user;
    private Animal animal;
    private String situacao = "pendente";
    private boolean ativa = false


    //Construtor:
    public Adocao(User user, Animal animal){
        this.user = user;
        this.animal = animal;
        this.ativa = true;
    }

    //getters

    public User getUser() {
        return user;
    }
    public Animal getAnimal() {
        return animal;
    }
    public String getSituacao() {
        return situacao;
    }

    public boolean isAtiva() {
        return ativa;
    }

    //Setters

    public void cancelarAdocao(boolean ativa) {
        this.ativa = "false";
    }


}