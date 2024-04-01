package co.udea.reservation_service.service;

import co.udea.reservation_service.dto.ReservaDTO;
import co.udea.reservation_service.mapper.ReservaMapper;
import co.udea.reservation_service.model.Reserva;
import co.udea.reservation_service.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservaService {
    private final ReservaRepository reservaRepository;
    private final ReservaMapper reservaMapper;

    public ReservaDTO createReserva(ReservaDTO reservaDTO) {
        if (isFechasDsponible(reservaDTO.getIdHabitacion(), reservaDTO.getFechaInicio(), reservaDTO.getFechaFin())) {
            Reserva reserva = reservaMapper.toReserva(reservaDTO);
            reserva = reservaRepository.save(reserva);
            return reservaMapper.toReservaDTO(reserva);
        } else {
            throw new IllegalArgumentException("Las fechas especificadas ya están reservadas para esta habitación.");
        }
    }

    public ReservaDTO getReserva(int id) throws IllegalAccessException {
        Optional<Reserva> optionalReserva = reservaRepository.findById(id);
        if (optionalReserva.isEmpty()) {
            throw new IllegalAccessException("No se encontró la reserva con ID: " + id);
        } else {
            return reservaMapper.toReservaDTO(optionalReserva.get());
        }
    }

    public List<ReservaDTO> getReservas() throws IllegalAccessException {
        List<Reserva> reservas = reservaRepository.findAll();
        List<ReservaDTO> reservaDTOs = new ArrayList<>();
        if (reservas.isEmpty()) {
            throw new IllegalAccessException("No se encontraron reservas");
        } else {
            for (Reserva reserva : reservas) {
                reservaDTOs.add(reservaMapper.toReservaDTO(reserva));
            }
        }
        return reservaDTOs;
    }

    public ReservaDTO updateReserva(ReservaDTO reservaDTO) {
        return createReserva(reservaDTO);
    }

    public void deleteReserva(int id) {
        Optional<Reserva> reserva = reservaRepository.findById(id);
        if (reserva.isEmpty()) {
            throw new IllegalArgumentException("No se encontró la reserva con ID: " + id);
        } else {
            reservaRepository.delete(reserva.get());
        }
    }

    public List<ReservaDTO> getReservasByHuesped(int idHuesped) throws IllegalAccessException {
        List<Reserva> optionalReservas = reservaRepository.findAllByIdHuesped(idHuesped);
        List<ReservaDTO> reservaDTOs = new ArrayList<>();
        if (optionalReservas.isEmpty()) {
            throw new IllegalAccessException("No se encontró la reserva con ID de huesped: " + idHuesped);
        } else {
            for (Reserva reserva : optionalReservas) {
                reservaDTOs.add(reservaMapper.toReservaDTO(reserva));
            }
        }
        return reservaDTOs;
    }

    public List<ReservaDTO> getReservasByHabitacion(int idHabitacion) {
        List<Reserva> optionalReservas = reservaRepository.findAllByIdHabitacion(idHabitacion);
        List<ReservaDTO> reservaDTOs = new ArrayList<>();
        if (optionalReservas.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron reservas con ID de habitación: " + idHabitacion);
        } else {
            for (Reserva reserva : optionalReservas) {
                reservaDTOs.add(reservaMapper.toReservaDTO(reserva));
            }
        }
        return reservaDTOs;
    }

    public boolean isFechasDsponible(int idHabitacion, LocalDate fechaInicio, LocalDate fechaFin) {
        List<Reserva> reservas = reservaRepository.findAllByIdHabitacion(idHabitacion);
        for (Reserva reserva : reservas) {
            if ((reserva.getFechaInicio().isBefore(fechaFin) || reserva.getFechaInicio().isEqual(fechaFin)) && (reserva.getFechaFin().isAfter(fechaInicio) || reserva.getFechaFin().isEqual(fechaInicio))) {
                return false; // Hay una superposición, la habitación no está disponible
            }
        }
        return true; // No hay superposiciones, la habitación está disponible
    }

}
