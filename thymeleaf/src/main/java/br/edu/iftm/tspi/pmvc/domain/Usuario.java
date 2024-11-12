package br.edu.iftm.tspi.pmvc.domain;

public class Usuario {

    private String login;

    private String nome;

    private String senha;

    private Boolean administrador;

    public Usuario() {
    }

    public Usuario(String nome, String login, String senha, Boolean administrador) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.administrador = administrador;
    }

    public String getNome() {
        return nome;
    }
    

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAdministrador() {
        return administrador;
    }

    public Boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(Boolean administrador) {
        this.administrador = administrador;
    }

    

}
