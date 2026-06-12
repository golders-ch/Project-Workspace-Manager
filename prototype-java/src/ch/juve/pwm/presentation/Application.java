package ch.juve.pwm.presentation;

// Import der Klassen aus den anderen Layern
import ch.juve.pwm.business.ArbeitsbereichService;
import ch.juve.pwm.business.IArbeitsbereichService;
import ch.juve.pwm.business.IProjektarbeitsbereichRepository;
import ch.juve.pwm.persistence.IDataAccess;
import ch.juve.pwm.persistence.MockDataAccess;
import ch.juve.pwm.persistence.ProjektarbeitsbereichRepository;

public class Application {

    // Hier startet die Applikation
    public static void main(String[] args) {

        // MockDataAccess implementiert die Schnittstelle IDataAccess
        // Holt die Singleton-Instanz von MockDataAccess für den Datenzugriff
        IDataAccess dataAccess = MockDataAccess.getInstance();

        // Löscht die Daten aus dem MockDataAccess Singleton Objekt
        dataAccess.zuruecksetzen();

        // Erstellt das Repository für den Zugriff auf Projektarbeitsbereiche
        // ProjektarbeitsbereichRepository implementiert die Schnittstelle IProjektarbeitsbereichRepository
        // Instanziert Objekt ProjektarbeitsbereichRepository
        IProjektarbeitsbereichRepository repository = new ProjektarbeitsbereichRepository(dataAccess);

        // Erstellt den Service mit Geschäftslogik für Projektarbeitsbereiche
        // ArbeitsbereichService implementiert die Schnittstelle IArbeitsbereichService
        // Instanziert Objekt ArbeitsbereichService
        IArbeitsbereichService service = new ArbeitsbereichService(repository);
        ConsoleClient client = new ConsoleClient(service);

        // Mehrere Projektarbeitsbereiche erstellen
        // Methode arbeitsbereichErstellenUndAnzeigen()
        client.arbeitsbereichErstellenUndAnzeigen("PRJ-2026-001", "PRJ-2026-JUVE-Webshop-Relaunch");
        client.arbeitsbereichErstellenUndAnzeigen("PRJ-2026-002", "PRJ-2026-ACME-Datenmigration");
        client.arbeitsbereichErstellenUndAnzeigen("PRJ-2026-003", "PRJ-2026-INT-Intranet-Modernisierung");
        client.arbeitsbereichErstellenUndAnzeigen("PRJ-2026-004", "PRJ-2026-JUVE-Mobile-App");

        System.out.println();

        // Funktion um alle erstellen Arbeitsbereiche aufzurufen
        client.alleArbeitsbereicheAnzeigen();

        System.out.println();
        System.out.println("Endbenutzertest abgeschlossen.");
    }
}
