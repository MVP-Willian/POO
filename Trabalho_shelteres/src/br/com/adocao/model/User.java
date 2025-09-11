package br.com.adocao.model;

import java.util.List;
import java.util.ArrayList;

public class User {
    //Atributos
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private float renda;
    private List<Animal> adotados;
    private List<Resgate> solicitResgates;
    private List<Adocao> solicitAdocoes;
    private boolean statusConta = false;

    //Construtor
    public User(String nome, String email, String cpf, String senha, float renda){
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.adotados = new ArrayList<>();
        this.solicitResgates = new ArrayList<>();
        this.solicitAdocoes = new ArrayList<>();
        this.renda = renda;
        this.statusConta = true;

    }
    //Getters e Setters
    //nome
    public String getNome(){ return nome; }
    public void setNome(String nome){ this.nome = nome; }

    //cpf
    public String getCpf() { return cpf; }
    public void setCpf(String cpf){ this.cpf = cpf; }

    //renda
    public float getRenda() { return renda; }
    public void setRenda(float renda){ this.renda = renda; }

    //senha
    public String getSenha(){ return senha; }

    //email
    public String getEmail(){ return email; }
    public void setEmail(String email){ this.email = email; }

    //adotados
    public List<Animal> getAdotados(){ return adotados; }
    public List<Animal> setAdotados(Animal animal){ this.adotados.add(animal); }
    //verificar mudar uma lista toda nao faz sentido, faz sentido adicionar um animal.

    //solicitações de adoções
    public List<Adocao> getSolicitAdocao() { return solicitAdocao; }
    public void setSolicitAdocao(Adocao adocao){ this.solicitAdocao.add(adocao); }

    //solicitações de resgates
    public List<Resgate> getSolicitResgates() { return solicitResgates; }
    public void setSolicitResgates(Resgate resgate) { this.solicitResgates.add(resgate); }

    //MÉTODOS (APENAS OS QUE ENVOLVEM A MESMA CLASSE)

    //editar perfil
    public void editarPerfil(String senhaAtual, String nome, String cpf, float renda, String email){
        if(this.senha.equals(senhaAtual)){
            this.nome = nome;
            this.cpf = cpf;
            this.renda = renda;
            this.email = email;
        }
    }
    //editar senha
    public void mudarSenha(String senhaAtual, String senhaNova){
        if (this.senha.equals(senhaAtual)) {
            this.senha = senhaNova;
        }
    }

    //apagar conta
    public void apagarConta(){
        this.statusConta = false;
    }

    //Efetuar solicitacoes
    public Adocao solicitarAdocao(Animal animal ){
        return new Adocao(this, animal );
    }

    public Resgate solicitarResgate(String especie, String sexo, String local, String caracteristicas){
        return new Resgate(especie, sexo, local, caracteristicas, this);
    }

}

