package br.edu.iftm.tspi.pmvc.login.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.batch.BatchProperties.Jdbc;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.pmvc.login.domain.Login;

@Repository
public class LoginRepository {

    public JdbcTemplate db;

    public LoginRepository(JdbcTemplate db) {
        this.db = db;
    }

    public Login validarLogin(Login loginDigitado) {
        String sql = "select usuario,senha from tb_login where usuario = ?";
        return db.queryForObject(
                            sql,
                            new BeanPropertyRowMapper<>(Login.class),
                            loginDigitado.getUsuario());
    }

    public void salvar(Login login) {
        String sql = "insert into tb_login(usuario,senha) values(?,?)";
        db.update(sql,login.getUsuario(),login.getSenha());
    }
        

}
