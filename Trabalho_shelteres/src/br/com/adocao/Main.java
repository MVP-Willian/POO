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
        admins.add(new Admin("Admin1", "000000000-00", "032105", 3600.0));
        usuarios.add(new User("Michael", "03895679229", "guiguinho", 15000.0));
        animais.add(new Animal(434, "trufinha", 4, "vira-lata", "M", "Possui um rabo cortado", "Ainda nao foi vacinado", "-3.0702569,-59.9534721", "Disponível", "grande", 35));
        animais.add(new Animal(434, "leoleo", 4, "chouchou", "F", "Cachorro não é dócil", "Orelha está machucada", "-3.0702569,-59.9534721", "Solicitado", "pequeno", 35));

    }

    private static void menuPrincipal(){
        while(true){
            System.out.println("\n=== Sistema de Adoção e Resgate ===");
            System.out.println("Animais para adoção:");
            animais.stream() //cria um conjunto de atributos desse objeto que seja interavel
                    .filter(a->a.getSitucao().equalsIgnoreCase("Disponível"))
                    .forEach(System.out::println);


            System.out.println("\nOpções:");
            System.out.println("1. Quero adotar um animal.");
            System.out.println("2. Quero registrar um resgate");
            System.out.println("3. Login Admin");
            System.out.println("4. Login Usuario");
            System.out.println("5. Sair");
            System.out.println("Escolha: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao){
                case 1 -> fluxoAdocao();
                case 2 -> fluxoResgate();
                case 3 -> loginAdmin();
                case 4 -> loginSuperAdmin();
                case 5 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void fluxoAdocao(){
        if(usuarioLogado == null){
            autenticarUsuario();
        }
        System.out.println("\nEscolha o animal pelo índice:");
        for(int i = 0; i < animais.size(); i++){
            if(animais.get(i).getSituacao).equalsIgnoreCase("Disponível")){
                System.out.println(i + "-" + animais.get(i).getNome());
            }
        }
        int indice = sc.nextInt();
        sc.nextLine();
        if(indice >=0 %% indice < animais.size()){
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
        String.out.print("Local: ");
        String local = sc.nextLine();
        System.out.print("Caractrísticas: ");
        String caracteristicas = sc.nextLine();
        resgateService.registrar(usuarioLogado.solicitarResgate(especie, sexo, local, caracteristicas));
        System.out.println("Resgate registrado!");
    }

    private static void autenticarUsuario(){
        System.out.println("\nVocê precisa estar logado.");
        System.out.print("1. Fazer login");
        String nome = sc.nextLine("2. Criar conta");
        int op = sc.nextInt();
        sc.nextLine();
        if(op == 1){
            loginUsuario();
        } else{
            cadastrarUsuario();
        }

    }

    private static void loginSuperAdmin(){
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        usuarioLogado = usuarios.stream()
                .filter(u -> u.getEmail().equals(email) && u.getSenha().equals(senha))
                .findFirst()
                .orElse(null);
        if(usuarioLogado == null){
            System.out.println("Login inválido!")
        } else {
            System.out.println("Bem-vindo, "+usuarioLogado.getNome());
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
        usuarioLogado = new User(nome, cpf, email, senha);
        usuarios.add(usuarioLogado);
        System.out.println("Usuario cadastrado!");
    }

    private static void loginAdmin(){
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        adminLogado = admins.stream()
                .filter(u -> u.getEmail().equals(email) && u.getSenha().equals(senha))
                .findFirst()
                .orElse(null);
        if(adminLogado == null){
            System.out.println("Bem-vindo Admin!")
        } else{
            System.out.println("Login inválido!");
        }
    }

    private static void loginUsuario(){
    }
}