package com.sop.controller;

import com.sop.model.Notificacoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificacoes")
public class NotificacoesController {

    @Autowired
    private NotificacoesController notificacaoService;

    // Criar nova notificação
    @PostMapping
    public ResponseEntity<Notificacoes> criar(@RequestBody Notificacoes notificacao) {
        Notificacoes nova = notificacaoService.criarNotificacao(notificacao);
        return ResponseEntity.ok(nova);
    }
    // Listar todas as notificações
    @GetMapping
    public ResponseEntity<List<Notificacoes>> listar() {
        return ResponseEntity.ok(notificacaoService.listarNotificacoes());
    }

    // Listar notificações por usuário
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Notificacoes>> listarPorUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(notificacaoService.listarPorUsuario(idUsuario));
    }

    // Marcar notificação como lida
    @PutMapping("/{id}/lida")
    public ResponseEntity<Notificacoes> marcarComoLida(@PathVariable Long id) {
        Notificacoes lida = notificacaoService.marcarComoLida(id);
        return ResponseEntity.ok(lida);
    }

    // Excluir notificação
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        notificacaoService.excluirNotificacao(id);
        return ResponseEntity.noContent().build();
    }
}



