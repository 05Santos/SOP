package com.sop.service;

import com.sop.model.Rotina;
import com.sop.repository.RotinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RotinaService {

    @Autowired
    private RotinaRepository rotinaRepository;

    //Listar todas as rotinas
    public List<Rotina> listarTodos() {
        return rotinaRepository.findAll();
    }

    //Buscar rotina por ID
    public Optional<Rotina> buscarPorId(Long id) {
        return rotinaRepository.findById(id);
    }

    //Criar nova rotina
    public Rotina salvar(Rotina rotina) {
        if (rotina.getNome() == null || rotina.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome da rotina é obrigatório");
        }
        return rotinaRepository.save(rotina);
    }

    //Atualizar rotina
    public Rotina atualizar(Long id, Rotina rotinaAtualizada) {
        return rotinaRepository.findById(id).map(rotina -> {
            rotina.setNome(rotinaAtualizada.getNome());
            rotina.setDescricao(rotinaAtualizada.getDescricao());
            rotina.setHorario(rotinaAtualizada.getHorario());
            rotina.setUsuario(rotinaAtualizada.getUsuario());
            return rotinaRepository.save(rotina);
        }).orElseThrow(() -> new RuntimeException("Rotina não encontrada."));
    }

    //Deletar rotina
    public void deletar(Long id) {
        if(!rotinaRepository.existsById(id)) {
            throw new RuntimeException("Rotina não encontrada");
        }
        rotinaRepository.deleteById(id);
    }
}
