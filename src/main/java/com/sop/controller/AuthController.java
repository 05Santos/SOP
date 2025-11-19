package com.sop.controller;

import com.sop.model.Usuario;
import com.sop.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public Object login(@RequestBody Map<String, String> dadosLogin) {
        String email = dadosLogin.get("email");
        String senha = dadosLogin.get("senha");

        //Busca o usuário
        Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);
        if (usuario == null) {
            // Email errado
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Email incorreto");
            return erro;
        }
        // Compara senha
        if (!usuario.getSenha().equals(senha)) {
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Senha incorreta");
            return erro;
        }
        // Retorna apenas o necessário
        Map<String, Object> retorno = new HashMap<>();
        retorno.put("id", usuario.getId());
        retorno.put("nome", usuario.getNome());
        retorno.put("email", usuario.getEmail());

        return retorno;
    }
}
