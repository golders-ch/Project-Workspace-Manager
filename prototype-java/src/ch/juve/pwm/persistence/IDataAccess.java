/*
 * Datei:       IDataAccess.java
 * Projekt:     Project-Workspace-Manager (Prototyp, Iteration 1)
 * Schicht:     Persistence
 * Beschreibung: Allgemeine CRUD-Schnittstelle gegen die Speichertechnologie.
 *              Wird nur von den konkreten Repositories aufgerufen.
 */
package ch.juve.pwm.persistence;

import java.util.List;

/**
 * CRUD-Schnittstelle gegen die Speichertechnologie.
 *
 * <p>Im fertigen System wird diese Schnittstelle gegen SharePoint Online
 * umgesetzt. Im Prototyp uebernimmt {@link MockDataAccess} die Rolle einer
 * Mock-Datenbank im Hauptspeicher.</p>
 *
 * <p>Ein Eintrag wird ueber einen Entitaeten-Namen
 * (z.B. {@code "projektarbeitsbereich"}) und seine ID angesprochen.
 * Das entspricht dem Listen-Konzept von SharePoint.</p>
 */
public interface IDataAccess {

    /**
     * Speichert ein Objekt unter der angegebenen Entitaet und ID.
     * Ein vorhandener Eintrag mit derselben ID wird ueberschrieben.
     *
     * @param entitaet Name der Entitaet (z.B. {@code "projektarbeitsbereich"})
     * @param id       eindeutiger Bezeichner innerhalb der Entitaet
     * @param objekt   zu speicherndes Objekt, nicht {@code null}
     */
    void speichern(String entitaet, String id, Object objekt);

    /**
     * Liest ein Objekt zu einer ID.
     *
     * @param entitaet Name der Entitaet
     * @param id       eindeutiger Bezeichner
     * @return das gefundene Objekt oder {@code null}, falls nicht vorhanden
     */
    Object lesen(String entitaet, String id);

    /**
     * Liest alle Objekte einer Entitaet.
     *
     * @param entitaet Name der Entitaet
     * @return Liste aller Objekte (kann leer sein), nie {@code null}
     */
    List<Object> alleLesen(String entitaet);

    /**
     * Loescht den Eintrag zu einer ID.
     *
     * @param entitaet Name der Entitaet
     * @param id       eindeutiger Bezeichner
     * @return {@code true} falls ein Eintrag entfernt wurde, sonst {@code false}
     */
    boolean loeschen(String entitaet, String id);

    /**
     * Setzt alle Daten zurueck (nur fuer Tests/Prototyp gedacht).
     */
    void zuruecksetzen();
}
