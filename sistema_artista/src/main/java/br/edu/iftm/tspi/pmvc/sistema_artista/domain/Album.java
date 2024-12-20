package br.edu.iftm.tspi.pmvc.sistema_artista.domain;

public class Album {

    private Integer codigo;

    private String nome;

    private Integer anoLancamento;

    private Artista artista;

    public Album() {

    }

    public Album(Integer codigo,String nome,Integer anoLancamento,Artista artista) {
        this.codigo = codigo;
        this.nome = nome;
        this.anoLancamento = anoLancamento;
        this.artista = artista;
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

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    

}
