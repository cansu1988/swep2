package de.hrw.swep.service.business;

/**
 * Interface für State-Pattern für Frage
 * 
 * @author andriesc
 *
 */
public interface FrageStatus {
    
    /**
     * Eine Antwort fuer eine gegebene Frage abschicken. Es koennen nur
     * Antworten fuer freigeschaltete Fragen abgeschickt werden.
     * 
     * @param antwortNummer
     *            Die Nummer der gewaehlten Antwort. Eine Antwort ist eindeutig
     *            durch die Position in der Antwortliste einer Frage
     *            gekennzeichnet. Die Nummerierung beginnt bei 0: die möglichen
     *            Antwortnummern liegen also im Bereich zwischen 0 und (Anzahl
     *            der Antwortoptionen - 1)
     */
    void abstimmen(int antwortNummer);
    
    /**
     * Öffnet eine Frage für Abstimmungen
     * 
     */
    void oeffnen();
    
    /**
     * Schließt eine Frage für Abstimmungen
     * 
     */
    void schliessen();
    
    /**
     * 
     * @return Der aktuelle Status der Frage
     */
    String getStatus();
}
