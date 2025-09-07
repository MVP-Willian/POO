package br.com.adocao.model;

public class Resgate {
    private String especie;
    private String sexo;
    private String local;
    private String caracteristicas;
    private String situacao; //solicitado, andamento, conlcuido
    private User user;

    //Construtor
    public Resgate(String especie, String sexo, String local, String caracteristicas, User user) {
        this.especie = especie;
        this.sexo = sexo;
        this.local = local;
        this.caracteristicas = caracteristicas;
        this.situacao = "solicitado";
        this.user = user;
    }


    //getters
    public String getEspecie(){ return especie; }
    public String getSexo() { return sexo; }
    public String getLocal(){ return local; }
    public String getCaracteristicas(){  return caracteristicas; }
    public String getSituacao(){ return situacao; }
    public User user(){ return user; }

    //Setters
    public void setSitucao(String situacao){ this.situacao = situacao;}


}