package br.edu.iftm.tspi.pmvc.sistema_artista.domain;

public class Artista {

    private Integer codigo;

    private String nome;

    public Artista() {
    }

    public Artista(Integer codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    

}
