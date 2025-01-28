package br.edu.iftm.tspi.pmvc.login.service;

import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.iftm.tspi.pmvc.login.domain.Login;
import br.edu.iftm.tspi.pmvc.login.repository.LoginRepository;

@Service
public class LoginService {

    private LoginRepository repository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public LoginService(LoginRepository repository) {
        this.repository = repository;
    }

    public boolean verificaLogin(Login loginDigitado) {
        try {
            Login loginBanco = repository.validarLogin(loginDigitado);
            return encoder.matches(loginDigitado.getSenha(),loginBanco.getSenha());
        } catch(EmptyResultDataAccessException e) {
            return false;
        }
    }

    public void salvar(Login login) {
        String senhaCriptografada = encoder.encode(login.getSenha());
        login.setSenha(senhaCriptografada);
        repository.salvar(login);
    }
        
}
