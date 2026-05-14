package cat.inspla.ra3.reserves;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LaboratoriTest {

    @Test
    void calcularCostReservaAmbSupervisioAfegeixCostExtra() {
        Laboratori lab = new Laboratori("Quimica", 20, true);
        // Cost base per 2 hores: 2 * 20 = 40. Extra per supervisió: + 15 = 55.0
        assertEquals(55.0, lab.calcularCostReserva(2), "S'hauria de sumar el recàrrec de 15.0 per la supervisió");
    }

    @Test
    void calcularCostReservaSenseSupervisioEsCalculaNormal() {
        Laboratori lab = new Laboratori("Informatica", 30, false);
        // Cost base per 2 hores: 2 * 20 = 40. Cap extra.
        assertEquals(40.0, lab.calcularCostReserva(2), "No s'hauria de sumar l'extra de 15.0");
    }
}