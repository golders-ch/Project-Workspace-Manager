/*
 * Datei:       ArbeitsbereichServiceTest.java
 * Projekt:     Project-Workspace-Manager (Prototyp, Iteration 1)
 * Schicht:     Business (Test)
 * Beschreibung: JUnit-5-Tests fuer ArbeitsbereichService und die
 *              ProjektarbeitsbereichFactory (Singleton + Erzeugung).
 */
package ch.juve.pwm.test.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ch.juve.pwm.business.ArbeitsbereichService;
import ch.juve.pwm.business.IArbeitsbereichService;
import ch.juve.pwm.business.IProjektarbeitsbereichRepository;
import ch.juve.pwm.business.Projektarbeitsbereich;
import ch.juve.pwm.business.ProjektarbeitsbereichFactory;

/**
 * Unit-Tests fuer die Business-Schicht.
 *
 * <p>Verwendet eine schlanke In-Test-Fake-Implementierung von
 * {@link IProjektarbeitsbereichRepository}, damit der Service unabhaengig
 * von der Persistence-Schicht getestet werden kann.</p>
 */
class ArbeitsbereichServiceTest {

    /**
     * Minimaler In-Memory-Fake des Repositories fuer Business-Tests.
     */
    private static final class FakeRepository implements IProjektarbeitsbereichRepository {
        private final Map<String, Projektarbeitsbereich> store = new HashMap<>();

        @Override
        public void speichern(Projektarbeitsbereich arbeitsbereich) {
            store.put(arbeitsbereich.getId(), arbeitsbereich);
        }

        @Override
        public Projektarbeitsbereich finden(String id) {
            return store.get(id);
        }

        @Override
        public List<Projektarbeitsbereich> alleLesen() {
            return new ArrayList<>(store.values());
        }
    }

    private FakeRepository repository;
    private IArbeitsbereichService service;

    @BeforeEach
    void setUp() {
        repository = new FakeRepository();
        service = new ArbeitsbereichService(repository);
    }

    @Test
    @DisplayName("Factory ist Singleton (gleiche Instanz)")
    void factoryIstSingleton() {
        assertSame(ProjektarbeitsbereichFactory.getInstance(),
                ProjektarbeitsbereichFactory.getInstance());
    }

    @Test
    @DisplayName("Factory erzeugt Arbeitsbereich mit gesetzten Attributen")
    void factoryErzeugtArbeitsbereich() {
        Projektarbeitsbereich ab = ProjektarbeitsbereichFactory.getInstance()
                .erstelle("PRJ-001", "PRJ-2026-JUVE-Webshop");

        assertNotNull(ab);
        assertEquals("PRJ-001", ab.getId());
        assertEquals("PRJ-2026-JUVE-Webshop", ab.getName());
    }

    @Test
    @DisplayName("Factory wirft Exception bei leerer ID")
    void factoryWirftExceptionBeiLeererId() {
        assertThrows(IllegalArgumentException.class,
                () -> ProjektarbeitsbereichFactory.getInstance().erstelle("", "Name"));
        assertThrows(IllegalArgumentException.class,
                () -> ProjektarbeitsbereichFactory.getInstance().erstelle(null, "Name"));
    }

    @Test
    @DisplayName("Factory wirft Exception bei leerem Namen")
    void factoryWirftExceptionBeiLeeremName() {
        assertThrows(IllegalArgumentException.class,
                () -> ProjektarbeitsbereichFactory.getInstance().erstelle("PRJ-001", ""));
        assertThrows(IllegalArgumentException.class,
                () -> ProjektarbeitsbereichFactory.getInstance().erstelle("PRJ-001", null));
    }

    @Test
    @DisplayName("arbeitsbereichErstellen persistiert den Arbeitsbereich")
    void arbeitsbereichErstellenPersistiert() {
        Projektarbeitsbereich ab = service.arbeitsbereichErstellen(
                "PRJ-001", "PRJ-2026-JUVE-Webshop");

        assertNotNull(ab);
        assertEquals("PRJ-001", ab.getId());
        assertEquals("PRJ-2026-JUVE-Webshop", ab.getName());
        assertNotNull(repository.finden("PRJ-001"));
    }

    @Test
    @DisplayName("arbeitsbereichFinden liefert gespeichertes Objekt")
    void arbeitsbereichFindenLiefertObjekt() {
        service.arbeitsbereichErstellen("PRJ-001", "Name1");
        Projektarbeitsbereich gefunden = service.arbeitsbereichFinden("PRJ-001");

        assertNotNull(gefunden);
        assertEquals("Name1", gefunden.getName());
    }

    @Test
    @DisplayName("arbeitsbereichFinden liefert null bei unbekannter ID")
    void arbeitsbereichFindenLiefertNullBeiUnbekannterId() {
        assertNull(service.arbeitsbereichFinden("UNBEKANNT"));
    }

    @Test
    @DisplayName("alleArbeitsbereiche liefert alle erstellten Objekte")
    void alleArbeitsbereicheLiefertAlle() {
        service.arbeitsbereichErstellen("PRJ-001", "Eins");
        service.arbeitsbereichErstellen("PRJ-002", "Zwei");
        service.arbeitsbereichErstellen("PRJ-003", "Drei");

        assertEquals(3, service.alleArbeitsbereiche().size());
    }

    @Test
    @DisplayName("Konstruktor mit null-Repository wirft IllegalArgumentException")
    void konstruktorMitNullRepositoryWirftException() {
        assertThrows(IllegalArgumentException.class, () -> new ArbeitsbereichService(null));
    }
}
