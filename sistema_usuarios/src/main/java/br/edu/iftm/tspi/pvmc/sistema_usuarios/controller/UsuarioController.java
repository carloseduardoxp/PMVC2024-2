package br.edu.iftm.tspi.pvmc.sistema_usuarios.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.iftm.tspi.pvmc.sistema_usuarios.domain.Usuario;
import br.edu.iftm.tspi.pvmc.sistema_usuarios.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioRepository repository;

    public static final String URL_LISTA = "usuario/lista";
    public static final String URL_FORM = "usuario/form";
    public static final String URL_REDIRECT_LISTA = "redirect:/usuario";

    public static final String ATRIBUTO_MENSAGEM = "mensagem";
    public static final String ATRIBUTO_OBJETO = "usuario";
    public static final String ATRIBUTO_LISTA = "usuarios";

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String listar(Model model) {
        List<Usuario> usuarios = repository.listar();
        model.addAttribute(ATRIBUTO_LISTA,usuarios);
        return URL_LISTA;
    }

    @GetMapping("/buscar")
    public String buscarPorNome(@RequestParam("nome") String nome, Model model) {
        List<Usuario> usuariosBusca = repository.buscaPorNome(nome); 
        model.addAttribute(ATRIBUTO_LISTA,usuariosBusca);
        if (usuariosBusca.isEmpty()) {
            model.addAttribute(ATRIBUTO_MENSAGEM, nome+" não encontrado.");
        } 
        return URL_LISTA; 
    }

    @GetMapping("/novo")
    public String abrirFormNovo(Model model) {
        Usuario usuario = new Usuario();        
        model.addAttribute(ATRIBUTO_OBJETO,usuario);
        return URL_FORM;
    }

    @GetMapping("/editar/{login}")
    public String abrirFormEditar(@PathVariable("login") String login, Model model, RedirectAttributes redirectAttributes) {
        Usuario usuarioBusca = repository.buscaPorLogin(login);         
        if (usuarioBusca == null) {
            redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, login+" não encontrado.");
            return URL_REDIRECT_LISTA;
        } else {
            model.addAttribute(ATRIBUTO_OBJETO,usuarioBusca);
            return URL_FORM; 
        }        
    }

    @PostMapping("/novo")
    public String salvar(@ModelAttribute("usuario") Usuario usuario, RedirectAttributes redirectAttributes) {
        repository.novo(usuario);
        redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, usuario.getNome()+ " salvo com sucesso");
        return URL_REDIRECT_LISTA; 
    }

    @PostMapping(value = "/excluir/{login}")
    public String excluir(@PathVariable("login") String login, RedirectAttributes redirectAttributes) {
        repository.delete(login); 
        redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, "Usuário excluído com sucesso.");
        return URL_REDIRECT_LISTA; 
    }

    @PostMapping("/editar/{login}")
    public String atualizar(@PathVariable("login") String login, @ModelAttribute("usuario") Usuario usuario, RedirectAttributes redirectAttributes) {
        if (repository.update(usuario)) {
            redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, usuario.getNome()+ " atualizado com sucesso");
        } else {
            redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, " Não foi possível atualizar "+usuario.getNome());
        }        
        return URL_REDIRECT_LISTA; 
    }

}
