package co.udea.reservation_service.controller;

import co.udea.reservation_service.dto.ReservaDTO;
import co.udea.reservation_service.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reserva")
public class ReservaController {
    private final ReservaService reservaService;

    @GetMapping("/getAll")
    public ResponseEntity<List<ReservaDTO>> getReservas() throws IllegalAccessException {
        return ResponseEntity.ok(reservaService.getReservas());
    }

    @GetMapping("{id}")
    public ResponseEntity<ReservaDTO> getReserva(@PathVariable int id) throws IllegalAccessException {
        return ResponseEntity.ok(reservaService.getReserva(id));
    }

    @GetMapping("/habitacion/{idHabitacion}")
    public ResponseEntity<List<ReservaDTO>> getReservasByIdHabitacion(@PathVariable int idHabitacion) {
        return ResponseEntity.ok(reservaService.getReservasByHabitacion(idHabitacion));
    }

    @GetMapping("/huesped/{idHuesped}")
    public ResponseEntity<List<ReservaDTO>> getReservasByHuesped(@PathVariable int idHuesped) throws IllegalAccessException {
        return ResponseEntity.ok(reservaService.getReservasByHuesped(idHuesped));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable int id) {
        reservaService.deleteReserva(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("")
    public ResponseEntity<ReservaDTO> createReserva(@RequestBody ReservaDTO reservaDTO) {
        return ResponseEntity.ok(reservaService.createReserva(reservaDTO));
    }

    @PutMapping("")
    public ResponseEntity<ReservaDTO> updateReserva(@RequestBody ReservaDTO reservaDTO) {
        return ResponseEntity.ok(reservaService.updateReserva(reservaDTO));
    }
}
