package ch.juve.pwm.presentation;

import ch.juve.pwm.business.ArbeitsbereichService;
import ch.juve.pwm.business.IArbeitsbereichService;
import ch.juve.pwm.business.IProjektarbeitsbereichRepository;
import ch.juve.pwm.persistence.IDataAccess;
import ch.juve.pwm.persistence.MockDataAccess;
import ch.juve.pwm.persistence.ProjektarbeitsbereichRepository;

public final class Application {

    private Application() {
        // Utility-Klasse: keine Instanziierung.
    }

    public static void main(String[] args) {
        // Schichten verdrahten (Dependency Injection per Konstruktor).
        IDataAccess dataAccess = MockDataAccess.getInstance();
        dataAccess.zuruecksetzen(); // Sauberer Startzustand fuer den Demo-Lauf.

        IProjektarbeitsbereichRepository repository = new ProjektarbeitsbereichRepository(dataAccess);
        IArbeitsbereichService service = new ArbeitsbereichService(repository);
        ConsoleClient client = new ConsoleClient(service);

        System.out.println("====================================================");
        System.out.println(" Project-Workspace-Manager - Prototyp Iteration 1   ");
        System.out.println(" Endbenutzertest FA01: Projektarbeitsbereich erstellen");
        System.out.println("====================================================");

        // Mehrere Projektarbeitsbereiche mit "harten" Testdaten erstellen.
        client.arbeitsbereichErstellenUndAnzeigen("PRJ-2026-001", "PRJ-2026-JUVE-Webshop-Relaunch");
        client.arbeitsbereichErstellenUndAnzeigen("PRJ-2026-002", "PRJ-2026-ACME-Datenmigration");
        client.arbeitsbereichErstellenUndAnzeigen("PRJ-2026-003", "PRJ-2026-INT-Intranet-Modernisierung");
        client.arbeitsbereichErstellenUndAnzeigen("PRJ-2026-004", "PRJ-2026-JUVE-Mobile-App");

        System.out.println();
        client.alleArbeitsbereicheAnzeigen();

        System.out.println();
        System.out.println("Endbenutzertest abgeschlossen.");
    }
}
