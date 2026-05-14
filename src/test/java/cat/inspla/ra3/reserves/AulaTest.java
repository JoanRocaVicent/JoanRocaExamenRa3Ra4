package cat.inspla.ra3.reserves;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AulaTest {

    @Test
    void calcularCostReservaAmbHoresValides() {
        Aula aula = new Aula("Aula1", 20);
        double costActual = aula.calcularCostReserva(2);
        assertEquals(24.0, costActual, "El cost de la reserva de 2 hores hauria de ser 24.0");
    }

    @Test
    void crearAulaAmbCapacitatInvalidaLlancaExcepcio() {
        IllegalArgumentException excepcio = assertThrows(
                IllegalArgumentException.class,
                () -> new Aula("Aula1", 0),
                "Hauria de llançar una IllegalArgumentException si la capacitat és 0"
        );
        assertEquals("La capacitat ha de ser positiva", excepcio.getMessage());
    }

    @Test
    void reservarAulaJaReservadaLlancaExcepcio() {
        Aula aula = new Aula("Aula1", 20);
        aula.reservar(); // Primera reserva correcta

        // Comprovem que llança una excepció a la segona reserva
        assertThrows(
                IllegalStateException.class,
                aula::reservar,
                "Hauria de llançar IllegalStateException si ja està reservada"
        );
    }
}