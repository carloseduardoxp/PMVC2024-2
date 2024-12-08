package br.edu.iftm.tspi.pvmc.sistema_usuarios.controller;

import java.time.LocalDateTime;
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

import br.edu.iftm.tspi.pvmc.sistema_usuarios.domain.Ocorrencia;
import br.edu.iftm.tspi.pvmc.sistema_usuarios.domain.Usuario;
import br.edu.iftm.tspi.pvmc.sistema_usuarios.repository.OcorrenciaRepository;
import br.edu.iftm.tspi.pvmc.sistema_usuarios.repository.UsuarioRepository;

@Controller
@RequestMapping("/ocorrencia")
public class OcorrenciaController {

    private final UsuarioRepository usuarioRepository;
    private final OcorrenciaRepository ocorrenciaRepository;

    public static final String URL_LISTA = "ocorrencia/lista";
    public static final String URL_FORM = "ocorrencia/form";
    public static final String URL_REDIRECT_LISTA = "redirect:/ocorrencia";

    public static final String ATRIBUTO_MENSAGEM = "mensagem";
    public static final String ATRIBUTO_OBJETO = "ocorrencia";
    public static final String ATRIBUTO_LISTA = "ocorrencias";

    public OcorrenciaController(OcorrenciaRepository ocorrenciaRepository,UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.ocorrenciaRepository = ocorrenciaRepository;
    }

    @GetMapping
    public String listar(Model model) {
        List<Ocorrencia> ocorrencias = ocorrenciaRepository.listar();
        model.addAttribute(ATRIBUTO_LISTA,ocorrencias);
        return URL_LISTA;
    }

    @GetMapping("/buscar")
    public String buscarPorDescricao(@RequestParam("descricao") String descricao, Model model) {
        List<Ocorrencia> ocorrenciasBusca = ocorrenciaRepository.buscaPorDescricao(descricao); 
        model.addAttribute(ATRIBUTO_LISTA,ocorrenciasBusca);
        if (ocorrenciasBusca.isEmpty()) {
            model.addAttribute(ATRIBUTO_MENSAGEM, descricao+" não encontrada.");
        } 
        return URL_LISTA; 
    }

    @GetMapping("/novo")
    public String abrirFormNovo(Model model) {
        Ocorrencia ocorrencia = new Ocorrencia();        
        List<Usuario> usuarios = usuarioRepository.listar();
        model.addAttribute("usuarios",usuarios);
        model.addAttribute(ATRIBUTO_OBJETO,ocorrencia);        
        return URL_FORM;
    }

    @GetMapping("/editar/{codigo}")
    public String abrirFormEditar(@PathVariable("codigo") Integer codigo, Model model, RedirectAttributes redirectAttributes) {
        Ocorrencia ocorrenciaBusca = ocorrenciaRepository.buscaPorCodigo(codigo);         
        if (ocorrenciaBusca == null) {
            redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, codigo+" não encontrado.");
            return URL_REDIRECT_LISTA;
        } else {
            model.addAttribute(ATRIBUTO_OBJETO,ocorrenciaBusca);
            List<Usuario> usuarios = usuarioRepository.listar();
            model.addAttribute("usuarios",usuarios);
            return URL_FORM; 
        }        
    }

    @PostMapping("/novo")
    public String salvar(@ModelAttribute("ocorrencia") Ocorrencia ocorrencia, RedirectAttributes redirectAttributes) {
        ocorrencia.setDataHora(LocalDateTime.now());
        ocorrenciaRepository.novo(ocorrencia);
        redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, ocorrencia.getCodigo()+ " salva com sucesso");
        return URL_REDIRECT_LISTA; 
    }

    @PostMapping(value = "/excluir/{id}")
    public String excluir(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        ocorrenciaRepository.delete(id); 
        redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, "Ocorrência excluída com sucesso.");
        return URL_REDIRECT_LISTA; 
    }

    @PostMapping("/editar/{id}")
    public String atualizar(@PathVariable("id") Integer id, @ModelAttribute("ocorrencia") Ocorrencia ocorrencia, RedirectAttributes redirectAttributes) {
        ocorrencia.setDataHora(LocalDateTime.now());
        if (ocorrenciaRepository.update(ocorrencia)) {
            redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, ocorrencia.getCodigo()+ " atualizada com sucesso");
        } else {
            redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, " Não foi possível atualizar "+ocorrencia.getCodigo());
        }        
        return URL_REDIRECT_LISTA; 
    }

}
