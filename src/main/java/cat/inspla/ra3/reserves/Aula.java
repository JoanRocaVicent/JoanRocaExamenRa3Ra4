package cat.inspla.ra3.reserves;

// TODO RA4: afegeix una capçalera Javadoc de classe que expliqui la responsabilitat de la classe Aula.

/**
 * Fitxer: Aula.java
 * Autor: Joan Roca
 * Data: 14/05/2026
 * Descripcio: Classe que representa una aula on guardem el seu nom capacitat i disponibilitat
 * Una aula te les capacitats de canviar el seu estat de disponiblitat. ser reservada calcular quant costara reservar-la
 * duran una determinada quantitat de temps i valida que les dades que li arrivin siguin valides
 */

public class Aula implements Reservable {
    private final String nom;
    private final int capacitat;
    private boolean disponible;

    // TODO RA4: documenta el constructor amb @param i @throws.

    /**
     * Constructor de la Classe Aula
     * @param nom nom de l'aula
     * @param capacitat capacitat de l'aula
     * @throws IllegalArgumentException Excepcio que salta quan el constructor reb un parametra no valid
     */
    public Aula(String nom, int capacitat) {
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("El nom de l'aula és obligatori");
        }
        if (capacitat <= 0) {
            throw new IllegalArgumentException("La capacitat ha de ser positiva");
        }
        this.nom = nom;
        this.capacitat = capacitat;
        this.disponible = true;
    }


    @Override
    public String getNom() { return nom; }

    @Override
    public TipusRecurs getTipus() { return TipusRecurs.AULA; }

    @Override
    public int getCapacitat() { return capacitat; }

    @Override
    public boolean estaDisponible() { return disponible; }

    // TODO RA4: documenta què passa si l'aula ja està reservada.

    /**
     * Funcio que reserva l'aula
     * @throws IllegalStateException llença l'excepcio quan s'intenta reserva una aula que no esta disponible
     */
    @Override
    public void reservar() {
        if (!disponible) {
            throw new IllegalStateException("El recurs ja està reservat");
        }
        disponible = false;
    }

    @Override
    public void alliberar() { disponible = true; }

    // TODO RA4: documenta el càlcul del cost, el paràmetre hores, el retorn i les excepcions.

    /**
     * Funcio que calcula el cost d'una reserva donades unes hores
     * @param hores hores que dura la reserva
     * @throws IllegalArgumentException si les hores passades per parametre son 0 o menys llença la excepcio
     * @return cost que tindra reserva aquella aula duran les hores dones per parametra
     */
    @Override
    public double calcularCostReserva(int hores) {
        validarHores(hores);
        return hores * 12.0;
    }

    /**
     * Funcio que valida les hores per confirmar que sigui un numero superior a 0
     * @param hores numero de hores a validar
     * @throws IllegalArgumentException si les hores son igual o inferior a 0 llença la excepcio
     */

    protected void validarHores(int hores) {
        if (hores <= 0) {
            throw new IllegalArgumentException("Les hores han de ser positives");
        }
    }
}
