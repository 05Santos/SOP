package com.sop.controller;

import com.sop.model.Tarefa;
import com.sop.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    @Autowired
    private TarefaService tarefaService;

    // Listar todas as tarefas
    @GetMapping
    public List<Tarefa> listar() {
        return tarefaService.listarTodas();
    }
    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarPorId(@PathVariable Long id) {
        try {
            Tarefa tarefa = tarefaService.buscarPorId(id);
            return ResponseEntity.ok(tarefa);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    // Criar nova tarefa
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Tarefa tarefa) {
        try {
            Tarefa nova = tarefaService.salvar(tarefa);
            return ResponseEntity.ok(nova);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // Atualizar tarefa
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Tarefa novaTarefa) {
        try {
            Tarefa atualizada = tarefaService.atualizar(id, novaTarefa);
            return ResponseEntity.ok(atualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    // Deletar tarefa
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            tarefaService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
