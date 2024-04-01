package co.udea.reservation_service.mapper;


import co.udea.reservation_service.dto.ReservaDTO;
import co.udea.reservation_service.model.Reserva;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ReservaMapper {
    private final ModelMapper modelMapper;

    public ReservaMapper() {
        this.modelMapper = new ModelMapper();
        configureMappings();
    }

    private void configureMappings() {
        modelMapper.createTypeMap(Reserva.class, ReservaDTO.class)
                .addMapping(Reserva::getId, ReservaDTO::setId)
                .addMapping(Reserva::getDescripcion, ReservaDTO::setDescripcion)
                .addMapping(Reserva::getFechaInicio, ReservaDTO::setFechaInicio)
                .addMapping(Reserva::getFechaFin, ReservaDTO::setFechaFin)
                .addMapping(Reserva::getIdHabitacion, ReservaDTO::setIdHabitacion)
                .addMapping(Reserva::getIdHuesped, ReservaDTO::setIdHuesped)
                .addMapping(Reserva::getPrecioTotal, ReservaDTO::setPrecioTotal);

    }

    public ReservaDTO toReservaDTO(Reserva reserva) {
        return modelMapper.map(reserva, ReservaDTO.class);
    }

    public Reserva toReserva(ReservaDTO reservaDTO) {
        return modelMapper.map(reservaDTO, Reserva.class);
    }

}
