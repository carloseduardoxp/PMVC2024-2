package br.edu.iftm.tspi.pmvc.sistema_artista.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.pmvc.sistema_artista.domain.Album;
import br.edu.iftm.tspi.pmvc.sistema_artista.domain.Artista;

@Repository
public class AlbumRepository {

    private final JdbcTemplate conexao;
    
    public AlbumRepository(JdbcTemplate conexao) {
        this.conexao = conexao;
    }

    public List<Album> listar() {
        String sql = """
                      select cod_album,
                             nom_album,
                             ano_lancamento,
                             al.cod_artista,
                             a.nom_artista 
                      from tb_album al,tb_artista a
                      where al.cod_artista = a.cod_artista;
                      """;
        return conexao.query(sql,
                            new AlbumRowMapper()
                            );
    }

    public List<Album> buscarPorNome(String nome) {
        String sql = """
                      select cod_album,
                             nom_album,
                             ano_lancamento,
                             al.cod_artista,
                             a.nom_artista 
                      from tb_album al,tb_artista a
                      where al.cod_artista = a.cod_artista
                      and lower(nom_album) like ?
                      """;
        return conexao.query(sql,
                            new AlbumRowMapper(),
                            "%"+nome.toLowerCase()+"%");
    }

    public void salvar(Album album) {
        String sql = """
                       insert into tb_album
                          (nom_album,ano_lancamento,cod_artista)
                       values (?,?,?)
                     """;
        conexao.update(sql,album.getNome(),
                           album.getAnoLancamento(),
                           album.getArtista().getCodigo());
    }

    public void atualizar(Album album) {
        String sql = """
                       update tb_album 
                       set nom_album = ?,
                           ano_lancamento = ?,
                           cod_artista = ?
                       where cod_album = ?
                     """;
        conexao.update(sql,album.getNome(),
                           album.getAnoLancamento(),
                           album.getArtista().getCodigo(),
                           album.getCodigo());
    }

    public void excluir(Integer codigo) {
        String sql = "delete from tb_album where cod_album = ?";
        conexao.update(sql,codigo);
    }

    public Album buscarPorCodigo(Integer codigo) {
        String sql = """
                      select cod_album,
                             nom_album,
                             ano_lancamento,
                             al.cod_artista,
                             a.nom_artista 
                      from tb_album al,tb_artista a
                      where al.cod_artista = a.cod_artista
                      and cod_album = ?
                      """;    
        return conexao.queryForObject(sql, 
                   new AlbumRowMapper(),
                   codigo);
    }

}

class AlbumRowMapper implements RowMapper<Album> {

    @Override
    public Album mapRow(ResultSet rs, int rowNum) throws SQLException {
        Album album = new Album();
        album.setCodigo(rs.getInt("cod_album"));
        album.setNome(rs.getString("nom_album"));
        album.setAnoLancamento(rs.getInt("ano_lancamento"));

        Artista artista = new Artista();
        artista.setCodigo(rs.getInt("cod_artista")); 
        artista.setNome(rs.getString("nom_artista"));
        album.setArtista(artista);

        return album;
    }
}
