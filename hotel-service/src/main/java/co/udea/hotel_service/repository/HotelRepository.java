package co.udea.hotel_service.repository;

import co.udea.hotel_service.model.Hotel;
import co.udea.hotel_service.repositoryimpl.HotelRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HotelRepository extends HotelRepositoryImpl, JpaRepository<Hotel, Integer> {

}
