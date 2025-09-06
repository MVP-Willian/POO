package br.com.adocao.model;

public class Resgate {
    private String especie;
    private String sexo;
    private String local;
    private String caracteristicas;
    private String situacao;
    private String nomeUser;

    //Construtor
    public Resgate(String especie, String sexo, String local, String caracteristicas, String situacao, String nomeUser) {
        this.especie = especie;
        this.sexo = sexo;
        this.local = local;
        this.caracteristicas = caracteristicas;
        this.situacao = situacao;
        this.nomeUser = nomeUser;
    }

    }
    //getters
    public String getEspecie(){ return especie; }
    public String getSexo() { return sexo; }
    public String getLocal(){ return local; }
    public String getCaracteristicas(){  return caracteristicas; }
    public String getSituacao(){ return situacao; }
    public String getNomeUser(){ return nomeUser; }

    //setters seguros(Sem a necessidade de permissão de outras classe)
    public void setEspecie(String especie){ this.especie = especie; }
    public void setSexo(String sexo){ this.sexo = sexo; }
    public void setLocal(String local){ this.local = local; }
    public void setCaracteristicas(String caracteristicas{ this.caracteristicas = caracteristicas; })
    public void setNomeUser(String nomeUser){ this.nomeUser = nomeUser; }


}