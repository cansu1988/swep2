package de.hrw.swep.service.business;

import java.util.List;

/**
 * Repräsentiert eine Frage mit allen moeglichen Antwortoptionen
 * @author Sandra Rebholz
 * @author andriesc
 * Student Serife Bolat, 22407
 *
 */
public class Frage {

    public static final String STATUS_OFFEN = "offen";
    public static final String STATUS_GESCHLOSSEN = "geschlossen";

    private int id;
    private String text;
    private List<String> antworten;
    private List<Integer> stimmen;
    private FrageStatus state;

    /**
     * Konstruktor
     * @param id ba
     * @param text 
     * @param status der Status (offen oder geschlossen)
     * @param antworten die Liste aller Antwortoptionen
     * @param stimmen die Liste der Stimmen pro Antwort aus der Abstimmung
     */
    public Frage(int id, String text, String status, List<String> antworten, List<Integer> stimmen) {
        super();
        this.id = id;
        this.text = text;
        this.antworten = antworten;
        this.stimmen = stimmen;
        

        if (status.equals(STATUS_OFFEN)) {
            setCurrentState(new FrageOffen(this));
        } else if (status.equals(STATUS_GESCHLOSSEN)) {
            setCurrentState(new FrageGeschlossen(this));
        }

    }

    /**
     * Liefert die Frage ID zurueck
     * @return die Frage ID
     */
    public int getId() {
        return id;
    }

    /**
     * Setzt die Frage ID
     * @param id die Frage ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Liefert den Text der Fragestellung zurueck
     * @return die Fragestellung
     */
    public String getText() {
        return text;
    }

    /**
     * Setzt die Fragestellung
     * @param text die Fragestellung
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Liefert den Status der Frage zurueck
     * @return der Status (offen/geschlossen)
     */
    public String getStatus() {
        return state.getStatus();
    }
    
    /**
     * Öffnet eine Frage für Abstimmungen
     * 
     */
    public void oeffnen() {
        state.oeffnen();
    }

    /**
     * Schließt eine Frage für Abstimmungen
     * 
     */
    public void schliessen() {
        state.schliessen();
    }

    /**
     * Liefert die Liste der moeglichen Antwortoptionen zurueck
     * @return die Antwortoptionen
     */
    public List<String> getAntworten() {
        return antworten;
    }

    /**
     * Setzt die Liste der moeglichen Antwortoptionen
     * @param antworten die Liste mit Antwortoptionen
     */
    public void setAntworten(List<String> antworten) {
        this.antworten = antworten;
    }

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
    public void abstimmen(int antwortNummer) {
        state.abstimmen(antwortNummer);
    }

    /**
     * 
     * @return Alle Abstimmungen für die Antworten
     */
    public List<Integer> getStimmen() {
        return stimmen;
    }
    
    /**
     * 
     * @param stimmen Abstimmungen für die Antworten auf die Frage setzen
     */
    public void setStimmen(List<Integer> stimmen) {
        this.stimmen = stimmen;
    }
    
    /**
     * State setzen (State-Pattern)
     * 
     * @param newState der neue State der Frage
     */
    public void setCurrentState(FrageStatus newState) {
        this.state = newState;
    }
}
