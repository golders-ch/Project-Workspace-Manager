/*
 * Datei:       IArbeitsbereichService.java
 * Projekt:     Project-Workspace-Manager (Prototyp, Iteration 1)
 * Schicht:     Business
 * Beschreibung: Anwendungsfall-Schnittstelle fuer FA01 (Projektarbeitsbereich erstellen).
 *              Einziger Eintrittspunkt der Presentation-Schicht fuer die Arbeitsbereich-
 *              Use-Cases der 1. Iteration.
 */
package ch.juve.pwm.business;

import java.util.List;

public interface IArbeitsbereichService {

    /**
     * Erstellt einen neuen Projektarbeitsbereich und persistiert ihn.
     *
     * @param id   gewuenschter Bezeichner des Arbeitsbereichs
     * @param name Name gemaess Namenskonvention
     * @return der frisch erstellte und persistierte Arbeitsbereich
     */
    Projektarbeitsbereich arbeitsbereichErstellen(String id, String name);

    /**
     * Sucht einen Projektarbeitsbereich anhand seiner ID.
     *
     * @param id eindeutiger Bezeichner
     * @return das gefundene Objekt oder {@code null}, falls keines existiert
     */
    Projektarbeitsbereich arbeitsbereichFinden(String id);

    /**
     * Liefert alle bisher erstellten Projektarbeitsbereiche.
     *
     * @return Liste aller Arbeitsbereiche, nie {@code null}
     */
    List<Projektarbeitsbereich> alleArbeitsbereiche();
}
