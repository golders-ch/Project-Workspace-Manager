/*
 * Datei:       Projektarbeitsbereich.java
 * Projekt:     Project-Workspace-Manager (Prototyp, Iteration 1)
 * Schicht:     Business
 * Beschreibung: Fachliches Domain-Objekt fuer einen SharePoint-Projektarbeitsbereich.
 *              In der 1. Iteration werden gemaess Vorgabe ausschliesslich die Attribute
 *              'id' und 'name' persistiert; weitere Attribute (Beschreibung, URL, Status,
 *              Datumsfelder, Metadaten) folgen in spaeteren Iterationen.
 */
package ch.juve.pwm.business;

/**
 * Repraesentiert einen Projektarbeitsbereich (Domain Object zu FA01).
 *
 * <p>Wird ausschliesslich ueber die {@link ProjektarbeitsbereichFactory} erzeugt
 * und ueber das {@link IProjektarbeitsbereichRepository} persistiert.</p>
 *
 * <p>Die Klasse ist bewusst als einfacher POJO/Value-Carrier ausgelegt.</p>
 */
public class Projektarbeitsbereich {

    private String id;
    private String name;

    /**
     * Erzeugt einen neuen Projektarbeitsbereich.
     *
     * @param id   eindeutiger Bezeichner des Arbeitsbereichs
     * @param name Name gemaess Namenskonvention (z.B. {@code PRJ-2026-JUVE-Webshop})
     */
    public Projektarbeitsbereich(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Getter und Setter fuer die in der 1. Iteration persistierten Attribute
     * {@code id} und {@code name}.
     *
     * @return Attributwert
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Liefert eine fuer den Endbenutzertest geeignete Konsolen-Darstellung.
     *
     * @return Klartext-Repraesentation mit Klassenname, id und name
     */
    @Override
    public String toString() {
        return "Projektarbeitsbereich[id=" + id + ", name=" + name + "]";
    }
}
