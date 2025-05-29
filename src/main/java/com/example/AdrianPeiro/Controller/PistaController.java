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

    @GetMapping("/seed")
    public String seed() {

        pistaRepository.save(new Pista(1, "Pista Central", "Tierra Batida", "Pista principal del club con capacidad para espectadores", "1.00", "2wCEAAkGBxITEhUREhMWFhUVGBUVFhgVFxUXGBUVGBUXFxUXFRcYHSggGBolHRUVITEhJSkrLi4uFx8zODMsNygtLisBCgoKDg0OGxAQGy8lICUtLi0tLS0uLy0tLy0tLS0tLS0tLS0tLS0tLS0tKy0tLS0tLS0tLS0tLS0tLS0tLS0tLf"));
        pistaRepository.save(new Pista(2, "Pista 2", "Tierra Batida", "Pista de arcilla", "1.00", null));
        pistaRepository.save(new Pista(3, "Pista 3", "Tierra Batida", "Pista de superficie dura para todo tipo de juego", "1.00", null));
        pistaRepository.save(new Pista(4, "Pista 4", "Tierra Batida", "", "1.00", null));
        pistaRepository.save(new Pista(5, "Pista 5", "Dura", null, "1.00", null));
        pistaRepository.save(new Pista(6, "Pista 6", "Dura", null, "1.00", null));
        pistaRepository.save(new Pista(7, "Pista Padel", "Cesped Artificial", "Es una gran oportunidad para disfrutar con tus amigas y pasar una rato divertido", "1.00", null));
        pistaRepository.save(new Pista(8, "Fronton 1", "Dura", null, "1.00", null));
        pistaRepository.save(new Pista(9, "Fronton 2", "Dura", null, "1.00", null));
        return "ok";
    }
}

