package co.udea.reservation_service.repository;

import co.udea.reservation_service.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Integer> {

    List<Habitacion> findAllByTipoContainingIgnoreCase(String term);

    List<Habitacion> findAllByIdHotel(int idHotel);
}
