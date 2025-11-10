package com.sop.repository;

import com.sop.model.Notificacoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacoesRepository extends JpaRepository<Notificacoes, Long> {
}
