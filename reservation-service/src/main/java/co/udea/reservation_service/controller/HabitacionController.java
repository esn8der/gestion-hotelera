package co.udea.reservation_service.controller;

import co.udea.reservation_service.dto.HabitacionDTO;
import co.udea.reservation_service.service.HabitacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Petición inválida"),
        @ApiResponse(responseCode = "500", description = "Error interno al procesar la respuesta")
})
@Tag(name = "Habitación", description = "Operaciones relacionadas con las habitaciones")
@RestController
@RequiredArgsConstructor
@RequestMapping("/habitacion")
public class HabitacionController {
    private final HabitacionService habitacionService;

    @Operation(
            summary = "Buscar una Habitación en la base de datos con el ID proporcionado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Habitación encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encuentra la habitación con el id proporcionado en la base de datos")})
    @SecurityRequirement(name = "security_auth")
    @GetMapping("{id}")
    public ResponseEntity<HabitacionDTO> getHabitacion(@PathVariable int id) throws IllegalAccessException {
        return ResponseEntity.ok(habitacionService.getHabitacion(id));
    }

    @Operation(
            summary = "Lista de todas las habitaciones en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Habitaciones encontradas exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encuentran habitaciones en la base de datos")})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity<List<HabitacionDTO>> getHabitaciones() throws IllegalAccessException {
        return ResponseEntity.ok(habitacionService.getHabitaciones());
    }

    @Operation(
            summary = "Buscar Habitaciones en la base de datos con el ID del hotel proporcionado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Habitaciones encontradas exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encuentran habitaciones con el id del hotel proporcionado en la base de datos")})
    @GetMapping("/hotel/{idHotel}")
    public ResponseEntity<List<HabitacionDTO>> getHabitacionesByIdHotel(@PathVariable int idHotel) throws IllegalAccessException {
        return ResponseEntity.ok(habitacionService.getHabitacionesByIdHotel(idHotel));
    }

    @Operation(
            summary = "Guardar una Habitación en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Habitación guardada exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se pudo guardar la habitación en la base de datos")})
    @PostMapping("")
    public ResponseEntity<HabitacionDTO> save(@RequestBody HabitacionDTO habitacionDTO) {
        return ResponseEntity.ok(habitacionService.createHabitacion(habitacionDTO));
    }

    @Operation(
            summary = "Actualizar una Habitación en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Habitación actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se pudo actualizar la habitación en la base de datos")})
    @PutMapping("")
    public ResponseEntity<HabitacionDTO> updateHabitacion(@RequestBody HabitacionDTO habitacionDTO) {
        return ResponseEntity.ok(habitacionService.updateHabitacion(habitacionDTO));
    }

    @Operation(
            summary = "Eliminar una Habitación en la base de datos con el ID proporcionado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Habitación eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se ha podido eliminar la habitación")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabitacion(@PathVariable int id) {
        habitacionService.deleteHabitacion(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Buscar Habitaciones en la base de datos con el tipo proporcionado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Habitaciones encontradas exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encuentran habitaciones con el tipo proporcionado en la base de datos")})
    @GetMapping(params = "tipo")
    public ResponseEntity<List<HabitacionDTO>> findByTipo(@RequestParam("tipo") String tipo)  {
        return ResponseEntity.ok(habitacionService.searchHabitaciones(tipo));
    }
}
