package ch.juve.pwm.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockDataAccess implements IDataAccess {
    private static MockDataAccess instance;
    private final Map<String, Map<String, Object>> daten = new HashMap<>();
    private MockDataAccess() {
    }

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
