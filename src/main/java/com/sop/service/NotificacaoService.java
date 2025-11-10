package com.sop.service;

import com.sop.model.Notificacoes;
import com.sop.repository.NotificacoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacaoService {
    @Autowired
    private NotificacoesRepository notificacoesRepository;

    // Listar todas as notificações
    public List<Notificacoes> listarTodos(){
        return notificacoesRepository.findAll();
    }

    // Buscar notificação por ID
    public Optional<Notificacoes> buscarPorId(Long id) {
        return notificacoesRepository.findById(id);
    }

    // Criar nova notificação
    public Notificacoes salvar(Notificacoes notificacao) {
        if (notificacao.getMensagem() == null || notificacao.getMensagem().isEmpty()) {
            throw new IllegalArgumentException("A mensagem da notificação é obrigatória");
        }
        return notificacoesRepository.save(notificacao);
    }

    // Atualizar notificação
    public Notificacoes atualizar(Long id, Notificacoes notificacaoAtualizada) {
        return notificacoesRepository.findById(id).map(notificacao -> {
            notificacao.setMensagem(notificacaoAtualizada.getMensagem());
            notificacao.setDataHora(notificacaoAtualizada.getDataHora());
            notificacao.setTarefa(notificacaoAtualizada.getTarefa());
            return notificacoesRepository.save(notificacao);
        }).orElseThrow(() -> new RuntimeException("Notificação não encontrada."));
    }

    // Deletar notificação
    public void deletar(Long id) {
        if (!notificacoesRepository.existsById(id)) {
            throw new RuntimeException("Notificação não encontrada.");
        }
        notificacoesRepository.deleteById(id);
    }
}
