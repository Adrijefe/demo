package com.example.AdrianPeiro.JPA;

import com.example.AdrianPeiro.Modelo.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    // Método para buscar reservas por fecha y pista
    // fecha es String (asegúrate que en Reserva está String, no LocalDate)
    List<Reserva> findByFechaAndPistaId(String fecha, Integer pistaId);

    // Método para buscar reservas por fecha
    List<Reserva> findByFecha(String fecha);

    // Método para buscar reservas por usuario
    List<Reserva> findByUsuarioId(Integer usuarioId);

    // Cuenta cuántas reservas tiene un usuario en una fecha concreta
    long countByUsuarioIdAndFecha(Integer usuarioId, String fecha);


    @Query("SELECT r FROM Reserva r WHERE r.usuarioId = ?1 OR ?2 = 'Administrador'")
    List<Reserva> findByUsuarioOrAdmin(Integer usuarioId, String perfil);


}
