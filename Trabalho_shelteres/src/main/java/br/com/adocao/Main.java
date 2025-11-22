package br.com.adocao;

import br.com.adocao.model.*;
import br.com.adocao.services.*;
import br.com.adocao.persistence.DatabaseInitializer;

import java.util.*;

/**
 * Classe principal que inicia a aplicação de linha de comando (CLI).
 * Gerencia o fluxo de menus, estado de login (usuário/admin), e
 * interações do usuário com os serviços do sistema.
 * <p>
 * Esta classe utiliza listas em memória para simular um banco de dados.
 */
public class Main {
    // --- Variáveis Estáticas Globais ---

    /** Scanner global para ler a entrada do usuário em todo o sistema. */
    private static Scanner sc = new Scanner(System.in);

    /** Lista em memória que simula a tabela de usuários comuns. */
    private static List<User> usuarios = new ArrayList<>();

    /** Lista em memória que simula a tabela de administradores. */
    private static List<Admin> admins = new ArrayList<>();

    /** Lista em memória que simula a tabela de animais. (Classe Animal não fornecida, mas presumida) */
    private static List<Animal> animais = new ArrayList<>();

    /** Instância do serviço que gerencia a lógica de negócios das solicitações. */
    private static SolicitacaoService solicitacoes = new SolicitacaoService();

    /** Armazena o usuário que está logado no momento. 'null' se ninguém estiver logado. */
    private static User usuarioLogado = null;

    /** Armazena o admin que está logado no momento. 'null' se nenhum admin estiver logado. */
    private static Admin adminLogado = null;

    /**
     * Ponto de entrada (entry point) da aplicação.
     *
     * @param args Argumentos da linha de comando (não utilizados neste projeto).
     */
    public static void main(String[] args) {
        // 1. Inicializa o banco de dados - caso ja esteja criado ele apenas inicializa a conexão caso não ele cria o arquivo e as relações.
        DatabaseInitializer.inicializar();
        // 2. Preenche o sistema com dados de exemplo para teste.
        carregarDadosIniciais();
        // 3. Inicia o loop infinito do menu principal.
        menuPrincipal();
    }

    /**
     * Popula as listas em memória com dados de exemplo (mock data).
     * Isso permite testar a aplicação sem um banco de dados real.
     */
    private static void carregarDadosIniciais() {
        // Adiciona um admin padrão
        admins.add(new Admin("Admin1", "user@gmail.com",
                "21412453412", "032105", 3600.0f));
        // Adiciona um usuário padrão
        usuarios.add(new User("Michael", "michael.vieira@icomp.ufam.edu.br",
                "02423459302", "guiguinho", 15000.0f));
        // Adiciona animais de exemplo
        animais.add(new Animal(434, "trufinha", 4, "vira-lata", "M",
                "Possui um rabo cortado", "Ainda nao foi vacinado",
                "-3.0702569,-59.9534721", "Disponível", "grande", 35));
        animais.add(new Animal(521, "leoleo", 4, "chouchou", "F",
                "Cachorro não é dócil", "Orelha está machucada",
                "-3.0702569,-59.9534721", "Solicitado", "pequeno", 35));
    }

