package cat.inspla.ra3.reserves;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AulaTest {

    @Test
    void calcularCostReservaAmbHoresValides() {
        // Crea una Aula i comprova el cost d'una reserva de 2 hores.
        Aula aula = new Aula("Aula1", 20);

        // 2 hores * 12.0 (preu per hora) = 24.0
        double costActual = aula.calcularCostReserva(2);

        assertEquals(24.0, costActual, "El cost de la reserva de 2 hores hauria de ser 24.0");
    }

    @Test
    void crearAulaAmbCapacitatInvalidaLlancaExcepcio() {
        // Comprova amb assertThrows què passa si la capacitat és 0.
        IllegalArgumentException excepcio = assertThrows(
                IllegalArgumentException.class,
                () -> new Aula("Aula1", 0),
                "Hauria de llançar una IllegalArgumentException si la capacitat és 0"
        );

        // Opcional: comprovar que el missatge de l'excepció és l'esperat
        assertEquals("La capacitat ha de ser positiva", excepcio.getMessage());
    }
}