package co.udea.reservation_service.service;

import co.udea.reservation_service.dto.HabitacionDTO;
import co.udea.reservation_service.mapper.HabitacionMapper;
import co.udea.reservation_service.model.Habitacion;
import co.udea.reservation_service.repositoryimpl.HabitacionRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HabitacionService {
    private final HabitacionRepositoryImpl habitacionRepository;
    private final HabitacionMapper habitacionMapper;

    public HabitacionDTO createHabitacion(HabitacionDTO habitacionDTO) {
        Habitacion habitacion = habitacionMapper.toHabitacion(habitacionDTO);
        habitacion = habitacionRepository.save(habitacion);
        return habitacionMapper.toHabitacionDTO(habitacion);
    }


    public HabitacionDTO getHabitacion(int id) throws IllegalAccessException {
        Optional<Habitacion> optionalHabitacion = habitacionRepository.findById(id);
        if(optionalHabitacion.isEmpty()) {
            throw new IllegalAccessException("No se encontró la habitación con ID: " + id);
        }else{
            return habitacionMapper.toHabitacionDTO(optionalHabitacion.get());
        }
    }

    public List<HabitacionDTO> getHabitaciones() throws IllegalAccessException {
        List<Habitacion> habitaciones = habitacionRepository.findAll();
        List<HabitacionDTO> habitacionesDTO = new ArrayList<>();

        if(habitaciones.isEmpty()) {
            throw new IllegalAccessException("No se encontraron habitaciones");
        }else {
            for (Habitacion habitacion : habitaciones) {
                habitacionesDTO.add(habitacionMapper.toHabitacionDTO(habitacion));
            }
        }
        return habitacionesDTO;
    }

    public List<HabitacionDTO> getHabitacionesByIdHotel(int idHotel) throws IllegalAccessException {
        List<Habitacion> habitaciones = habitacionRepository.findAllByIdHotel(idHotel);
        List<HabitacionDTO> habitacionesDTO = new ArrayList<>();

        if(habitaciones.isEmpty()) {
            throw new IllegalAccessException("No se encontraron habitaciones con el ID del hotel: " + idHotel);
        }else {
            for (Habitacion habitacion : habitaciones) {
                habitacionesDTO.add(habitacionMapper.toHabitacionDTO(habitacion));
            }
        }
        return habitacionesDTO;
    }

    public HabitacionDTO updateHabitacion(HabitacionDTO habitacionDTO) {
        return createHabitacion(habitacionDTO);
    }

    public void deleteHabitacion(int id) {
        Optional<Habitacion> habtiacion = habitacionRepository.findById(id);
        if(habtiacion.isEmpty()) {
            throw new IllegalArgumentException("No se encontró la habitación con ID: " + id);
        }else {
            habitacionRepository.deleteById(id);
        }
    }

    public List<HabitacionDTO> searchHabitaciones(String term) {
        List<Habitacion> habitaciones = habitacionRepository.findAllByTipoContainingIgnoreCase(term);
        List<HabitacionDTO> habitacionDTOs = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            habitacionDTOs.add(habitacionMapper.toHabitacionDTO(habitacion));
        }
        return habitacionDTOs;
    }
}
