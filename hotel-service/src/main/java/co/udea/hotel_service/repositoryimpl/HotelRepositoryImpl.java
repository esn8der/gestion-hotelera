package co.udea.hotel_service.repositoryimpl;

import co.udea.hotel_service.model.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelRepositoryImpl {
    Optional<Hotel> findByNombreIgnoreCase(String nombre);

    Hotel save(Hotel hotel);

    Optional<Hotel> findById(int id);

    void deleteById(int id);

    List<Hotel> findAll();
}
