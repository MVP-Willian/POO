package br.com.adocao.model;

public class Animal {
    private int id;
    private String nome;
    private int idade;
    private String especie; //gato ou cachorro
    private String sexo;
    private String porte; //pequeno, médio ou grande
    private float peso;
    private String personalidade;
    private String historico;
    private String localEncontrado;
    private String situacao; //solicitAdocao, adotado, recuperacao

    //construtor
    public Animal(int id, String nome, int idade, String especie, String sexo, String personalidade, String historico, String localEncontrado, String situacao, String porte, float peso){
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.especie = especie;
        this.sexo = sexo;
        this.porte = porte;
        this.peso = peso;
        this.personalidade = personalidade;
        this.historico = historico;
        this.localEncontrado = localEncontrado;
        this.situacao = situacao;
    }

    //getters
    public int getId() { return id; }
    public String getNome(){ return nome; }
    public int getIdade() { return idade; }
    public String getEspecie(){ return especie; }
    public String getSexo(){ return sexo; }
    public String getPorte(){ return porte; }
    public float getPeso(){ return peso; }
    public String getPersonalidade() { return personalidade; }
    public String getHistorico(){ return historico; }
    public String getLocalEncontrado() { return localEncontrado; }
    public String getSituacao() { return situacao; }

    //Setters apenas para atributos seguros(que nao dependem de outras classes)
    public void setId(int id){ this.id = id; }
    public void setNome(String nome){ this.nome = nome; }
    public void setEspecie(String especie){ this.especie = especie; }
    public void setSexo(String sexo){ this.sexo = sexo; }
    public void setPorte(String porte){ this.porte = porte; }
    public void setPeso(float peso){ this.peso = peso; }
    public void setIdade(int idade){ this.idade = idade; }
    public void setPersonalidade(String personalidade){ this.personalidade = personalidade; }
    public void setHistorico(String historico){ this.historico = historico; }
    public void setLocalEncontrado(String localEncontrado){ this.localEncontrado = localEncontrado; }

    @Override
    public String toString(){
        return nome + " - " + especie + " - " + idade + " - " + situacao;
    }
}