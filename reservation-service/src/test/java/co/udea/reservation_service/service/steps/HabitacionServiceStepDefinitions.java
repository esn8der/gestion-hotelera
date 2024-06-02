package co.udea.reservation_service.service.steps;

import static org.mockito.Mockito.*;

import co.udea.reservation_service.dto.HabitacionDTO;
import co.udea.reservation_service.model.Habitacion;
import co.udea.reservation_service.repository.HabitacionRepository;
import co.udea.reservation_service.service.HabitacionService;
import co.udea.reservation_service.mapper.HabitacionMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class HabitacionServiceStepDefinitions {

    @Mock
    private HabitacionRepository habitacionRepository;

    @Mock
    private HabitacionMapper habitacionMapper;

    @InjectMocks
    private HabitacionService habitacionService;

    private HabitacionDTO habitacionDTO;
    private List<HabitacionDTO> habitacionesDTO;
    private Exception exception;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Given("que existe una habitación con ID {int}")
    public void givenHabitacionExiste(int id) {
        Habitacion habitacion = new Habitacion(id, "Single Room", 1, 1, 100, 1, 1);

        when(habitacionRepository.findById(id)).thenReturn(Optional.of(habitacion));
        when(habitacionMapper.toHabitacionDTO(habitacion)).thenReturn(new HabitacionDTO(id, "Single Room", 1, 1, 100, 1, 1));
    }

    @Given("que no existe una habitación con ID {int}")
    public void givenHabitacionNoExiste(int id) {
        when(habitacionRepository.findById(id)).thenReturn(Optional.empty());
    }

    @When("solicito la habitación con ID {int}")
    public void whenSolicitoHabitacion(int id) {
        try {
            habitacionDTO = habitacionService.getHabitacion(id);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("la respuesta debe contener los detalles de la habitación con ID {int}")
    public void thenLaRespuestaContieneDetalles(int id) {
        assertNotNull(habitacionDTO);
        assertEquals(id, habitacionDTO.getId());
    }

    @Then("debo recibir un error de {string}")
    public void thenReciboError(String mensajeError) {
        assertNotNull(exception);
        assertEquals(mensajeError, exception.getMessage());
    }

    @Given("que existen habitaciones en la base de datos")
    public void givenHabitacionesExisten() {
        List<Habitacion> habitaciones = List.of(
                new Habitacion(1, "Single Room", 1, 1, 100, 1, 1),
                new Habitacion(2, "Double Room", 2, 2, 200, 2, 2)
        );

        when(habitacionRepository.findAll()).thenReturn(habitaciones);
        when(habitacionMapper.toHabitacionDTO(any(Habitacion.class)))
                .thenAnswer(invocation -> {
                    Habitacion habitacion = invocation.getArgument(0);
                    return new HabitacionDTO(habitacion.getId(), habitacion.getTipo(), habitacion.getCamas(), habitacion.getCapacidad(), habitacion.getPrecio(), habitacion.getIdHotel(), habitacion.getIdEstado());
                });
    }

    @Given("que no existen habitaciones en la base de datos")
    public void givenHabitacionesNoExisten() {
        when(habitacionRepository.findAll()).thenReturn(new ArrayList<>());
    }

    @When("solicito todas las habitaciones")
    public void whenSolicitoHabitaciones() {
        try {
            habitacionesDTO = habitacionService.getHabitaciones();
        } catch (Exception e) {
            exception = e;
        }
    }

    @Given("se recibe una solicitud de creación de habitación con tipo {string}, camas {int}, capacidad {int}, precio {int} y hotel ID {int}")
    public void givenSolicitudCreacionHabitacion(String tipo, int camas, int capacidad, int precio, int hotelId) {
        habitacionDTO = new HabitacionDTO();
        habitacionDTO.setTipo(tipo);
        habitacionDTO.setCamas(camas);
        habitacionDTO.setCapacidad(capacidad);
        habitacionDTO.setPrecio(precio);
        habitacionDTO.setIdHotel(hotelId);
    }

    @When("se realiza la creación de la habitación")
    public void whenSeRealizaCreacionHabitacion() {
        try {
            when(habitacionMapper.toHabitacion(any(HabitacionDTO.class))).thenReturn(new Habitacion());
            when(habitacionMapper.toHabitacionDTO(any(Habitacion.class))).thenReturn(new HabitacionDTO());
            when(habitacionRepository.save(any(Habitacion.class))).thenReturn(new Habitacion());
            habitacionDTO = habitacionService.createHabitacion(habitacionDTO);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("la habitación es creada exitosamente")
    public void thenHabitacionCreadaExitosamente() {
        assertNotNull(habitacionDTO);
    }

    @Then("la respuesta debe contener todas las habitaciones")
    public void thenLaRespuestaContieneTodasLasHabitaciones() {
        assertNotNull(habitacionesDTO);
        assertFalse(habitacionesDTO.isEmpty());
    }

    @Then("debo recibir un error de habitaciones {string}")
    public void thenReciboErrorHabitaciones(String mensajeError) {
        assertNotNull(exception);
        assertEquals(mensajeError, exception.getMessage());
    }
}
