package co.udea.hotel_service.controller;

import co.udea.hotel_service.dto.HotelDTO;
import co.udea.hotel_service.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Petición inválida"),
        @ApiResponse(responseCode = "500", description = "Error interno al procesar la respuesta")
})
@RestController
@RequiredArgsConstructor
@RequestMapping("/hotel")
public class HotelController {
    private final HotelService hotelService;

    @Operation(
            summary = "Buscar un Hotel en la base de datos con el ID proporcionado y devuelve un objeto de tipo `HotelDTO` que contiene información sobre el Hotel encontrado.",
            tags = {"Hotel"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hotel encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encuentra el hotel con el id proporcionado en la base de datos")})
    @SecurityRequirement(name = "security_auth")
    @GetMapping("{id}")
    public ResponseEntity<HotelDTO> getHotel(@PathVariable int id) throws IllegalAccessException {
        return ResponseEntity.ok(hotelService.getHotel(id));
    }

    @Operation(
            summary = "Lista de todos los hoteles en la base de datos.",
            tags = {"Hoteles"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hoteles encontrados exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encuentran hoteles en la base de datos")})
    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<HotelDTO>> getHotels() throws IllegalAccessException {
        return ResponseEntity.ok(hotelService.getHoteles());
    }

    @Operation(
            summary = "Buscar un Hotel en la base de datos con el nombre proporcionado",
            tags = {"Hotel","Buscar"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hotel encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encuentra el hotel con el nombre proporcionado en la base de datos")})
    @GetMapping(params = "name")
    public ResponseEntity<HotelDTO> findByName(@RequestParam("name") String nombre) throws IllegalAccessException {
        return ResponseEntity.ok(hotelService.findByNombre(nombre));
    }

    @Operation(
            summary = "Guardar un Hotel en la base de datos.",
            tags = {"Hotel", "Guardar"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hotel guardado exitosamente")})
    @PostMapping("/create")
    public ResponseEntity<HotelDTO> createHotel(@RequestBody HotelDTO hotelDTO) {
        return ResponseEntity.ok(hotelService.createHotel(hotelDTO));
    }

    @Operation(
            summary = "Actualizar un Hotel en la base de datos.",
            tags = {"Hotel", "Actualizar"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hotel actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encuentra el hotel a actualizar en la base de datos")})
    @PutMapping("/update")
    public ResponseEntity<HotelDTO> updateHotel(@RequestBody HotelDTO hotelDTO) {
        return ResponseEntity.ok(hotelService.updateHotel(hotelDTO));
    }

    @Operation(
            summary = "Eliminar un Hotel en la base de datos.",
            tags = {"Hotel", "Eliminar"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Hotel eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encuentra el hotel con el id proporcionado en la base de datos")})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable int id) {
        hotelService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Buscar todas las habitaciones de un hotel en la base de datos.",
            tags = {"Habitaciones"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Habitaciones encontradas exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encuentran habitaciones para este hotel en la base de datos")})
    @GetMapping("/habitaciones/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Object>> getHabitacionesByHotel(@PathVariable int id) {
        return ResponseEntity.ok(hotelService.getHabitacionesByHotel(id));
    }

}
