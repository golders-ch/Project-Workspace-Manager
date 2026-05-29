/*
 * Datei:       ProjektarbeitsbereichFactory.java
 * Projekt:     Project-Workspace-Manager (Prototyp, Iteration 1)
 * Schicht:     Business
 * Beschreibung: Singleton-Factory zur Erzeugung von Projektarbeitsbereich-Objekten.
 *              Buendelt die Initialisierungs-Defaults an einer Stelle und vermeidet,
 *              dass diese Logik mehrfach im Service-Code auftaucht.
 */
package ch.juve.pwm.business;

/**
 * Singleton-Factory fuer {@link Projektarbeitsbereich}.
 *
 * <p>Pattern: <em>Singleton</em> (nur eine Instanz) kombiniert mit <em>Factory Method</em>
 * (kapselt die Konstruktion). In spaeteren Iterationen werden hier weitere Defaults
 * (Initialstatus, Erstellungsdatum, URL-Schema) gesetzt.</p>
 *
 * <p>Thread-Safety: Initialisierung erfolgt thread-sicher ueber den Class-Loader
 * (Initialization-on-demand Holder-Idiom).</p>
 */
public final class ProjektarbeitsbereichFactory {

    /**
     * Holder-Klasse fuer das lazy-initialisierte Singleton (Bill-Pugh-Idiom).
     */
    private static final class Holder {
        static final ProjektarbeitsbereichFactory INSTANCE = new ProjektarbeitsbereichFactory();
    }

    private ProjektarbeitsbereichFactory() {
        // Konstruktor ist private, um externe Instanziierung zu verhindern.
    }

    /**
     * Liefert die zentrale Factory-Instanz.
     *
     * @return die einzige Factory-Instanz
     */
    public static ProjektarbeitsbereichFactory getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Erzeugt einen neuen Projektarbeitsbereich mit den angegebenen Attributen.
     *
     * @param id   eindeutiger Bezeichner
     * @param name Name gemaess Namenskonvention
     * @return neuer, noch nicht persistierter Arbeitsbereich
     * @throws IllegalArgumentException falls {@code id} oder {@code name} leer/null sind
     */
    public Projektarbeitsbereich erstelle(String id, String name) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id darf nicht leer sein");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name darf nicht leer sein");
        }
        return new Projektarbeitsbereich(id, name);
    }
}
