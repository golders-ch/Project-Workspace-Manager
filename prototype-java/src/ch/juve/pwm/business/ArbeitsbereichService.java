package ch.juve.pwm.business;
//Test 6
import java.util.List;

/**
 * Standard-Implementierung von {@link IArbeitsbereichService}.
 *
 * <p>Die Klasse haelt den Normalablauf von FA01 zusammen: sie laesst den
 * Arbeitsbereich von der {@link ProjektarbeitsbereichFactory} erzeugen und
 * persistiert ihn anschliessend ueber das {@link IProjektarbeitsbereichRepository}.</p>
 *
 * <p>Das Repository wird per Konstruktor uebergeben, damit die Klasse
 * unabhaengig von einer konkreten Speichertechnologie (SharePoint, Mock-DB)
 * getestet werden kann.</p>
 */
public class ArbeitsbereichService implements IArbeitsbereichService {

    private final IProjektarbeitsbereichRepository repository;
    private final ProjektarbeitsbereichFactory factory;

    /**
     * Erzeugt einen Service mit dem angegebenen Repository.
     *
     * @param repository das zu verwendende Repository, nicht {@code null}
     */
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
