package co.udea.reservation_service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "habitacion")
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String tipo;
    private int camas;
    private int capacidad;
    private int precio;
    private int idEstado;
    private int idHotel;

    public Habitacion() {

    }

    public Habitacion(int i, String singleRoom, int i1, int i2, int i3, int i4, int i5) {
        this.id = i;
        this.tipo = singleRoom;
        this.camas = i1;
        this.capacidad = i2;
        this.precio = i3;
        this.idEstado = i4;
        this.idHotel = i5;
    }

}
