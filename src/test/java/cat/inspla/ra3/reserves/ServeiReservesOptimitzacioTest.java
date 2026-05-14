package cat.inspla.ra3.reserves;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServeiReservesOptimitzacioTest {

    @Test
    void recursosOrdenatsPerNomRetornaCopiaOrdenada() {
        // Preparem l'entorn
        ServeiReserves servei = new ServeiReserves();
        Aula aulaZ = new Aula("Zeta", 20);
        Aula aulaA = new Aula("Alfa", 20);
        Aula aulaM = new Aula("Mates", 30);

        // Afegim recursos en ordre desordenat
        servei.afegirRecurs(aulaZ);
        servei.afegirRecurs(aulaA);
        servei.afegirRecurs(aulaM);

        // Executem el mètode a testejar
        List<Reservable> ordenats = servei.obtenirRecursosOrdenatsPerNom();

        // Comprovem que la llista retornada està ordenada alfabèticament
        assertEquals(3, ordenats.size(), "Hauria de contenir 3 elements");
        assertEquals("Alfa", ordenats.get(0).getNom(), "El primer element hauria de ser Alfa");
        assertEquals("Mates", ordenats.get(1).getNom(), "El segon element hauria de ser Mates");
        assertEquals("Zeta", ordenats.get(2).getNom(), "El tercer element hauria de ser Zeta");

        // Comprovem que la llista original no s'ha modificat
        List<Reservable> originals = servei.getRecursos();
        assertEquals("Zeta", originals.get(0).getNom(), "L'ordre original no s'hauria d'haver alterat (Zeta primer)");
        assertEquals("Alfa", originals.get(1).getNom(), "L'ordre original no s'hauria d'haver alterat (Alfa segon)");
    }

    @Test
    void generarInformeInclouNomTipusIEstat() {
        // Preparem l'entorn
        ServeiReserves servei = new ServeiReserves();
        Aula aulaDisponible = new Aula("Aula1", 20);
        Aula aulaReservada = new Aula("Aula2", 30);

        // Reservem la segona aula
        aulaReservada.reservar();

        servei.afegirRecurs(aulaDisponible);
        servei.afegirRecurs(aulaReservada);

        // Executem el mètode
        String informe = servei.generarInformeRecursos();

        // Comprovem que no és nul i conté la informació d'ambdues aules correctament
        assertNotNull(informe, "L'informe no ha de ser nul");

        // Validem el format: Nom - Tipus - Estat
        String liniaEsperadaAula1 = "Aula1 - " + aulaDisponible.getTipus() + " - Disponible";
        String liniaEsperadaAula2 = "Aula2 - " + aulaReservada.getTipus() + " - Reservat";

        assertTrue(informe.contains(liniaEsperadaAula1), "L'informe hauria de contenir les dades de l'aula disponible");
        assertTrue(informe.contains(liniaEsperadaAula2), "L'informe hauria de contenir les dades de l'aula reservada");

        // Opcional: comprovar que conté els salts de línia adequats (System.lineSeparator())
        assertTrue(informe.contains(System.lineSeparator()), "L'informe hauria d'utilitzar salts de línia");
    }
}