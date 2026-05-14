package cat.inspla.ra3.reserves;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

public class ServeiReserves {
    private final List<Reservable> recursos = new ArrayList<>();

    public void afegirRecurs(Reservable recurs) {
        if (recurs == null) {
            throw new IllegalArgumentException("El recurs no pot ser nul");
        }
        recursos.add(recurs);
    }

    public List<Reservable> getRecursos() {
        return Collections.unmodifiableList(recursos);
    }

    public double calcularCostTotal(int hores) {
        if (hores <= 0) {
            throw new IllegalArgumentException("Les hores han de ser positives");
        }
        double total = 0;
        for (Reservable recurs : recursos) {
            total += recurs.calcularCostReserva(hores);
        }
        return total;
    }

    public long comptarDisponibles() {
        return recursos.stream().filter(Reservable::estaDisponible).count();
    }

    public Reservable buscarPerNom(String nom) {
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("El nom de cerca és obligatori");
        }
        return recursos.stream()
                .filter(r -> r.getNom().equalsIgnoreCase(nom))
                .findFirst()
                .orElse(null);
    }

    /**
     * TODO RA4: aquest mètode funciona, però està fet expressament de manera poc eficient.
     * Cal optimitzar-lo utilitzant eines adequades del llenguatge Java.
     */
    public List<Reservable> obtenirRecursosOrdenatsPerNom() {
        List<Reservable> copia = new ArrayList<>(recursos);

        // Utilitza el mètode sort de la llista amb un comparador per nom
        copia.sort(Comparator.comparing(Reservable::getNom, String.CASE_INSENSITIVE_ORDER));

        return copia;
    }

    /**
     * TODO RA4: aquest mètode concatena Strings dins d'un bucle.
     * Cal optimitzar-lo sense canviar el resultat retornat.
     */
    public String generarInformeRecursos() {
        StringBuilder informe = new StringBuilder();

        for (Reservable recurs : recursos) {
            informe.append(recurs.getNom())
                    .append(" - ")
                    .append(recurs.getTipus())
                    .append(" - ")
                    .append(recurs.estaDisponible() ? "Disponible" : "Reservat")
                    .append(System.lineSeparator());
        }

        return informe.toString();
    }
}
