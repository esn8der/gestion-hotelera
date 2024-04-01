package co.udea.hotel_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelDTO {
    private int id;
    private String nombre;
    private String descripcion;
    private String direccion;
    private String telefono;
    private String email;
    private String ciudad;
    private String pais;
    private String website;
}
