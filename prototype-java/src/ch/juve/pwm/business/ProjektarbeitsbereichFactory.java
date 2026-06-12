package ch.juve.pwm.business;

public class ProjektarbeitsbereichFactory {

    private static ProjektarbeitsbereichFactory instance;
    private ProjektarbeitsbereichFactory() {
    }


    public static ProjektarbeitsbereichFactory getInstance() {
        if (instance == null) {
            instance = new ProjektarbeitsbereichFactory();
        }
        return instance;
    }

    public Projektarbeitsbereich erstelle(String id, String name) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id darf nicht leer sein");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name darf nicht leer sein");
        }
        return new Projektarbeitsbereich(id, name);
    }
}
