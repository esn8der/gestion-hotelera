package co.udea.reservation_service.repositoryimpl;

import co.udea.reservation_service.model.Reserva;

import java.util.List;
import java.util.Optional;

public interface ReservaRepositoryImpl {
    Reserva save(Reserva reserva);

    Optional<Reserva> findById(int id);

    List<Reserva> findAll();

    void delete(Reserva reserva);

    List<Reserva> findAllByIdHuesped(int idHuesped);

    List<Reserva> findAllByIdHabitacion(int idHabitacion);
}
