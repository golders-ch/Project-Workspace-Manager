/*
 * Datei:       ProjektarbeitsbereichFactory.java
 * Projekt:     Project-Workspace-Manager (Prototyp, Iteration 1)
 * Schicht:     Business
 * Beschreibung: Factory zum Erzeugen von Projektarbeitsbereich-Objekten.
 *              Sammelt die Erzeugungslogik an einer Stelle, damit sie nicht
 *              mehrfach im Service-Code steht.
 */
package ch.juve.pwm.business;

/**
 * Factory fuer {@link Projektarbeitsbereich} (Pattern: Singleton + Factory).
 *
 * <p>Es gibt nur eine Instanz, die ueber {@link #getInstance()} geholt wird.
 * In spaeteren Iterationen werden hier weitere Vorgabewerte gesetzt
 * (z.B. Initialstatus, Erstellungsdatum).</p>
 */
public class ProjektarbeitsbereichFactory {

    // Die einzige Instanz (Singleton). Wird beim ersten Aufruf erzeugt.
    private static ProjektarbeitsbereichFactory instance;

    // Konstruktor ist privat, damit von aussen keine Instanz erzeugt werden kann.
    private ProjektarbeitsbereichFactory() {
    }

    /**
     * Liefert die einzige Factory-Instanz und erzeugt sie beim ersten Aufruf.
     *
     * @return die Factory-Instanz
     */
    public static ProjektarbeitsbereichFactory getInstance() {
        if (instance == null) {
            instance = new ProjektarbeitsbereichFactory();
        }
        return instance;
    }

    /**
     * Erzeugt einen neuen Projektarbeitsbereich.
     *
     * @param id   eindeutiger Bezeichner
     * @param name Name gemaess Namenskonvention
     * @return neuer, noch nicht gespeicherter Arbeitsbereich
     * @throws IllegalArgumentException falls id oder name leer/null sind
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
