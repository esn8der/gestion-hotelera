package co.udea.hotel_service.mapper;

import co.udea.hotel_service.dto.HotelDTO;
import co.udea.hotel_service.model.Hotel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper {
    private final ModelMapper modelMapper;

    public HotelMapper() {
        this.modelMapper = new ModelMapper();
        configureMappings();
    }

    private void configureMappings() {
        modelMapper.createTypeMap(Hotel.class, HotelDTO.class)
                .addMapping(Hotel::getId, HotelDTO::setId)
                .addMapping(Hotel::getNombre, HotelDTO::setNombre)
                .addMapping(Hotel::getDescripcion, HotelDTO::setDescripcion)
                .addMapping(Hotel::getDireccion, HotelDTO::setDireccion)
                .addMapping(Hotel::getTelefono, HotelDTO::setTelefono)
                .addMapping(Hotel::getEmail, HotelDTO::setEmail)
                .addMapping(Hotel::getCiudad, HotelDTO::setCiudad)
                .addMapping(Hotel::getPais, HotelDTO::setPais)
                .addMapping(Hotel::getWebsite, HotelDTO::setWebsite);
    }

    public HotelDTO toHotelDTO(Hotel hotel) {
        return modelMapper.map(hotel, HotelDTO.class);
    }

    public Hotel toHotel(HotelDTO hotelDTO) {
        return modelMapper.map(hotelDTO, Hotel.class);
    }
}
