package co.udea.reservation_service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idHuesped;
    private int idHabitacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String descripcion;
    private BigDecimal precioTotal;
}
