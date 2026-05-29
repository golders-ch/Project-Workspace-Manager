/*
 * Datei:       ProjektarbeitsbereichRepositoryTest.java
 * Projekt:     Project-Workspace-Manager (Prototyp, Iteration 1)
 * Schicht:     Persistence (Test)
 * Beschreibung: JUnit-5-Tests fuer die Persistence-Schicht
 *              (MockDataAccess + ProjektarbeitsbereichRepository).
 */
package ch.juve.pwm.test.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ch.juve.pwm.business.Projektarbeitsbereich;
import ch.juve.pwm.persistence.IDataAccess;
import ch.juve.pwm.persistence.MockDataAccess;
import ch.juve.pwm.persistence.ProjektarbeitsbereichRepository;

/**
 * Unit-Tests fuer das {@link ProjektarbeitsbereichRepository} in Kombination mit
 * der {@link MockDataAccess}-Implementierung.
 */
class ProjektarbeitsbereichRepositoryTest {

    private IDataAccess dataAccess;
    private ProjektarbeitsbereichRepository repository;

    @BeforeEach
    void setUp() {
        dataAccess = MockDataAccess.getInstance();
        dataAccess.zuruecksetzen();
        repository = new ProjektarbeitsbereichRepository(dataAccess);
    }

    @Test
    @DisplayName("MockDataAccess ist Singleton (gleiche Instanz)")
    void mockDataAccessIstSingleton() {
        assertSame(MockDataAccess.getInstance(), MockDataAccess.getInstance());
    }

    @Test
    @DisplayName("speichern + finden liefert dasselbe Objekt")
    void speichernUndFinden() {
        Projektarbeitsbereich ab = new Projektarbeitsbereich("PRJ-001", "PRJ-2026-JUVE-Webshop");
        repository.speichern(ab);

        Projektarbeitsbereich gefunden = repository.finden("PRJ-001");

        assertNotNull(gefunden, "gespeichertes Objekt muss gefunden werden");
        assertEquals("PRJ-001", gefunden.getId());
        assertEquals("PRJ-2026-JUVE-Webshop", gefunden.getName());
    }

    @Test
    @DisplayName("finden mit unbekannter ID liefert null")
    void findenLiefertNullBeiUnbekannterId() {
        assertNull(repository.finden("UNBEKANNT"));
    }

    @Test
    @DisplayName("alleLesen liefert alle gespeicherten Objekte")
    void alleLesenLiefertAlleObjekte() {
        repository.speichern(new Projektarbeitsbereich("PRJ-001", "Eins"));
        repository.speichern(new Projektarbeitsbereich("PRJ-002", "Zwei"));
        repository.speichern(new Projektarbeitsbereich("PRJ-003", "Drei"));

        List<Projektarbeitsbereich> alle = repository.alleLesen();

        assertEquals(3, alle.size());
    }

    @Test
    @DisplayName("speichern mit gleicher ID ueberschreibt den vorhandenen Eintrag")
    void speichernUeberschreibtVorhandenes() {
        repository.speichern(new Projektarbeitsbereich("PRJ-001", "Alt"));
        repository.speichern(new Projektarbeitsbereich("PRJ-001", "Neu"));

        Projektarbeitsbereich gefunden = repository.finden("PRJ-001");
        assertEquals("Neu", gefunden.getName());
        assertEquals(1, repository.alleLesen().size());
    }

    @Test
    @DisplayName("alleLesen liefert leere Liste, wenn nichts gespeichert wurde")
    void alleLesenLiefertLeereListe() {
        assertTrue(repository.alleLesen().isEmpty());
    }

    @Test
    @DisplayName("speichern mit null wirft IllegalArgumentException")
    void speichernMitNullWirftException() {
        assertThrows(IllegalArgumentException.class, () -> repository.speichern(null));
    }

    @Test
    @DisplayName("Konstruktor mit null-DataAccess wirft IllegalArgumentException")
    void konstruktorMitNullDataAccessWirftException() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProjektarbeitsbereichRepository(null));
    }
}
