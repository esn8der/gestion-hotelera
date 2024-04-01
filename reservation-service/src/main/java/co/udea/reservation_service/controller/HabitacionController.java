package co.udea.reservation_service.controller;

import co.udea.reservation_service.dto.HabitacionDTO;
import co.udea.reservation_service.service.HabitacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/habitacion")
public class HabitacionController {
    private final HabitacionService habitacionService;

    @GetMapping("{id}")
    public ResponseEntity<HabitacionDTO> getHabitacion(@PathVariable int id) throws IllegalAccessException {
        return ResponseEntity.ok(habitacionService.getHabitacion(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<HabitacionDTO>> getHabitaciones() throws IllegalAccessException {
        return ResponseEntity.ok(habitacionService.getHabitaciones());
    }

    @GetMapping("/hotel/{idHotel}")
    public ResponseEntity<List<HabitacionDTO>> getHabitacionesByIdHotel(@PathVariable int idHotel) throws IllegalAccessException {
        return ResponseEntity.ok(habitacionService.getHabitacionesByIdHotel(idHotel));
    }

    @PostMapping("")
    public ResponseEntity<HabitacionDTO> save(@RequestBody HabitacionDTO habitacionDTO) {
        return ResponseEntity.ok(habitacionService.createHabitacion(habitacionDTO));
    }

    @PutMapping("")
    public ResponseEntity<HabitacionDTO> updateHabitacion(@RequestBody HabitacionDTO habitacionDTO) {
        return ResponseEntity.ok(habitacionService.updateHabitacion(habitacionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabitacion(@PathVariable int id) {
        habitacionService.deleteHabitacion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(params = "tipo")
    public ResponseEntity<List<HabitacionDTO>> findByTipo(@RequestParam("tipo") String tipo)  {
        return ResponseEntity.ok(habitacionService.searchHabitaciones(tipo));
    }
}
