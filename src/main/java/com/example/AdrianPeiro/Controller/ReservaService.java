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
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
        ZoneId zonaEspaña = ZoneId.of("Europe/Madrid");
        String hoy = ZonedDateTime.now(zonaEspaña).toLocalDate().toString();

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
        ZoneId zonaEspaña = ZoneId.of("Europe/Madrid");
        ZonedDateTime ahoraZoned = ZonedDateTime.now(zonaEspaña);
        String hoy = ahoraZoned.toLocalDate().toString();
        String ahora = ahoraZoned.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));

        List<Reserva> reservas = reservaRepository.findAll().stream()
                .filter(r -> r.getFecha().equals(fecha) && r.getPistaId() == pistaId)
                .collect(Collectors.toList());

        Set<String> ocupadas = new HashSet<>();
        for (Reserva r : reservas) {
            LocalTime inicio = LocalTime.parse(r.getHoraInicio());
            LocalTime fin = LocalTime.parse(r.getHoraFin());
            while (inicio.isBefore(fin)) {
                ocupadas.add(inicio.format(DateTimeFormatter.ofPattern("HH:mm")));
                inicio = inicio.plusHours(1);
            }
        }

        List<String> todasLasHoras = new ArrayList<>();
        for (int i = 8; i < 22; i++) {
            todasLasHoras.add(String.format("%02d:00", i));
        }

        return todasLasHoras.stream()
                .filter(h -> (!ocupadas.contains(h) && (!fecha.equals(hoy) || h.compareTo(ahora) > 0)))
                .collect(Collectors.toList());
    }


    public Reserva crearReserva(Reserva reserva) {
        ZoneId zonaEspaña = ZoneId.of("Europe/Madrid");
        ZonedDateTime ahoraZoned = ZonedDateTime.now(zonaEspaña);
        String hoy = ahoraZoned.toLocalDate().toString();
        String ahora = ahoraZoned.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));

        if (reserva.getFecha().equals(hoy) && reserva.getHoraInicio().compareTo(ahora) < 0) {
            throw new IllegalArgumentException("La hora solicitada ya ha pasado.");
        }

        long reservasHoy = reservaRepository.findByUsuarioId(reserva.getUsuarioId()).stream()
                .filter(r -> LocalDate.parse(r.getFecha()).equals(LocalDate.parse(reserva.getFecha())))
                .count();

        if (reservasHoy >= 4) {
            throw new IllegalArgumentException("No puedes tener más de 4 reservas por día.");
        }

        return reservaRepository.save(reserva);
    }


    public Reserva actualizarReserva(Reserva datos) {
        ZoneId zonaEspaña = ZoneId.of("Europe/Madrid");
        ZonedDateTime ahoraZoned = ZonedDateTime.now(zonaEspaña);
        String hoy = ahoraZoned.toLocalDate().toString();
        String ahora = ahoraZoned.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));

        Reserva r = reservaRepository.findById(datos.getId())
                .orElseThrow(() -> new NoSuchElementException("Reserva no encontrada"));

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
