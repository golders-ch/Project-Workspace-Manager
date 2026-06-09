/*
 * Datei:       IProjektarbeitsbereichRepository.java
 * Projekt:     Project-Workspace-Manager (Prototyp, Iteration 1)
 * Schicht:     Business (Schnittstelle, Realisierung in Persistence)
 * Beschreibung: Fachliche Repository-Schnittstelle fuer den Projektarbeitsbereich.
 *              Wird in der Business-Schicht definiert; die konkrete Implementierung
 *              liegt in der Persistence-Schicht.
 */
package ch.juve.pwm.business;

import java.util.List;

/**
 * Repository-Schnittstelle fuer die Persistenz von {@link Projektarbeitsbereich}-Objekten.
 *
 * <p>Verbirgt vor der Business-Schicht, dass die Speicherung im produktiven System
 * in SharePoint Online erfolgt. Im Prototyp wird die Schnittstelle gegen eine
 * In-Memory-Mock-Datenbank realisiert.</p>
 */
public interface IProjektarbeitsbereichRepository {

    /**
     * Speichert einen Projektarbeitsbereich.
     *
     * @param arbeitsbereich der zu speichernde Arbeitsbereich, nicht {@code null}
     */
    void speichern(Projektarbeitsbereich arbeitsbereich);

    /**
     * Liest einen Projektarbeitsbereich anhand seiner ID.
     *
     * @param id eindeutiger Bezeichner
     * @return das gefundene Objekt oder {@code null}, falls keines existiert
     */
    Projektarbeitsbereich finden(String id);

    /**
     * Liefert alle gespeicherten Projektarbeitsbereiche.
     *
     * @return Liste aller Arbeitsbereiche, nie {@code null}
     */
    List<Projektarbeitsbereich> alleLesen();
}
