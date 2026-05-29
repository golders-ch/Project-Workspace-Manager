/*
 * Datei:       IDataAccess.java
 * Projekt:     Project-Workspace-Manager (Prototyp, Iteration 1)
 * Schicht:     Persistence
 * Beschreibung: Generische CRUD-Schnittstelle (Singleton-Vertrag) gegen die jeweils
 *              eingesetzte Speichertechnologie. Wird ausschliesslich von den konkreten
 *              Repositories aufgerufen.
 */
package ch.juve.pwm.persistence;

import java.util.List;

/**
 * Generisches CRUD-Gateway gegen die Speichertechnologie.
 *
 * <p>Im produktiven System wird die Schnittstelle gegen SharePoint Online via
 * Microsoft Graph realisiert. Im Prototyp uebernimmt {@link MockDataAccess} die
 * Rolle einer in-memory Mock-Datenbank.</p>
 *
 * <p>Die Entitaeten werden ueber einen {@code entitaet}-Schluessel
 * (z.B. {@code "projektarbeitsbereich"}) und ihre {@code id} adressiert.
 * Dies entspricht dem Listen-Konzept von SharePoint.</p>
 */
public interface IDataAccess {

    /**
     * Speichert ein Objekt unter der angegebenen Entitaet und ID.
     * Vorhandene Eintraege mit derselben ID werden ueberschrieben.
     *
     * @param entitaet logischer Name der Entitaet (z.B. {@code "projektarbeitsbereich"})
     * @param id       eindeutiger Bezeichner innerhalb der Entitaet
     * @param objekt   zu speicherndes Objekt, nicht {@code null}
     */
    void speichern(String entitaet, String id, Object objekt);

    /**
     * Liest ein Objekt zu einer ID einer bestimmten Entitaet.
     *
     * @param <T>      erwarteter Typ des zurueckgegebenen Objekts
     * @param entitaet logischer Name der Entitaet
     * @param id       eindeutiger Bezeichner
     * @param typ      Class-Token des erwarteten Typs (fuer typsicheren Cast)
     * @return das gefundene Objekt oder {@code null}, falls nicht vorhanden
     */
    <T> T lesen(String entitaet, String id, Class<T> typ);

    /**
     * Liest alle Objekte einer Entitaet.
     *
     * @param <T>      erwarteter Typ der zurueckgegebenen Objekte
     * @param entitaet logischer Name der Entitaet
     * @param typ      Class-Token des erwarteten Typs
     * @return Liste aller Objekte (kann leer sein), nie {@code null}
     */
    <T> List<T> alleLesen(String entitaet, Class<T> typ);

    /**
     * Loescht den Eintrag zu einer ID.
     *
     * @param entitaet logischer Name der Entitaet
     * @param id       eindeutiger Bezeichner
     * @return {@code true} falls ein Eintrag entfernt wurde, sonst {@code false}
     */
    boolean loeschen(String entitaet, String id);

    /**
     * Setzt alle Daten zurueck (nur fuer Tests/Prototyp gedacht).
     */
    void zuruecksetzen();
}
