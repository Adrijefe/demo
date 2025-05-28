package com.example.AdrianPeiro;

import com.example.AdrianPeiro.JPA.UsuarioRepository;
import com.example.AdrianPeiro.Modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Integer id) {
        return usuarioRepository.findById(id);
    }

    public Usuario createUsuario(Usuario usuario) {
        if (!usuario.getEmail().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Email inválido");
        }

        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        if (!List.of("Administrador", "Socio").contains(usuario.getPerfil())) {
            throw new IllegalArgumentException("El perfil debe ser 'Administrador' o 'Socio'");
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Long id, Usuario datos) {
        Usuario usuario = usuarioRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        if (datos.getNombre() != null) usuario.setNombre(datos.getNombre());
        if (datos.getTelefono() != null) usuario.setTelefono(datos.getTelefono());
        if (datos.getPassword() != null) {
            usuario.setPassword(passwordEncoder.encode(datos.getPassword()));
        }
        usuario.setEs_socio(datos.getEs_socio());


        if (datos.getPerfil() != null) {
            if (!List.of("Administrador", "Socio").contains(datos.getPerfil())) {
                throw new IllegalArgumentException("El perfil debe ser 'Administrador' o 'Socio'");
            }
            usuario.setPerfil(datos.getPerfil());
        }

        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(String nombre, String email) {
        Usuario usuario = usuarioRepository.findByNombreAndEmail(nombre, email)
                .orElseThrow(() -> new NoSuchElementException("El usuario no existe"));

        usuarioRepository.delete(usuario);
    }
}