    /**
     * Exibe o menu principal e gerencia o loop de interação com o usuário.
     * O menu exibido se adapta dinamicamente com base no estado de login
     * (usuário comum, admin, ou deslogado).
     */
    private static void menuPrincipal(){
        // Loop infinito para manter o menu ativo
        while(true){
            System.out.println("\n=== Sistema de Adoção e Resgate ===");
            System.out.println("Animais para adoção:");

            // Usando Stream API para filtrar e exibir animais "Disponíveis"
            animais.stream()
                    .filter(a -> a.getSituacao().equalsIgnoreCase("Disponível"))
                    .forEach(System.out::println); // Imprime o toString() de cada animal


            System.out.println("\nOpções:");

            // Bloco 1: Define qual menu exibir com base no login
            if(usuarioLogado == null && adminLogado == null) {
                // Menu para usuário DESLOGADO
                System.out.println("1. Quero adotar um animal.");
                System.out.println("2. Quero registrar um resgate");
                System.out.println("3. Login");
                System.out.println("4. Cadastrar conta");
                System.out.println("5. Sair");
            } else if(adminLogado != null) { // Verifica se é um admin (presumindo que adminLogado é um Admin)
                // Menu para ADMIN LOGADO
                System.out.println("1. Verificar todas as solicitações.");
                System.out.println("2. Verificar todos os animais");
                System.out.println("3. Adicionar um animal.");
                System.out.println("4. Adicionar um resgate.");
                System.out.println("5. Adicionar um Admin");
                System.out.println("6. Logout"); // Corrigido para 6
                System.out.println("7. Sair"); // Corrigido para 7

            } else {
                // Menu para USUÁRIO COMUM LOGADO
                System.out.println("1. Quero adotar um animal.");
                System.out.println("2. Quero registrar um resgate.");
                System.out.println("3. Ver minhas solicitações");
                System.out.println("4. Logout");
                System.out.println("5. Sair");
            }

            System.out.println("Escolha: ");
            int opcao = sc.nextInt();
            sc.nextLine(); // Consome a quebra de linha (Enter) pendente do nextInt()

            // Bloco 2: Processa a escolha do usuário
            if(usuarioLogado == null && adminLogado == null) {
                // Ações para usuário DESLOGADO
                switch (opcao) {
                    case 1 -> fluxoAdocao();
                    case 2 -> fluxoResgate();
                    case 3 -> login();
                    case 4 -> cadastrarUsuario();
                    case 5 -> { System.out.println("Saindo..."); return; } // 'return' sai do método e encerra o loop
                    default -> System.out.println("Opção inválida!");
                }
            } else if(adminLogado != null) {
                // Ações para ADMIN LOGADO
                switch (opcao) {
                    // TODO: Implementar métodos de admin
                    case 1 -> System.out.println("TODO: verificarSolicitacoes()"); //verificarSolicitacoes();
                    case 2 -> System.out.println("TODO: verificarAnimais()"); //verificarAnimais();
                    case 3 -> System.out.println("TODO: adicionarAnimal()"); //adicionarAnimal();
                    case 4 -> fluxoResgate(); // Admin também pode registrar resgate
                    case 5 -> System.out.println("TODO: adicionarAdmin()"); //adicionarAdmin();
                    case 6 -> { adminLogado = null; System.out.println("Logout realizado."); }
                    case 7 -> { System.out.println("Saindo..."); return; }
                    default -> System.out.println("Opção inválida!");
                }
            } else {
                // Ações para USUÁRIO COMUM LOGADO
                switch (opcao) {
                    case 1 -> fluxoAdocao();
                    case 2 -> fluxoResgate();
                    case 3 -> verSolicitacoes();
                    case 4 -> { usuarioLogado = null; System.out.println("Logout realizado."); }
                    case 5 -> { System.out.println("Saindo..."); return; }
                    default -> System.out.println("Opção inválida!");
                }
            }
        }
    }

    /**
     * Controla o fluxo de usuário para registrar uma nova solicitação de adoção.
     * Se o usuário não estiver logado, chama {@link #autenticarUsuario()} primeiro.
     * Lista os animais disponíveis e registra a solicitação.
     */
    private static void fluxoAdocao(){
        // Passo 1: Verificar se o usuário está logado
        if(usuarioLogado == null){
            autenticarUsuario(); // Tenta logar ou cadastrar

            // Se mesmo após a tentativa, o usuário ainda for nulo (ex: cancelou), aborta o fluxo.
            if(usuarioLogado == null){
                System.out.println("Não foi possível logar ou registrar o usuário! Voltando ao menu.");
                return;
            }
        }

        // Passo 2: Listar animais disponíveis para adoção
        System.out.println("\nEscolha o animal pelo índice:");
        for(int i = 0; i < animais.size(); i++){
            // Mostra apenas os animais "Disponíveis"
            if(animais.get(i).getSituacao().equalsIgnoreCase("Disponível")){
                System.out.println(i + " - " + animais.get(i).getNome());
            }
        }

        // Passo 3: Ler a escolha e criar a solicitação
        int indice = sc.nextInt();
        sc.nextLine(); // Consome o \n

        // Valida se o índice está dentro dos limites da lista
        if(indice >= 0 && indice < animais.size()){
            Animal escolhido = animais.get(indice);

            // Cria a solicitação específica de adoção
            SolicitacaoAdocao solicitacaoAdocao = new SolicitacaoAdocao(usuarioLogado, escolhido);

            // Registra a solicitação no serviço
            solicitacoes.registrarSolicitacao(solicitacaoAdocao);
            System.out.println("Solicitação registrada com sucesso!");
        } else{
            System.out.println("Índice inválido!");
        }
    }

    /**
     * Controla o fluxo de usuário para registrar uma nova solicitação de resgate.
     * Se o usuário não estiver logado, força a autenticação.
     * Coleta dados do animal a ser resgatado e registra a solicitação.
     */
    private static void fluxoResgate(){
        // Passo 1: Verificar login
        if(usuarioLogado == null){
            autenticarUsuario();
            // Se a autenticação falhar, aborta.
            if(usuarioLogado == null){
                System.out.println("Login/Cadastro necessário. Voltando ao menu.");
                return;
            }
        }

        // Passo 2: Coletar dados do resgate
        System.out.print("Espécie (ex: Cachorro, Gato): ");
        String especie = sc.nextLine();
        System.out.print("Sexo (ex: Macho, Fêmea, Não sei): ");
        String sexo = sc.nextLine();
        System.out.print("Local (ex: Rua, bairro, ponto de ref.): ");
        String local = sc.nextLine();
        System.out.print("Descrição (estado do animal, etc.): ");
        String descricao = sc.nextLine();

        // Passo 3: Criar e registrar a solicitação de resgate
        SolicitacaoResgate solicitacaoResgate = new SolicitacaoResgate(usuarioLogado, especie, sexo, local, descricao);
        solicitacoes.registrarSolicitacao(solicitacaoResgate);

        System.out.println("Solicitação de resgate registrada!");
    }

