package co.udea.reservation_service.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDTO {
    private int id;
    private int idHuesped;
    private int idHabitacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String descripcion;
    private BigDecimal precioTotal;
}
