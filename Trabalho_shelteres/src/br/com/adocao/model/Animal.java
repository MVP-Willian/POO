package br.com.adocao.model;

public class Animal {
    private int id;
    private String nome;
    private int idade;
    private String especie;
    private String sexo;
    private String caracteristica;
    private String historico;
    private String localEncontrado;
    private String situcao;

    //construtor
    public Animal(int id, String nome, int idade, String especie, String sexo, String caracteristica, String historico, String localEncontrado, String situcao){
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.especie = especie;
        this.sexo = sexo;
        this.caracteristica = caracteristica;
        this.historico = historico;
        this.localEncontrado = localEncontrado;
        this.situcao = situcao;
    }

    //getters
    public int getId() { return id; }
    public String getNome(){ return nome; }
    public int getIdade() { return idade; }
    public String getEspecie(){ return especie; }
    public String getSexo(){ return sexo; }
    public String getCaracteristica() { return caracteristica; }
    public String getHistorico(){ return historico; }
    public String getLocalEncontrado() { return localEncontrado; }
    public String getSitucao() { return situcao; }

    //Setters apenas para atributos seguros(que nao dependem de outras classes)
    public void setId(int id){ this.id = id; }
    public void setIdade(int idade){ this.idade = idade; }
    public void setCaracteristica(String caracteristica){ this.caracteristica = caracteristica; }
    public void setHistorico(String historico){ this.historico = historico; }
    public void setLocalEncontrado(String localEncontrado){ this.localEncontrado = localEncontrado; }
}