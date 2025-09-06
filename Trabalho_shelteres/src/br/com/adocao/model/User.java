package br.com.adocao.model;

import java.util.List;
import java.util.ArrayList;

public class User {
    //Atributos
    private String nome;
    private String cpf;
    private String senha;
    private List<Animal> adotados;
    private boolean statusConta;

    //Construto
    public User(String nome, String cpf, String senha){
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.adotados = new ArrayList<>();
        this.statusConta = true;

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


    //MÉTODOS (APENAS OS QUE ENVOLVEM A MESMA CLASSE

    //editar perfil
    public void editarPergil(String senhaAtual, String nome, String cpf){
        if(this.senha.equals(senhaAtual)){
            this.nome = nome;
            this.cpf = cpf;
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
    public Solicitacao solicitarAdocao(Animal animal){
        return new Solicitacao(this.cpf, animal, "adocao", "pendente");
    }

    public Solicitacao solicitarResgate(Animal animal){
        return new Solicitacao(this.cpf, animal, "Resgate", "pendente");
    }

}

public class Admin extends User{
    public Admin(String nome, String cpf, String senha){
        super(nome, cpf,  senha);
    }
    public void aprovarSolicitacao(Solicitacao solicitacao){
        solicitacao.setSituacao("aprovada");
        System.out.println("Solicitação aprovada pelo ADM.");
    }
    public void recusarSolicitacao(Solicitacao solicitacao){
        solicitacao.setSituacao("recusada");
        System.out.println("Solicitação recusada pelo ADM.");
    }
}

public class superAdmin extends Admin{
}