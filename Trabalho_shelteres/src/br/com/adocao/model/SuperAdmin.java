package br.com.adocao.model;


import java.util.List;
import java.util.ArrayList;


public class SuperAdmin extends Admin{
    //Construtor:
    public SuperAdmin(String nome, String cpf, String email, String senha, float renda){
        super(nome, cpf, email, senha, renda);
        System.out.println("superAdmin " + nome + " adicionado com sucesso!");
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