package ch.juve.pwm.persistence;

import java.util.List;

public interface IDataAccess {

    void speichern(String entitaet, String id, Object objekt);
    Object lesen(String entitaet, String id);
    List<Object> alleLesen(String entitaet);
    boolean loeschen(String entitaet, String id);
    void zuruecksetzen();
}
