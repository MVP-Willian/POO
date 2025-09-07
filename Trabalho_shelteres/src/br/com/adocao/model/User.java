package br.com.adocao.model;

import java.util.List;
import java.util.ArrayList;

public class User {
    //Atributos
    private String nome;
    private String cpf;
    private String senha;
    private float renda;
    private List<Animal> adotados;
    private boolean statusConta = false;

    //Construtor
    public User(String nome, String cpf, String senha, float renda){
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.adotados = new ArrayList<>();
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

    //adotados
    public List<Animal> getAdotados(){ return adotados; }
    //verificar mudar uma lista toda nao faz sentido, faz sentido adicionar um animal.


    //MÉTODOS (APENAS OS QUE ENVOLVEM A MESMA CLASSE

    //editar perfil
    public void editarPergil(String senhaAtual, String nome, String cpf, float renda){
        if(this.senha.equals(senhaAtual)){
            this.nome = nome;
            this.cpf = cpf;
            this.renda = renda;
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

public class Admin extends User{
    private boolean ativa = false;

    //Construtor
    public Admin(String nome, String cpf, String senha, float renda){
        super(nome, cpf,  senha, renda);
    }

    //getters
    public boolean isAtiva(){ return ativa; }

    //Métodos:
    public void aprovar(){
        this.ativa = true;
    }
    public void reprovar(){
        this.ativa = false;
    }

    public void aprovarSolicitacao(Solicitacao solicitacao){
        if(this.ativa == true){
            solicitacao.setSituacao("aprovada");
            System.out.println("Solicitação aprovada pelo ADM.");
        }
        else{
            System.out.println("ADM não aprovado ainda. Não pode aprovar solicitações.")
        }
    }


    public void recusarSolicitacao(Solicitacao solicitacao){
        if(this.ativa == true){
            solicitacao.setSituacao("recusada");
            System.out.println("Solicitação recusada pelo ADM.");
        }
        else {
            System.out.println("ADM não aprovado ainda. Não pode reprovar solicitações.");
        }
    }
}

public class superAdmin extends Admin{
    //Construtor:
    public superAdmin(String nome, String cpf, String senha, float renda){
        super(nome, cpf, senha, renda);
        System.out.println("ADM " + novoAdm.getNome() + " adicionado com sucesso!");
    }
    public void removerAdmin(List<Admin> adms, Admin adm){
        if(adms.remove(adm)){
            System.out.println("ADM" + adm.getNome() + "removido com sucesso.");
        }
        else{
            System.out.println("ADM" + adm.getNome() + "não encontrado.");
        }
    }

}