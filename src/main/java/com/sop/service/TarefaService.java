package com.sop.service;

import com.sop.model.Tarefa;
import com.sop.model.Usuario;
import com.sop.repository.TarefaRepository;
import com.sop.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Retorna todas as tarefas cadastradas
    public List<Tarefa> listarTodas() {
        return tarefaRepository.findAll();
    }

    public Tarefa buscarPorId(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada."));
    }

    // Salva uma tarefa no banco de dados, validando o usuário antes
    public Tarefa salvar(Tarefa tarefa) {

        if(tarefa.getUsuario() == null || tarefa.getUsuario().getId() == null){
            throw new IllegalArgumentException("Usuário da tarefa não informado.");
        }

        Usuario usuario = usuarioRepository.findById(tarefa.getUsuario().getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        // Vincula o usuário validado à tarefa
        tarefa.setUsuario(usuario);
        return tarefaRepository.save(tarefa);
    }

    public Tarefa atualizar(Long  id, Tarefa novaTarefa) {
        return tarefaRepository.findById(id)
                .map(tarefa -> {
                    tarefa.setTitulo(novaTarefa.getTitulo());
                    tarefa.setDescricao(novaTarefa.getDescricao());
                    return tarefaRepository.save(tarefa);
                })
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    public void deletar(Long  id) {
        if(!tarefaRepository.existsById(id)){
            throw new RuntimeException("Tarefa não encontrada");
        }
        tarefaRepository.deleteById(id);
    }
}
