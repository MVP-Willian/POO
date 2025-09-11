package br.com.adocao;

import br.com.adocao.model.*;
import br.com.adocao.services.*;

import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static List<User> usuarios = new ArrayList<>();
    private static List<Admin> admins = new ArrayList<>();
    private static List<Animal> animais = new ArrayList<>();
    private static AdocaoService adocaoService = new AdocaoService();
    private static ResgateService resgateService = new ResgateService();
    private static User usuarioLogado = null;
    private static Admin adminLogado = null;

    public static void main(String[] args) {
        carregarDadosIniciais();
        menuPrincipal();
    }

    private static void carregarDadosIniciais() {
        admins.add(new Admin("Admin1", "user@gmail.com", "21412453412", "032105", 3600.0f));
        usuarios.add(new User("Michael", "michael.vieira@icomp.ufam.edu.br", "02423459302", "guiguinho", 15000.0f));
        animais.add(new Animal(434, "trufinha", 4, "vira-lata", "M", "Possui um rabo cortado",
                "Ainda nao foi vacinado", "-3.0702569,-59.9534721", "Disponível", "grande", 35));
        animais.add(new Animal(521, "leoleo", 4, "chouchou", "F", "Cachorro não é dócil",
                "Orelha está machucada", "-3.0702569,-59.9534721", "Solicitado", "pequeno", 35));

    }

    private static void menuPrincipal(){
        while(true){
            System.out.println("\n=== Sistema de Adoção e Resgate ===");
            System.out.println("Animais para adoção:");
            animais.stream() //cria um conjunto de atributos desse objeto que seja interavel
                    .filter(a->a.getSituacao().equalsIgnoreCase("Disponível"))
                    .forEach(System.out::println);


            System.out.println("\nOpções:");
            if(usuarioLogado == null) {
                System.out.println("1. Quero adotar um animal.");
                System.out.println("2. Quero registrar um resgate");
                System.out.println("3. Login");
                System.out.println("4. Cadastrar conta");
                System.out.println("5. Sair");
            } else {
                // Usuário já está logado -> mostrar opções específicas
                System.out.println("1. Quero adotar um animal.");
                System.out.println("2. Quero registrar um resgate.");
                System.out.println("3. Ver minhas solicitações");
                System.out.println("4. Logout");
                System.out.println("5. Sair");
            }
            System.out.println("Escolha: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            if(usuarioLogado == null) {
                switch (opcao) {
                    case 1 -> fluxoAdocao();
                    case 2 -> fluxoResgate();
                    case 3 -> login();
                    case 4 -> cadastrarUsuario();
                    case 5 -> { System.out.println("Saindo..."); return; }
                    default -> System.out.println("Opção inválida!");
                }
            } else{
                switch (opcao) {
                    case 1 -> fluxoAdocao();
                    case 2 -> fluxoResgate();
                    case 3 -> verSolicitacoes();
                    case 4 -> { usuarioLogado = null; System.out.println("Logou realizado."); return; }
                    case 5 -> { System.out.println("Saindo..."); return; }
                    default -> System.out.println("Opção inválida!");
                }
            }
        }
    }

    private static void fluxoAdocao(){
        if(usuarioLogado == null){
            autenticarUsuario();
            if(usuarioLogado == null){
                System.out.println("Não foi possível logar ou registrar o usuário!");
                return;
            }
        }
        System.out.println("\nEscolha o animal pelo índice:");
        for(int i = 0; i < animais.size(); i++){
            if(animais.get(i).getSituacao().equalsIgnoreCase("Disponível")){
                System.out.println(i + "-" + animais.get(i).getNome());
            }
        }
        int indice = sc.nextInt();
        sc.nextLine();
        if(indice >=0 && indice < animais.size()){
            Animal escolhido = animais.get(indice);
            adocaoService.registrar(usuarioLogado.solicitarAdocao(escolhido));
            System.out.println("Solicitação registrada!");
        } else{
            System.out.println("Índice inválido!");
        }
    }

    private static void fluxoResgate(){
        if(usuarioLogado == null){
            autenticarUsuario();

        }

        System.out.print("Espécie: ");
        String especie = sc.nextLine();
        System.out.print("Sexo: ");
        String sexo = sc.nextLine();
        System.out.print("Local: ");
        String local = sc.nextLine();
        System.out.print("Caractrísticas: ");
        String caracteristicas = sc.nextLine();
        resgateService.registrar(usuarioLogado.solicitarResgate(especie, sexo, local, caracteristicas));
        System.out.println("Resgate registrado!");
    }

    private static void autenticarUsuario(){
        System.out.println("\nVocê precisa estar logado.");
        System.out.println("1. Fazer login");
        System.out.println("2. Criar conta");
        System.out.println("3. Menu Principal");
        int op = sc.nextInt();
        sc.nextLine();
        switch (op){
            case 1 -> login();
            case 2 -> cadastrarUsuario();
            case 3 -> {
                System.out.println("Saindo...");
                return;
            }
            default -> System.out.println("Opção inválida!");
        }

    }

    private static void login(){
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        usuarioLogado = usuarios.stream()
                .filter(u -> u.getEmail().equals(email) && u.getSenha().equals(senha))
                .findFirst()
                .orElse(null);
        if(usuarioLogado == null){
            usuarioLogado = admins.stream()
                    .filter(u -> u.getEmail().equals(email) && u.getSenha().equals(senha))
                    .findFirst()
                    .orElse(null);
            if(usuarioLogado == null){
                System.out.println("\nLogin ou senha incorreto!");
            }
            else {
                System.out.println("Bem-vindo Admin, " + usuarioLogado.getNome());
            }
        } else {
            System.out.println("Bem-vindo, " + usuarioLogado.getNome());
        }
    }

    private static void cadastrarUsuario(){
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        System.out.print("Renda: ");
        String rendaStr = sc.nextLine();
        float renda = Float.parseFloat(rendaStr);

        usuarioLogado = new User(nome, email, cpf, senha, renda);
        usuarios.add(usuarioLogado);
        System.out.println("Usuario cadastrado!");
    }

    public static void verSolicitacoes(){

    }

}