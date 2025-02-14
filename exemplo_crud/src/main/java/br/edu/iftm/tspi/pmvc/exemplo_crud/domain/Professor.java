package br.edu.iftm.tspi.pmvc.exemplo_crud.domain;

public class Professor {

    private Integer codigo;
    
    private String nome;

    private String endereco;

    private String titulacao;

    

    public Professor() {
    }



    public Professor(Integer codigo, String nome, String endereco, String titulacao) {
        this.codigo = codigo;
        this.nome = nome;
        this.endereco = endereco;
        this.titulacao = titulacao;
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



    public String getEndereco() {
        return endereco;
    }



    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }



    public String getTitulacao() {
        return titulacao;
    }



    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        return result;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Professor other = (Professor) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }

    
    

}
