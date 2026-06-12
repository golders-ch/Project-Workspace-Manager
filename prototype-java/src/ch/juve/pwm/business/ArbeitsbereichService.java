package ch.juve.pwm.business;

import java.util.List;

public class ArbeitsbereichService implements IArbeitsbereichService {

    private final IProjektarbeitsbereichRepository repository;
    private final ProjektarbeitsbereichFactory factory;

    public ArbeitsbereichService(IProjektarbeitsbereichRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("repository darf nicht null sein");
        }
        this.repository = repository;
        this.factory = ProjektarbeitsbereichFactory.getInstance();
    }

    @Override
    public Projektarbeitsbereich arbeitsbereichErstellen(String id, String name) {
        Projektarbeitsbereich arbeitsbereich = factory.erstelle(id, name);
        repository.speichern(arbeitsbereich);
        return arbeitsbereich;
    }

    @Override
    public Projektarbeitsbereich arbeitsbereichFinden(String id) {
        return repository.finden(id);
    }

    @Override
    public List<Projektarbeitsbereich> alleArbeitsbereiche() {
        return repository.alleLesen();
    }
}
