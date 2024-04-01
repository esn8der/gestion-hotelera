package co.udea.reservation_service.mapper;

import co.udea.reservation_service.dto.HabitacionDTO;
import co.udea.reservation_service.model.Habitacion;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class HabitacionMapper {
    private final ModelMapper modelMapper;

    public HabitacionMapper() {
        this.modelMapper = new ModelMapper();
        configureMappings();
    }

    private void configureMappings() {
        modelMapper.createTypeMap(Habitacion.class, HabitacionDTO.class)
                .addMapping(Habitacion::getId, HabitacionDTO::setId)
                .addMapping(Habitacion::getTipo, HabitacionDTO::setTipo)
                .addMapping(Habitacion::getPrecio, HabitacionDTO::setPrecio)
                .addMapping(Habitacion::getCapacidad, HabitacionDTO::setCapacidad)
                .addMapping(Habitacion::getCamas, HabitacionDTO::setCamas)
                .addMapping(Habitacion::getIdEstado, HabitacionDTO::setIdEstado);
    }

    public HabitacionDTO toHabitacionDTO(Habitacion habitacion) {
        return modelMapper.map(habitacion, HabitacionDTO.class);
    }

    public Habitacion toHabitacion(HabitacionDTO habitacionDTO) {
        return modelMapper.map(habitacionDTO, Habitacion.class);
    }

}
