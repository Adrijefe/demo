package com.example.AdrianPeiro.JPA;

import com.example.AdrianPeiro.Modelo.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    // Método para buscar reservas por usuario
    List<Reserva> findByUsuarioId(Integer usuarioId);


}
