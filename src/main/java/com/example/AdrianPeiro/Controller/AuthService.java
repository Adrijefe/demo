package com.example.AdrianPeiro.Controller;

import com.example.AdrianPeiro.DTO.LoginRequest;
import com.example.AdrianPeiro.DTO.LoginResponse;
import com.example.AdrianPeiro.JPA.UsuarioRepository;
import com.example.AdrianPeiro.Modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public LoginResponse login(LoginRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();

        if (!email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Formato de email inválido");
        }

        Usuario user = usuarioRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email no registrado"));

        boolean passwordMatch;
        String storedPassword = user.getPassword();

        if (storedPassword.length() > 20 && (storedPassword.startsWith("$2a$") || storedPassword.startsWith("$2y$") || storedPassword.startsWith("$2b$"))) {
            passwordMatch = new BCryptPasswordEncoder().matches(password, storedPassword);
        } else {
            passwordMatch = password.equals(storedPassword);
        }


        if (!passwordMatch) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Contraseña incorrecta");
        }

        user.setPassword(null); // no enviar el password de vuelta

        switch (user.getPerfil()) {
            case "Socio":
                return new LoginResponse(true, "Login exitoso para Socio", user);
            case "Administrador":
                return new LoginResponse(true, "Login exitoso para Administrador", user);
            default:
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Perfil no válido");
        }
    }
}
