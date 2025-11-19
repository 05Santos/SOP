package com.sop.service;

import com.sop.model.Usuario;
import com.sop.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id){
        return usuarioRepository.findById(id);
    }

    public Usuario salvar(Usuario usuario){
        //Exemplo de validação simples;
        if(usuario.getEmail() == null || usuario.getEmail().isEmpty()){
            throw new IllegalArgumentException("O e-mail é obrigatório.");
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar (Long id, Usuario novoUsuario){
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setEmail(novoUsuario.getEmail());
                    usuario.setNome(novoUsuario.getNome());
                    usuario.setSenha(novoUsuario.getSenha());
                    return usuarioRepository.save(usuario);
                })
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
    public void deletar(long id){
        if(!usuarioRepository.existsById(id)){
            throw new RuntimeException("Usuário não encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}
