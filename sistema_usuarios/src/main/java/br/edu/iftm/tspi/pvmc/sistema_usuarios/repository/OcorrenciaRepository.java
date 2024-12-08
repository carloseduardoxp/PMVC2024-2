package br.edu.iftm.tspi.pvmc.sistema_usuarios.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.pvmc.sistema_usuarios.domain.Ocorrencia;
import br.edu.iftm.tspi.pvmc.sistema_usuarios.domain.Usuario;

@Repository
public class OcorrenciaRepository {

    private final List<Ocorrencia> ocorrencias; 

    public OcorrenciaRepository() {
        this.ocorrencias = new ArrayList<>();
        Usuario cadu = new Usuario("cadu", "tspi", "Carlos", true);
        Usuario amigao = new Usuario("amigao", "teste", "Amigão", true);

        this.ocorrencias.add(new Ocorrencia(1, "bloquear a internet",LocalDateTime.now(),cadu));
        this.ocorrencias.add(new Ocorrencia(2, "colocar todos os laboratórios na mesma rede",LocalDateTime.now(),amigao));
    }

    public List<Ocorrencia> listar() {
        return this.ocorrencias;
    }

    public List<Ocorrencia> buscaPorDescricao(String descricao) {
        List<Ocorrencia> ocorrenciasBusca = new ArrayList<>();
        for (Ocorrencia ocorrencia: this.ocorrencias) {
            if (ocorrencia.getDescricao().toLowerCase().contains(descricao.toLowerCase())) {
                ocorrenciasBusca.add(ocorrencia);
            }
        }
        return ocorrenciasBusca;
    }

    public Ocorrencia buscaPorCodigo(Integer codigo) {
        Ocorrencia ocorrenciaBusca = new Ocorrencia(codigo);        
        int index = ocorrencias.indexOf(ocorrenciaBusca);
        if (index != -1) {
            return ocorrencias.get(index);
        } else {
            return null; 
        }
    }

    public void novo(Ocorrencia ocorrencia) {
        ocorrencias.add(ocorrencia);
    }

    public boolean delete(Integer codigo) {
        Ocorrencia ocorrencia = new Ocorrencia(codigo);        
        return ocorrencias.remove(ocorrencia);
    }

    public boolean update(Ocorrencia ocorrencia) {
        int index = ocorrencias.indexOf(ocorrencia);
        if (index != -1) {
            ocorrencias.set(index, ocorrencia);
            return true;
        }
        return false;
    }
    
}
