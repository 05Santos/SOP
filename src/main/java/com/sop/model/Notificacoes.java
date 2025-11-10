package com.sop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "notificacoes")
public class Notificacoes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensagem;

    @Column(name = "data_hora")
    private LocalTime dataHora;

    @ManyToOne
    @JoinColumn(name = "id_tarefa")
    private Tarefa tarefa;
}
