package br.edu.iftm.tspi.pmvc.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.iftm.tspi.pmvc.domain.Usuario;


@Controller
public class UsuarioController {

    @GetMapping("/")
    public String init(Model model) {
        String nomeUsuario = "Carlos Eduardo";        
        Usuario usuario = new Usuario();
        usuario.setNome(nomeUsuario);
        usuario.setAdministrador(true);
        model.addAttribute("nomeUsuario",nomeUsuario);
        model.addAttribute("usuario",usuario);
        List<String> nomes = Arrays.asList("Carlos","Marco","Amanda","Maria");
        model.addAttribute("nomes",nomes);  

        List<Usuario> usuarios = Arrays.asList(
            new Usuario("Carlos", "carlos123", "senha123", true),
            new Usuario("Maria", "maria456", "senha456", false),
            new Usuario("João", "joao789", "senha789", false)
        );
        model.addAttribute("usuarios", usuarios);

        return "usuarioView";
    }

    @GetMapping("/editar/{login}")
    public String mostrarFormularioEdicao(@PathVariable String login, Model model) {                
        Usuario usuario = new Usuario();
        usuario.setLogin(login);
        usuario.setNome("Um nome qualquer");
        usuario.setAdministrador(false);
        usuario.setSenha("umasenhaqualquer");
        model.addAttribute("usuario",usuario);
        return "formUsuario"; 
    }

    @GetMapping("/novo")
    public String novoUsuario(Model model) {            
        Usuario usuario = new Usuario();
        usuario.setLogin("amigao");
        usuario.setSenha("teste");
        usuario.setNome("Amigão da ESPN");
        usuario.setAdministrador(true);
        model.addAttribute("usuario",usuario);
        return "formUsuario"; 
    }

    @GetMapping("/vazio")
    public String usuarioVazio(Model model) {            
        Usuario usuario = new Usuario();
        model.addAttribute("usuario",usuario);
        return "formUsuario"; 
    }


}
