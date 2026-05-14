package cat.inspla.ra3.reserves;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ServeiReservesTest {

    @Test
    void afegirRecursIncrementaLlista() {
        ServeiReserves servei = new ServeiReserves();
        assertEquals(0, servei.getRecursos().size(), "La llista hauria de començar buida");

        servei.afegirRecurs(new Aula("A1", 20));
        assertEquals(1, servei.getRecursos().size(), "S'ha d'haver incrementat la llista a 1");
    }

    @Test
    void afegirRecursNulLlancaExcepcio() {
        ServeiReserves servei = new ServeiReserves();
        assertThrows(
                IllegalArgumentException.class,
                () -> servei.afegirRecurs(null),
                "No s'hauria de permetre afegir un recurs null"
        );
    }

    @Test
    void comptarDisponiblesComptaCorrectament() {
        ServeiReserves servei = new ServeiReserves();
        Aula aula1 = new Aula("A1", 20);
        Aula aula2 = new Aula("A2", 30);

        aula2.reservar(); // En reservem una

        servei.afegirRecurs(aula1);
        servei.afegirRecurs(aula2);

        assertEquals(1, servei.comptarDisponibles(), "Només hi hauria d'haver 1 recurs disponible");
    }

    @Test
    void buscarPerNomRetornaRecursIgnorantMajuscules() {
        ServeiReserves servei = new ServeiReserves();
        Aula aula = new Aula("Informatica", 25);
        servei.afegirRecurs(aula);

        Reservable trobat = servei.buscarPerNom("inforMatiCa"); // Cas límit: ignore case
        assertEquals(aula, trobat, "Hauria de trobar l'aula encara que canviïn les majúscules");
    }

    @Test
    void buscarPerNomBuitLlancaExcepcio() {
        ServeiReserves servei = new ServeiReserves();
        assertThrows(
                IllegalArgumentException.class,
                () -> servei.buscarPerNom("   "),
                "Cercar per un nom buit o espais hauria de llançar excepció"
        );
    }

    @Test
    void calcularCostTotalSumaTotsElsRecursos() {
        ServeiReserves servei = new ServeiReserves();
        servei.afegirRecurs(new Aula("A1", 20)); // Cost 2h = 24.0
        servei.afegirRecurs(new Laboratori("L1", 20, false)); // Cost 2h = 40.0

        assertEquals(64.0, servei.calcularCostTotal(2), "El cost total hauria de ser la suma de tots els recursos (24 + 40)");
    }
}