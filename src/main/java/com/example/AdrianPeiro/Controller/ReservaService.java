package com.example.AdrianPeiro.Controller;

import com.example.AdrianPeiro.JPA.PistaRepository;
import com.example.AdrianPeiro.JPA.ReservaRepository;
import com.example.AdrianPeiro.JPA.UsuarioRepository;
import com.example.AdrianPeiro.Modelo.Pista;
import com.example.AdrianPeiro.Modelo.Reserva;
import com.example.AdrianPeiro.Modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PistaRepository pistaRepository;

    private void completarNombres(Reserva r) {
        usuarioRepository.findById(r.getUsuarioId())
                .ifPresent(u -> r.setUsuarioNombre(u.getNombre()));

        pistaRepository.findById(r.getPistaId())
                .ifPresent(p -> r.setPistaNombre(p.getNombre()));
    }

    public List<Reserva> getTodasLasReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        reservas.forEach(this::completarNombres);
        return reservas;
    }

    public List<Reserva> getReservasHoy() {
        String hoy = LocalDate.now().toString();
        List<Reserva> reservas = reservaRepository.findAll().stream()
                .filter(r -> r.getFecha().equals(hoy))
                .collect(Collectors.toList());

        reservas.forEach(this::completarNombres);
        return reservas;
    }

    public List<Reserva> getMisReservas(int usuarioId, String perfil) {
        List<Reserva> reservas;
        if ("Administrador".equals(perfil)) {
            reservas = reservaRepository.findAll();
        } else {
            reservas = reservaRepository.findByUsuarioId(usuarioId);
        }

        reservas.forEach(this::completarNombres);
        return reservas;
    }

    public List<String> getHorasDisponibles(String fecha, int pistaId) {
        List<Reserva> reservas = reservaRepository.findAll().stream()
                .filter(r -> r.getFecha().equals(fecha) && r.getPistaId() == pistaId)
                .collect(Collectors.toList());

        Set<String> ocupadas = new HashSet<>();
        for (Reserva r : reservas) {
            LocalTime inicio = LocalTime.parse(r.getHoraInicio());
            LocalTime fin = LocalTime.parse(r.getHoraFin());
            while (inicio.isBefore(fin)) {
                ocupadas.add(inicio.toString());
                inicio = inicio.plusHours(1);
            }
        }

        List<String> todasLasHoras = new ArrayList<>();
        for (int i = 8; i < 22; i++) {
            todasLasHoras.add(String.format("%02d:00", i));
        }

        String hoy = LocalDate.now().toString();
        String ahora = LocalTime.now().toString().substring(0, 5);

        return todasLasHoras.stream()
                .filter(h -> (!ocupadas.contains(h) && (!fecha.equals(hoy) || h.compareTo(ahora) >= 0)))
                .collect(Collectors.toList());
    }

    public Reserva crearReserva(Reserva reserva) {
        String hoy = LocalDate.now().toString();
        String ahora = LocalTime.now().toString().substring(0, 5);

        if (reserva.getFecha().equals(hoy) && reserva.getHoraInicio().compareTo(ahora) < 0) {
            throw new IllegalArgumentException("La hora solicitada ya ha pasado.");
        }

        long reservasHoy = reservaRepository.findByUsuarioId(reserva.getUsuarioId()).stream()
                .filter(r -> r.getFecha().equals(reserva.getFecha()))
                .count();

        if (reservasHoy >= 4) {
            throw new IllegalArgumentException("No puedes tener más de 4 reservas por día.");
        }

        completarNombres(reserva);
        return reservaRepository.save(reserva);
    }

    public Reserva actualizarReserva(Reserva datos) {
        Reserva r = reservaRepository.findById(datos.getId())
                .orElseThrow(() -> new NoSuchElementException("Reserva no encontrada"));

        String hoy = LocalDate.now().toString();
        String ahora = LocalTime.now().toString().substring(0, 5);

        if (datos.getFecha().equals(hoy) && datos.getHoraInicio().compareTo(ahora) < 0) {
            throw new IllegalArgumentException("La hora solicitada ya ha pasado.");
        }

        r.setUsuarioId(datos.getUsuarioId());
        r.setPistaId(datos.getPistaId());
        r.setFecha(datos.getFecha());
        r.setHoraInicio(datos.getHoraInicio());
        r.setHoraFin(datos.getHoraFin());
        r.setEstado(datos.getEstado());

        completarNombres(r);
        return reservaRepository.save(r);
    }

    public void eliminarReserva(int id) {
        Reserva r = reservaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Reserva no encontrada"));
        reservaRepository.delete(r);
    }

    public List<Reserva> getReservasPorUsuario(int usuarioId) {
        List<Reserva> reservas = reservaRepository.findByUsuarioId(usuarioId);
        reservas.forEach(this::completarNombres);
        return reservas;
    }
}
