package com.example.AdrianPeiro.Controller;

import com.example.AdrianPeiro.Modelo.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "*")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<Reserva> getTodas() {
        return reservaService.getTodasLasReservas();
    }

    @GetMapping("/hoy")
    public List<Reserva> getReservasHoy() {
        return reservaService.getReservasHoy();
    }

    @GetMapping("/misreservas")
    public List<Reserva> getMisReservas(@RequestParam int usuarioId, @RequestParam String perfil) {
        return reservaService.getMisReservas(usuarioId, perfil);
    }

    @GetMapping("/disponibles")
    public Map<String, Object> getHorasDisponibles(@RequestParam String fecha, @RequestParam int pistaId) {
        List<String> horas = reservaService.getHorasDisponibles(fecha, pistaId);
        return Map.of("success", true, "fecha", fecha, "horas", horas);
    }

    @PostMapping
    public Map<String, Object> crearReserva(@RequestBody Reserva reserva) {
        try {
            Reserva nueva = reservaService.crearReserva(reserva);
            return Map.of("success", true, "message", "Reserva creada", "reserva", nueva);
        } catch (Exception e) {
            return Map.of("success", false, "message", e.getMessage());
        }
    }

    @PutMapping
    public Map<String, Object> actualizarReserva(@RequestBody Reserva reserva) {
        try {
            Reserva actualizada = reservaService.actualizarReserva(reserva);
            return Map.of("success", true, "message", "Reserva actualizada", "reserva", actualizada);
        } catch (Exception e) {
            return Map.of("success", false, "message", e.getMessage());
        }
    }

    @DeleteMapping
    public Map<String, Object> eliminarReserva(@RequestParam int id) {
        try {
            reservaService.eliminarReserva(id);
            return Map.of("success", true, "message", "Reserva eliminada");
        } catch (Exception e) {
            return Map.of("success", false, "message", e.getMessage());
        }
    }

    // Método para obtener reservas por usuarioId
    @GetMapping(params = "usuarioId")
    public List<Reserva> getReservasPorUsuario(@RequestParam int usuarioId) {
        return reservaService.getReservasPorUsuario(usuarioId);
    }
}
