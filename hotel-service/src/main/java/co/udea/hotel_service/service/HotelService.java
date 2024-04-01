package co.udea.hotel_service.service;

import co.udea.hotel_service.dto.HotelDTO;
import co.udea.hotel_service.mapper.HotelMapper;
import co.udea.hotel_service.model.Hotel;
import co.udea.hotel_service.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;
    private final WebClient.Builder webClientBuilder;

    public HotelDTO createHotel(HotelDTO hotelDTO) {
        Hotel hotel = hotelMapper.toHotel(hotelDTO);
        return hotelMapper.toHotelDTO(hotelRepository.save(hotel));
    }

    public void deleteById(int id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isEmpty()) {
            throw new IllegalArgumentException("Error: Hotel not found");
        } else {
            hotelRepository.deleteById(id);
        }
    }

    public HotelDTO updateHotel(HotelDTO hotelDTO) {
        return createHotel(hotelDTO);
    }

    public HotelDTO findByNombre(String nombre) throws IllegalAccessException {
        Optional<Hotel> optionalHotel = hotelRepository.findByNombreIgnoreCase(nombre);
        if (optionalHotel.isEmpty()) {
            throw new IllegalAccessException("Hotel not found");
        } else {
            return hotelMapper.toHotelDTO(optionalHotel.get());
        }
    }

    public HotelDTO getHotel(int id) throws IllegalAccessException {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isEmpty()) {
            throw new IllegalAccessException("Hotel not found");
        } else {
            return hotelMapper.toHotelDTO(optionalHotel.get());
        }
    }

    public List<HotelDTO> getHoteles() throws IllegalAccessException {
        List<Hotel> hoteles = hotelRepository.findAll();
        List<HotelDTO> hotelDTOs = new ArrayList<>();
        if (hoteles.isEmpty()) {
            throw new IllegalAccessException("No hotels found");
        } else {
            for (Hotel hotel : hoteles) {
                hotelDTOs.add(hotelMapper.toHotelDTO(hotel));
            }
        }
        return hotelDTOs;
    }

    public List<Object> getHabitacionesByHotel(int idHotel) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/habitacion/hotel/" + idHotel)
                .retrieve()
                .bodyToFlux(Object.class)
                .collectList()
                .block();
    }

}