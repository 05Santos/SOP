package com.sop.repository;

import com.sop.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
    // Método de consulta personalizado.
    // O Spring Data JPA cria automaticamente a query:
    // SELECT * FROM usuario WHERE email = ?;
    //
    // Retorna um Optional para evitar NullPointerException
}
