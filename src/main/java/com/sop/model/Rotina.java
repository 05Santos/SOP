package com.sop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "rotinas")
public class Rotina {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String nome;
    private String descricao;
    private LocalTime horario;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "rotina", cascade = CascadeType.ALL)
    private List<Tarefa> tarefas;
}
