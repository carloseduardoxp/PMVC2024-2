package br.edu.iftm.tspi.pmvc.sistema_artista.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        model.addAttribute("mensagem", "O Alexandre faltou");
        return "artistas/artistaListar";
    }

}
