package com.example.AdrianPeiro.Controller;

import com.example.AdrianPeiro.JPA.PistaRepository;
import com.example.AdrianPeiro.Modelo.Pista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pistas")
public class PistaController {

    @Autowired
    private PistaRepository pistaRepository;

    @GetMapping
    public ResponseEntity<?> obtenerTodasLasPistas() {
        List<Pista> pistas = pistaRepository.findAll();

        if (pistas.isEmpty()) {
            return ResponseEntity.ok().body(
                    new java.util.HashMap<>() {{
                        put("success", false);
                        put("message", "No se encontraron pistas");
                    }}
            );
        } else {
            return ResponseEntity.ok().body(
                    new java.util.HashMap<>() {{
                        put("success", true);
                        put("pistas", pistas);
                    }}
            );
        }
    }

    @GetMapping("/disponibles")
    public ResponseEntity<?> obtenerPistasDisponibles() {

        List<Pista> pistasDisponibles = pistaRepository.findAll();

        if (pistasDisponibles.isEmpty()) {
            return ResponseEntity.ok().body(
                    new java.util.HashMap<>() {{
                        put("success", false);
                        put("message", "No se encontraron pistas disponibles");
                    }}
            );
        } else {
            return ResponseEntity.ok().body(
                    new java.util.HashMap<>() {{
                        put("success", true);
                        put("pistas", pistasDisponibles);
                    }}
            );
        }
    }
}
