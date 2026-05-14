package cat.inspla.ra3.reserves;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class EquipamentTest {

    // Prova parametritzada per comprovar la lògica de càlcul (hores * unitats * 3.5)
    @ParameterizedTest
    @CsvSource({
            "1, 2, 7.0",   // 1 hora * 2 unitats * 3.5 = 7.0
            "2, 10, 70.0", // 2 hores * 10 unitats * 3.5 = 70.0
            "5, 5, 87.5"   // 5 hores * 5 unitats * 3.5 = 87.5
    })
    void calcularCostReservaParametritzada(int hores, int unitats, double costEsperat) {
        Equipament equip = new Equipament("Portatils", unitats);
        assertEquals(costEsperat, equip.calcularCostReserva(hores), "El cost no coincideix amb l'esperat segons la fórmula");
    }

    @Test
    void crearEquipamentAmbNomBuitLlancaExcepcio() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Equipament("", 5),
                "Hauria de llançar excepció si el nom està buit"
        );
    }
}