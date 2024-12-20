package br.edu.iftm.tspi.pmvc.sistema_artista.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.iftm.tspi.pmvc.sistema_artista.domain.Album;
import br.edu.iftm.tspi.pmvc.sistema_artista.domain.Artista;
import br.edu.iftm.tspi.pmvc.sistema_artista.repository.AlbumRepository;
import br.edu.iftm.tspi.pmvc.sistema_artista.repository.ArtistaRepository;

@Controller
public class AlbumController {

    private final ArtistaRepository artistaRepository;
    private final AlbumRepository albumRepository;

    public AlbumController(AlbumRepository albumRepository,ArtistaRepository artistaRepository) {
        this.albumRepository = albumRepository;
        this.artistaRepository = artistaRepository;
    }

    @GetMapping("/albuns/listar")
    public String listar(Model model) {
        List<Album> albuns = albumRepository.listar(); 
        model.addAttribute("albuns", albuns);
        return "albuns/albumListar";
    }

    @GetMapping("/albuns/novo")
    public String novo(Model model) {
        List<Artista> artistas = artistaRepository.listar();
        model.addAttribute("artistas",artistas);
        model.addAttribute("album",new Album());
        return "albuns/albumForm";
    }

    @PostMapping("/albuns/novo")
    public String salvar(@ModelAttribute Album album,Model model) {
        if (album.getCodigo() == null) {
            albumRepository.salvar(album);
        } else {
            albumRepository.atualizar(album);
        }
        return listar(model);
    }

    // @PathVariable  localhost:8080/artistas/buscar/1
    // @REquestParam  localhost:8080/artistas/buscar?nome=oficina%g3
    @PostMapping("/albuns/excluir/{codigo}")
    public String excluir(@PathVariable Integer codigo,Model model) {
        albumRepository.excluir(codigo);
        model.addAttribute("mensagem","Excluido com sucesso");
        return listar(model);
    }
    
    @GetMapping("/albuns/editar/{codigo}")
    public String editar(@PathVariable Integer codigo,Model model) {
        Album album = albumRepository.buscarPorCodigo(codigo);
        model.addAttribute("album",album);
        List<Artista> artistas = artistaRepository.listar();
        model.addAttribute("artistas",artistas);
        return "albuns/albumForm";
    }

    @GetMapping("/albuns/buscar")
    public String buscar(@RequestParam("nome") String nome,Model model) {
        List<Album> albuns = albumRepository.buscarPorNome(nome);
        if (albuns.isEmpty()) {
            model.addAttribute("mensagem", 
                                  "Não tem albuns para o nome informado: "+nome);    
        }
        model.addAttribute("albuns",albuns);
        return "albuns/albumListar";
    }

}
