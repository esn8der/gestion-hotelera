package co.udea.reservation_service.repository;

import co.udea.reservation_service.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    List<Reserva> findAllByIdHuesped(int idHuesped);

    List<Reserva> findAllByIdHabitacion(int idHabitacion);
}
