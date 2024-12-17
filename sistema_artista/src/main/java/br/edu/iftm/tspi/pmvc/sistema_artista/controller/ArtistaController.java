package br.edu.iftm.tspi.pmvc.sistema_artista.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.iftm.tspi.pmvc.sistema_artista.domain.Artista;
import br.edu.iftm.tspi.pmvc.sistema_artista.repository.ArtistaRepository;




@Controller
public class ArtistaController {

    private final ArtistaRepository artistaRepository;

    public ArtistaController(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    @GetMapping("/artistas/listar")
    public String listar(Model model) {
        List<Artista> artistas = artistaRepository.listar(); 
        model.addAttribute("artistas", artistas);
        return "artistas/artistaListar";
    }

    @GetMapping("/artistas/novo")
    public String novo(Model model) {
        model.addAttribute("artista",new Artista());
        return "artistas/artistaForm";
    }

    @PostMapping("/artistas/novo")
    public String salvar(@ModelAttribute Artista artista,Model model) {
        if (artista.getCodigo() == null) {
            artistaRepository.salvar(artista);
        } else {
            artistaRepository.atualizar(artista);
        }
        return listar(model);
    }

    // @PathVariable  localhost:8080/artistas/buscar/1
    // @REquestParam  localhost:8080/artistas/buscar?nome=oficina%g3
    @PostMapping("/artistas/excluir/{codigo}")
    public String excluir(@PathVariable Integer codigo,Model model) {
        artistaRepository.excluir(codigo);
        model.addAttribute("mensagem","Excluido com sucesso");
        return listar(model);
    }
    
    @GetMapping("/artistas/editar/{codigo}")
    public String editar(@PathVariable Integer codigo,Model model) {
        Artista artista = artistaRepository.buscarPorCodigo(codigo);
        model.addAttribute("artista",artista);
        return "artistas/artistaForm";
    }

    @GetMapping("/artistas/buscar")
    public String buscar(@RequestParam("nome") String nome,Model model) {
        List<Artista> artistas = artistaRepository.buscarPorNome(nome);
        if (artistas.isEmpty()) {
            model.addAttribute("mensagem", 
                                  "Não tem artistas para o nome informado: "+nome);    
        }
        model.addAttribute("artistas",artistas);
        return "artistas/artistaListar";
    }

}
