/*
 * Datei:       ProjektarbeitsbereichRepository.java
 * Projekt:     Project-Workspace-Manager (Prototyp, Iteration 1)
 * Schicht:     Persistence
 * Beschreibung: Konkrete Implementierung von IProjektarbeitsbereichRepository.
 *              Delegiert technische Lese-/Schreibzugriffe an IDataAccess und
 *              kapselt die Mapping-Logik auf das SharePoint-Listen-Schema
 *              (im Prototyp: Mock-DB).
 */
package ch.juve.pwm.persistence;

import java.util.ArrayList;
import java.util.List;

import ch.juve.pwm.business.IProjektarbeitsbereichRepository;
import ch.juve.pwm.business.Projektarbeitsbereich;

/**
 * Persistence-Implementierung des Projektarbeitsbereich-Repositories.
 *
 * <p>Verwendet den konstanten Entitaeten-Schluessel {@link #ENTITAET}, der im
 * produktiven System dem Namen der SharePoint-Liste entspricht.</p>
 */
public class ProjektarbeitsbereichRepository implements IProjektarbeitsbereichRepository {

    /** Entitaeten-Schluessel im {@link IDataAccess}. */
    public static final String ENTITAET = "projektarbeitsbereich";

    private final IDataAccess dataAccess;

    /**
     * Erzeugt ein Repository mit dem angegebenen DataAccess.
     *
     * @param dataAccess das zu verwendende DataAccess, nicht {@code null}
     */
    public ProjektarbeitsbereichRepository(IDataAccess dataAccess) {
        if (dataAccess == null) {
            throw new IllegalArgumentException("dataAccess darf nicht null sein");
        }
        this.dataAccess = dataAccess;
    }

    @Override
    public void speichern(Projektarbeitsbereich arbeitsbereich) {
        if (arbeitsbereich == null) {
            throw new IllegalArgumentException("arbeitsbereich darf nicht null sein");
        }
        dataAccess.speichern(ENTITAET, arbeitsbereich.getId(), arbeitsbereich);
    }

    @Override
    public Projektarbeitsbereich finden(String id) {
        // IDataAccess liefert Object zurueck, deshalb hier der Cast auf unseren Typ.
        Object treffer = dataAccess.lesen(ENTITAET, id);
        return (Projektarbeitsbereich) treffer;
    }

    @Override
    public List<Projektarbeitsbereich> alleLesen() {
        // Object-Liste in eine typisierte Liste umwandeln.
        List<Projektarbeitsbereich> ergebnis = new ArrayList<>();
        for (Object o : dataAccess.alleLesen(ENTITAET)) {
            ergebnis.add((Projektarbeitsbereich) o);
        }
        return ergebnis;
    }
}
