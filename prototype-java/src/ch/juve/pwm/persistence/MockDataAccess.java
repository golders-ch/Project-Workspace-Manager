/*
 * Datei:       MockDataAccess.java
 * Projekt:     Project-Workspace-Manager (Prototyp, Iteration 1)
 * Schicht:     Persistence
 * Beschreibung: In-Memory-Realisierung von IDataAccess fuer den Prototyp.
 *              Ersetzt die spaetere SharePoint-Anbindung durch eine Map-basierte
 *              Mock-Datenbank im Hauptspeicher.
 */
package ch.juve.pwm.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Singleton-Implementierung von {@link IDataAccess} mit Speicherung im Hauptspeicher.
 *
 * <p>Die interne Struktur ist eine geschachtelte Map:
 * {@code Map<Entitaet, Map<Id, Objekt>>}.</p>
 *
 * <p>Diese Klasse ersetzt im Prototyp die im Designmodell vorgesehene
 * Klasse {@code SharePointDataAccess}; die Schnittstelle bleibt identisch.</p>
 */
public final class MockDataAccess implements IDataAccess {

    private static final class Holder {
        static final MockDataAccess INSTANCE = new MockDataAccess();
    }

    private final Map<String, Map<String, Object>> daten = new HashMap<>();

    private MockDataAccess() {
        // Konstruktor ist private, um externe Instanziierung zu verhindern.
    }

    /**
     * Liefert die zentrale Instanz.
     *
     * @return die einzige Instanz
     */
    public static MockDataAccess getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public synchronized void speichern(String entitaet, String id, Object objekt) {
        if (entitaet == null || id == null || objekt == null) {
            throw new IllegalArgumentException("entitaet, id und objekt duerfen nicht null sein");
        }
        daten.computeIfAbsent(entitaet, k -> new HashMap<>()).put(id, objekt);
    }

    @Override
    public synchronized <T> T lesen(String entitaet, String id, Class<T> typ) {
        Map<String, Object> bereich = daten.get(entitaet);
        if (bereich == null) {
            return null;
        }
        Object treffer = bereich.get(id);
        return treffer == null ? null : typ.cast(treffer);
    }

    @Override
    public synchronized <T> List<T> alleLesen(String entitaet, Class<T> typ) {
        Map<String, Object> bereich = daten.get(entitaet);
        if (bereich == null) {
            return new ArrayList<>();
        }
        List<T> ergebnis = new ArrayList<>(bereich.size());
        for (Object o : bereich.values()) {
            ergebnis.add(typ.cast(o));
        }
        return ergebnis;
    }

    @Override
    public synchronized boolean loeschen(String entitaet, String id) {
        Map<String, Object> bereich = daten.get(entitaet);
        if (bereich == null) {
            return false;
        }
        return bereich.remove(id) != null;
    }

    @Override
    public synchronized void zuruecksetzen() {
        daten.clear();
    }
}
