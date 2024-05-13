package co.udea.reservation_service.controller;

import co.udea.reservation_service.dto.ReservaDTO;
import co.udea.reservation_service.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Petición inválida"),
        @ApiResponse(responseCode = "500", description = "Error interno al procesar la respuesta")
})
@Tag(name = "Reserva", description = "Operaciones relacionadas con las reservas")
@RestController
@RequiredArgsConstructor
@RequestMapping("/reserva")
public class ReservaController {
    private final ReservaService reservaService;

    @Operation(
            summary = "Buscar una Reserva en la base de datos con el ID proporcionado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encuentra la reserva con el id proporcionado en la base de datos")})
    @GetMapping("{id}")
    public ResponseEntity<ReservaDTO> getReserva(@PathVariable int id) throws IllegalAccessException {
        return ResponseEntity.ok(reservaService.getReserva(id));
    }

    @Operation(
            summary = "Lista de todas reservas en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encuentran reservas en la base de datos")})
    @GetMapping("/getAll")
    public ResponseEntity<List<ReservaDTO>> getReservas() throws IllegalAccessException {
        return ResponseEntity.ok(reservaService.getReservas());
    }

    @Operation(
            summary = "Buscar Reservas en la base de datos con el ID de la habitación proporcionado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservas encontradas exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encuentran reservas con el id de la habitación proporcionado en la base de datos")})
    @GetMapping("/habitacion/{idHabitacion}")
    public ResponseEntity<List<ReservaDTO>> getReservasByIdHabitacion(@PathVariable int idHabitacion) {
        return ResponseEntity.ok(reservaService.getReservasByHabitacion(idHabitacion));
    }

    @Operation(
            summary = "Buscar Reservas en la base de datos con el ID del huésped proporcionado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservas encontradas exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encuentran reservas con el id del huésped proporcionado en la base de datos")})
    @GetMapping("/huesped/{idHuesped}")
    public ResponseEntity<List<ReservaDTO>> getReservasByHuesped(@PathVariable int idHuesped) throws IllegalAccessException {
        return ResponseEntity.ok(reservaService.getReservasByHuesped(idHuesped));
    }

    @Operation(
            summary = "Eliminar una Reserva en la base de datos con el ID proporcionado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Reserva eliminada exitosamente")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable int id) {
        reservaService.deleteReserva(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Guardar una Reserva en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva guardada exitosamente")})
    @PostMapping("")
    public ResponseEntity<ReservaDTO> createReserva(@RequestBody ReservaDTO reservaDTO) {
        return ResponseEntity.ok(reservaService.createReserva(reservaDTO));
    }

    @Operation(
            summary = "Actualizar una Reserva en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encuentra la reserva a actualizar en la base de datos")})
    @PutMapping("")
    public ResponseEntity<ReservaDTO> updateReserva(@RequestBody ReservaDTO reservaDTO) {
        return ResponseEntity.ok(reservaService.updateReserva(reservaDTO));
    }
}
