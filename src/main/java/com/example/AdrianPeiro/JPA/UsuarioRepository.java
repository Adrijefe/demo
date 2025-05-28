package com.example.AdrianPeiro.JPA;

import com.example.AdrianPeiro.Modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByNombreAndEmail(String nombre, String email);
}
