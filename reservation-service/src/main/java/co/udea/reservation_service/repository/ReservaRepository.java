package co.udea.reservation_service.repository;

import co.udea.reservation_service.model.Reserva;
import co.udea.reservation_service.repositoryimpl.ReservaRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends ReservaRepositoryImpl, JpaRepository<Reserva, Integer> {
}
