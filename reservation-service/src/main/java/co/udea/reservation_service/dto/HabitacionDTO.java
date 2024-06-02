package co.udea.reservation_service.dto;


import co.udea.reservation_service.model.Habitacion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HabitacionDTO extends Habitacion {
    private int id;
    private String tipo;
    private int camas;
    private int capacidad;
    private int precio;
    private int idEstado;
    private int idHotel;
}
