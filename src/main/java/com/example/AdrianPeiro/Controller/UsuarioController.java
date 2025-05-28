package com.example.AdrianPeiro.Controller;


import com.example.AdrianPeiro.Modelo.Usuario;
import com.example.AdrianPeiro.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<?> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        return usuarios.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "No hay usuarios")) :
                ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable Long id) {
        return usuarioService.getUsuarioById    (Math.toIntExact(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body((Usuario) Map.of("error", "Usuario no encontrado")));
    }

    @PostMapping
    public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario nuevo = usuarioService.createUsuario(usuario);
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Usuario registrado exitosamente",
                    "user_id", nuevo.getId()
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable Long id, @RequestBody Usuario datos) {
        try {
            usuarioService.updateUsuario(id, datos);
            return ResponseEntity.ok(Map.of("success", true, "message", "Usuario actualizado"));
        } catch (NoSuchElementException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUsuario(@RequestParam String nombre, @RequestParam String email) {
        try {
            usuarioService.deleteUsuario(nombre, email);
            return ResponseEntity.ok(Map.of("success", true, "message", "Usuario eliminado exitosamente"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("success", false, "message", e.getMessage()));
        }
    }
}
