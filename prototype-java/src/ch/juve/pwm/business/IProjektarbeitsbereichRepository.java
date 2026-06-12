package ch.juve.pwm.business;

import java.util.List;

public interface IProjektarbeitsbereichRepository {
    void speichern(Projektarbeitsbereich arbeitsbereich);
    Projektarbeitsbereich finden(String id);
    List<Projektarbeitsbereich> alleLesen();
}
