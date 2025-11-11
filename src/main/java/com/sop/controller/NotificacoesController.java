package com.sop.controller;

import com.sop.model.Notificacoes;
import com.sop.service.NotificacaoService;
import com.sop.service.RotinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificacoes")
@CrossOrigin(origins = "*")
public class NotificacoesController {

    @Autowired
    private NotificacaoService notificacaoService;
    @Autowired
    private RotinaService rotinaService;

    //Listar todas as notificacoes
    @GetMapping
    public List<Notificacoes> listar() {return notificacaoService.listarTodos();}

    //Buscar rotinas por ID
    @GetMapping("/{id}")
    public ResponseEntity<Notificacoes> buscarPorId(@PathVariable Long id) {
        return notificacaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Criar nova Notificação
    @PostMapping
    public ResponseEntity<Notificacoes> criar(@RequestBody Notificacoes notificacoes) {
        try{
            Notificacoes nova = notificacaoService.salvar(notificacoes);
            return ResponseEntity.ok(nova);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(notificacoes);
        }
    }

    //Atualizar rotina
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Notificacoes notificacoes) {
        try{
            Notificacoes atualizada = notificacaoService.atualizar(id, notificacoes);
            return ResponseEntity.ok(atualizada);
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    //Deletar rotina
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        try {
            notificacaoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}



