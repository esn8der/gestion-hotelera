package co.udea.reservation_service.repositoryimpl;

import co.udea.reservation_service.model.Habitacion;

import java.util.List;
import java.util.Optional;

public interface HabitacionRepositoryImpl {
    Habitacion save(Habitacion habitacion);

    Optional<Habitacion> findById(int id);

    List<Habitacion> findAll();

    List<Habitacion> findAllByIdHotel(int idHotel);

    void deleteById(int id);

    List<Habitacion> findAllByTipoContainingIgnoreCase(String term);
}
