/*
 * Datei:       ConsoleClient.java
 * Projekt:     Project-Workspace-Manager (Prototyp, Iteration 1)
 * Schicht:     Presentation
 * Beschreibung: Konsolenbasierte Benutzerschnittstelle. Fordert Domain-Operationen
 *              ueber den Service an und gibt das Ergebnis auf der Konsole aus.
 *              Enthaelt keine fachliche Logik.
 */
package ch.juve.pwm.presentation;

import java.util.List;

import ch.juve.pwm.business.IArbeitsbereichService;
import ch.juve.pwm.business.Projektarbeitsbereich;

/**
 * Konsolen-Client der Presentation-Schicht.
 *
 * <p>Die Klasse ist bewusst schlank: sie ruft den
 * {@link IArbeitsbereichService} auf und formatiert das Ergebnis fuer die
 * Konsole. In spaeteren Iterationen kann diese Schicht durch eine grafische
 * Java-Oberflaeche ersetzt werden, ohne dass Business- oder Persistence-Schicht
 * angepasst werden muessten.</p>
 */
public class ConsoleClient {

    private final IArbeitsbereichService service;

    /**
     * Erzeugt einen ConsoleClient mit dem angegebenen Service.
     *
     * @param service der zu verwendende Arbeitsbereich-Service, nicht {@code null}
     */
    public ConsoleClient(IArbeitsbereichService service) {
        if (service == null) {
            throw new IllegalArgumentException("service darf nicht null sein");
        }
        this.service = service;
    }

    /**
     * Erstellt einen Arbeitsbereich und gibt eine Bestaetigung auf der Konsole aus.
     *
     * @param id   eindeutiger Bezeichner
     * @param name Name gemaess Namenskonvention
     */
    public void arbeitsbereichErstellenUndAnzeigen(String id, String name) {
        Projektarbeitsbereich neu = service.arbeitsbereichErstellen(id, name);
        System.out.println("[ERSTELLT] " + neu);
    }

    /**
     * Listet alle bisher erstellten Arbeitsbereiche auf der Konsole auf.
     */
    public void alleArbeitsbereicheAnzeigen() {
        List<Projektarbeitsbereich> alle = service.alleArbeitsbereiche();
        System.out.println("---- Liste aller Projektarbeitsbereiche (" + alle.size() + ") ----");
        for (Projektarbeitsbereich ab : alle) {
            System.out.println("  - " + ab);
        }
        System.out.println("------------------------------------------------------------");
    }
}
