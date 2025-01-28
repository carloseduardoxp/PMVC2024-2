package br.edu.iftm.tspi.pmvc.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.iftm.tspi.pmvc.login.domain.Login;
import br.edu.iftm.tspi.pmvc.login.service.LoginService;

@Controller
public class LoginController {

    private LoginService service;

    public LoginController(LoginService service) {
        this.service = service;
    }

    @PostMapping("/login/entrar")
    public String validarLogin(Login login,Model model) {
        if (service.verificaLogin(login)) {
            model.addAttribute("mensagem", "usuario logado com sucesso");
        } else {
            model.addAttribute("mensagem", "usuario ou senha invalidos");
        }
        return "login/login";
    }

    @GetMapping("/")
    public String telaInicial(Model model) {
        return "login/login";
    }

    @GetMapping("/login/telaNovoUsuario")
    public String novo(Model model) {
        return "login/cadastro";
    }

    @PostMapping("/login/novoUsuario")
    public String novoUsuario(Login loginDigitado,Model model) {
        service.salvar(loginDigitado);
        model.addAttribute("mensagem","Usuário "+loginDigitado.getUsuario()+" cadastrado com sucesso"); 
        return "login/login";
    }
    
}
