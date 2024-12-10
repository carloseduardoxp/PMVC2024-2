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

}
