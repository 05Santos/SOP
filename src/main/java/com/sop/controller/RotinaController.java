package com.sop.controller;

import com.sop.model.Rotina;
import com.sop.service.RotinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rotinas")
public class RotinaController {

    @Autowired
    private RotinaService rotinaService;

    //Listar todas as rotinas
    @GetMapping
    public List<Rotina> listar() {
        return rotinaService.listarTodos();
    }

    //Buscar rotina por ID
    @GetMapping("/{id}")
    public ResponseEntity<Rotina> buscarPorId(@PathVariable Long id) {
        return rotinaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Criar nova rotina
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Rotina rotina) {
        try{
            Rotina nova =  rotinaService.salvar(rotina);
            return ResponseEntity.ok(nova);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Atualizar rotina
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Rotina rotina) {
        try {
            Rotina atualizada = rotinaService.atualizar(id, rotina);
            return ResponseEntity.ok(atualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar rotina
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        try {
            rotinaService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
