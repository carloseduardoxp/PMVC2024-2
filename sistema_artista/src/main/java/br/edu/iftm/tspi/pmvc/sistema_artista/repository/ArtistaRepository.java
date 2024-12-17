package br.edu.iftm.tspi.pmvc.sistema_artista.repository;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.pmvc.sistema_artista.domain.Artista;

@Repository
public class ArtistaRepository {

    private final JdbcTemplate conexao;
    
    public ArtistaRepository(JdbcTemplate conexao) {
        this.conexao = conexao;
    }

    public List<Artista> listar() {
        String sql = """
                      select cod_artista as codigo,
                             nom_artista as nome
                      from tb_artista;
                      """;
        return conexao.query(sql,
                             new BeanPropertyRowMapper<>(Artista.class)
                            );
    }

    public List<Artista> buscarPorNome(String nome) {
        String sql = """
                      select cod_artista as codigo,
                             nom_artista as nome
                      from tb_artista
                      where lower(nom_artista) like ?
                      """;
        return conexao.query(sql,
                            new BeanPropertyRowMapper<>(Artista.class),
                            "%"+nome.toLowerCase()+"%");
    }

    public void salvar(Artista artista) {
        String sql = "insert into tb_artista(nom_artista) values (?)";
        conexao.update(sql,artista.getNome());
    }

    public void atualizar(Artista artista) {
        String sql = "update tb_artista set nom_artista = ? where cod_artista = ?";
        conexao.update(sql,artista.getNome(),artista.getCodigo());
    }

    public void excluir(Integer codigo) {
        String sql = "delete from tb_artista where cod_artista = ?";
        conexao.update(sql,codigo);
    }

    public Artista buscarPorCodigo(Integer codigo) {
        String sql = """
                      select cod_artista as codigo,
                             nom_artista as nome
                      from tb_artista
                      where cod_artista = ?
                      """;    
        return conexao.queryForObject(sql, 
                   new BeanPropertyRowMapper<>(Artista.class),
                   codigo);
    }

}
