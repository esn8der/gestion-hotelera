package co.udea.reservation_service.repository;

import co.udea.reservation_service.model.Habitacion;
import co.udea.reservation_service.repositoryimpl.HabitacionRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitacionRepository extends HabitacionRepositoryImpl, JpaRepository<Habitacion, Integer> {


}
