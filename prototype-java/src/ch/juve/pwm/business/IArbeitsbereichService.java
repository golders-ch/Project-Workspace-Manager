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
    Projektarbeitsbereich arbeitsbereichErstellen(String id, String name);
    Projektarbeitsbereich arbeitsbereichFinden(String id);
    List<Projektarbeitsbereich> alleArbeitsbereiche();
}
