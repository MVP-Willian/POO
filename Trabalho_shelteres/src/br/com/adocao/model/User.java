package br.com.adocao.model;

import java.util.List;
import java.util.ArrayList;

public class User {
    //Atributos
    private String nome;
    private String cpf;
    private String senha;
    private List<Animal> adotados;

    //Construto
    public User(String nome, String cpf, String senha){
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.adotados = new ArrayList<>();
    }
    //Getters e Setters
    //nome
    public String getNome(){ return nome; }
    public void setNome(String nome){ this.nome = nome; }

    //cpf
    public String getCpf() { return cpf; }
    public void setCpf(String cpf){ this.cpf = cpf; }

    //senha
    public String getSenha(){ return senha; }
    public void setSenha(String senha){ this.senha = senha; }

    //adotados
    public List<Animal> getAdotados(){ return adotados; }
    //verificar mudar uma lista toda nao faz sentido, faz sentido adicionar um animal.
}

public class Admin extends User{
    public Admin(String nome, String cpf, String senha){
        super(nome, cpf,  senha);
    }

}

public class superAdmin extends Admin{
}