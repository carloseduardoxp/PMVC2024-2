package br.edu.iftm.tspi.pmvc.thymeleaf_sem_internet.domain;

public class Usuario {

    private String login;

    private String nome;

    private String senha;

    private Boolean administrador;

    public Usuario() {
    }

    public Usuario(String login, String nome, String senha, Boolean administrador) {
        this.login = login;
        this.nome = nome;
        this.senha = senha;
        this.administrador = administrador;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public void setAdministrador(Boolean administrador) {
        this.administrador = administrador;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario{");
        sb.append("login=").append(login);
        sb.append(", nome=").append(nome);
        sb.append(", senha=").append(senha);
        sb.append(", administrador=").append(administrador);
        sb.append('}');
        return sb.toString();
    }

    

}