    /**
     * Exibe um sub-menu para o usuário escolher entre fazer login ou criar conta.
     * É chamado por fluxos (como adotar ou resgatar) que exigem um usuário logado.
     */
    private static void autenticarUsuario(){
        System.out.println("\nVocê precisa estar logado para esta ação.");
        System.out.println("1. Fazer login");
        System.out.println("2. Criar conta");
        System.out.println("3. Voltar ao Menu Principal");

        int op = sc.nextInt();
        sc.nextLine(); // Consome o \n

        switch (op){
            case 1 -> login();
            case 2 -> cadastrarUsuario();
            case 3 -> {
                System.out.println("Voltando ao menu...");
                return; // Retorna ao fluxo que o chamou (que por sua vez retornará ao menu)
            }
            default -> System.out.println("Opção inválida!");
        }
    }

    /**
     * Solicita email e senha e tenta autenticar um usuário.
     * Verifica primeiro na lista de usuários comuns e, se falhar, na lista de admins.
     * Define a variável de sessão ({@code usuarioLogado} ou {@code adminLogado}) apropriada.
     */
    private static void login(){
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        // Tenta encontrar um usuário comum com o email e senha
        usuarioLogado = usuarios.stream()
                .filter(u -> u.getEmail().equals(email) && u.getSenha().equals(senha))
                .findFirst()
                .orElse(null); // Retorna null se não encontrar

        // Se não for usuário comum, tenta encontrar um admin
        if(usuarioLogado == null){
            adminLogado = admins.stream()
                    .filter(a -> a.getEmail().equals(email) && a.getSenha().equals(senha))
                    .findFirst()
                    .orElse(null);

            if(adminLogado == null){
                // Se não encontrou nem usuário nem admin
                System.out.println("\nLogin ou senha incorreto!");
            }
            else {
                // Login de admin bem-sucedido
                // BUGFIX: A mensagem de boas-vindas do admin estava usando a variável errada (usuarioLogado)
                System.out.println("Bem-vindo Admin, " + adminLogado.getNome());
            }
        } else {
            // Login de usuário comum bem-sucedido
            System.out.println("Bem-vindo, " + usuarioLogado.getNome());
        }
    }

    /**
     * Solicita os dados (nome, cpf, email, etc.) e cria uma nova conta de usuário.
     * O novo usuário é adicionado à lista {@code usuarios} e automaticamente
     * logado no sistema (definindo {@code usuarioLogado}).
     */
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

        // Converte a renda de String para float
        float renda = Float.parseFloat(rendaStr);

        // Cria o novo usuário e o define como logado
        usuarioLogado = new User(nome, email, cpf, senha, renda);
        // Adiciona o novo usuário à "base de dados" em memória
        usuarios.add(usuarioLogado);

        System.out.println("Usuario cadastrado e logado com sucesso!");
    }

    /**
     * Exibe todas as solicitações (com seu resumo) feitas pelo
     * usuário atualmente logado ({@code usuarioLogado}).
     * Utiliza o método polimórfico {@link Solicitacao#resumo()} para
     * exibir os detalhes de cada tipo de solicitação.
     */
    public static void verSolicitacoes(){
        // Busca no serviço apenas as solicitações do usuário logado
        List<Solicitacao> userSolicitacoes = solicitacoes.listarPorUsuario(usuarioLogado);

        // BUGFIX: Verifica se a lista está nula OU vazia
        if(userSolicitacoes == null || userSolicitacoes.isEmpty()){
            System.out.println("Você ainda não fez nenhuma solicitação");
        } else{
            System.out.println("\n=== Minhas Solicitacoes ===");
            // Itera sobre a lista de solicitações genéricas
            for(Solicitacao s: userSolicitacoes){
                // Chama o método polimórfico resumo()
                // O Java decide em tempo de execução qual 'resumo()' chamar
                // (o de SolicitacaoAdocao ou o de SolicitacaoResgate)
                System.out.println("ID: " + s.getId() +
                        " | Status: " + s.getStatus() +
                        " | " + s.resumo());
            }
        }
    }

    // --- Métodos de Admin (TODO) ---
    // (Você pode adicionar os métodos que faltam aqui)

    // private static void verificarSolicitacoes() { ... }
    // private static void verificarAnimais() { ... }
    // private static void adicionarAnimal() { ... }
    // private static void adicionarAdmin() { ... }
}
