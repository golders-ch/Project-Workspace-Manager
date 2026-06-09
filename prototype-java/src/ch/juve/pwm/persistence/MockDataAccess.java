/*
 * Datei:       MockDataAccess.java
 * Projekt:     Project-Workspace-Manager (Prototyp, Iteration 1)
 * Schicht:     Persistence
 * Beschreibung: Umsetzung von IDataAccess fuer den Prototyp. Ersetzt die spaetere
 *              SharePoint-Anbindung durch eine Mock-Datenbank im Hauptspeicher.
 */
package ch.juve.pwm.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Umsetzung von {@link IDataAccess}, die alle Daten im Hauptspeicher haelt
 * (Pattern: Singleton).
 *
 * <p>Die Daten liegen in einer Map pro Entitaet:
 * {@code Map<Entitaet, Map<Id, Objekt>>}.</p>
 *
 * <p>Diese Klasse ersetzt im Prototyp die im Designmodell vorgesehene Klasse
 * {@code SharePointDataAccess}; die Schnittstelle bleibt gleich.</p>
 */
public class MockDataAccess implements IDataAccess {

    // Die einzige Instanz (Singleton). Wird beim ersten Aufruf erzeugt.
    private static MockDataAccess instance;

    // Aeussere Map: Entitaet -> (innere Map: Id -> Objekt)
    private final Map<String, Map<String, Object>> daten = new HashMap<>();

    // Konstruktor ist privat, damit von aussen keine Instanz erzeugt werden kann.
    private MockDataAccess() {
    }

    /**
     * Liefert die einzige Instanz und erzeugt sie beim ersten Aufruf.
     *
     * @return die Instanz
     */
    public static MockDataAccess getInstance() {
        if (instance == null) {
            instance = new MockDataAccess();
        }
        return instance;
    }

    @Override
    public void speichern(String entitaet, String id, Object objekt) {
        if (entitaet == null || id == null || objekt == null) {
            throw new IllegalArgumentException("entitaet, id und objekt duerfen nicht null sein");
        }
        // Innere Map fuer die Entitaet holen oder neu anlegen.
        if (!daten.containsKey(entitaet)) {
            daten.put(entitaet, new HashMap<>());
        }
        daten.get(entitaet).put(id, objekt);
    }

    @Override
    public Object lesen(String entitaet, String id) {
        Map<String, Object> bereich = daten.get(entitaet);
        if (bereich == null) {
            return null;
        }
        return bereich.get(id);
    }

    @Override
    public List<Object> alleLesen(String entitaet) {
        List<Object> ergebnis = new ArrayList<>();
        Map<String, Object> bereich = daten.get(entitaet);
        if (bereich != null) {
            ergebnis.addAll(bereich.values());
        }
        return ergebnis;
    }

    @Override
    public boolean loeschen(String entitaet, String id) {
        Map<String, Object> bereich = daten.get(entitaet);
        if (bereich == null) {
            return false;
        }
        return bereich.remove(id) != null;
    }

    @Override
    public void zuruecksetzen() {
        daten.clear();
    }
}
