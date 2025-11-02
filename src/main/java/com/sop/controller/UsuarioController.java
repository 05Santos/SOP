package com.sop.controller;

import com.sop.model.Usuario;
import com.sop.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    //Listar todos
    @GetMapping
    public List<Usuario> listar(){
        return usuarioService.listarTodos();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id){
        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Criar novo usuario
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Usuario usuario){
        try{
            Usuario novo = usuarioService.salvar(usuario);
            return ResponseEntity.ok(novo);
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // Atualizar usuário
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            Usuario atualizado = usuarioService.atualizar(id, usuario);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    // Deletar usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            usuarioService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
