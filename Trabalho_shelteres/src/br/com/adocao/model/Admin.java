package br.com.adocao.model;

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
            System.out.println("ADM não aprovado ainda. Não pode aprovar solicitações.");
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