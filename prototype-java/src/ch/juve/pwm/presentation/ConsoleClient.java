package ch.juve.pwm.presentation;

import java.util.List;

import ch.juve.pwm.business.IArbeitsbereichService;
import ch.juve.pwm.business.Projektarbeitsbereich;

public class ConsoleClient {

    private final IArbeitsbereichService service;

    public ConsoleClient(IArbeitsbereichService service) {
        if (service == null) {
            throw new IllegalArgumentException("service darf nicht null sein");
        }
        this.service = service;
    }

    public void arbeitsbereichErstellenUndAnzeigen(String id, String name) {
        Projektarbeitsbereich neu = service.arbeitsbereichErstellen(id, name);
        System.out.println("[ERSTELLT] " + neu);
    }

    public void alleArbeitsbereicheAnzeigen() {
        List<Projektarbeitsbereich> alle = service.alleArbeitsbereiche();
        System.out.println("---- Liste aller Projektarbeitsbereiche (" + alle.size() + ") ----");
        for (Projektarbeitsbereich ab : alle) {
            System.out.println("  - " + ab);
        }
        System.out.println("------------------------------------------------------------");
    }
}
