package co.udea.hotel_service.controller;

import co.udea.hotel_service.dto.HotelDTO;
import co.udea.hotel_service.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotel")
public class HotelController {
    private final HotelService hotelService;


    @GetMapping("{id}")
    public ResponseEntity<HotelDTO> getHotel(@PathVariable int id) throws IllegalAccessException {
        return ResponseEntity.ok(hotelService.getHotel(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<HotelDTO>> getHotels() throws IllegalAccessException {
        return ResponseEntity.ok(hotelService.getHoteles());
    }

    @GetMapping(params = "name")
    public ResponseEntity<HotelDTO> findByName(@RequestParam("name") String nombre) throws IllegalAccessException {
        return ResponseEntity.ok(hotelService.findByNombre(nombre));
    }

    @PostMapping("")
    public ResponseEntity<HotelDTO> save(@RequestBody HotelDTO hotelDTO) {
        return ResponseEntity.ok(hotelService.createHotel(hotelDTO));
    }

    @PutMapping("")
    public ResponseEntity<HotelDTO> updateHotel(@RequestBody HotelDTO hotelDTO) {
        return ResponseEntity.ok(hotelService.updateHotel(hotelDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable int id) {
        hotelService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/habitaciones/{id}")
    public ResponseEntity<List<Object>> getHabitacionesByHotel(@PathVariable int id) {
        return ResponseEntity.ok(hotelService.getHabitacionesByHotel(id));
    }

}
