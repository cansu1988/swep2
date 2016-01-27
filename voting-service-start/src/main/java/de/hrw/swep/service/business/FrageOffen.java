/**
 * 
 */
package de.hrw.swep.service.business;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andriesc
 *
 */
public class FrageOffen implements FrageStatus {

    private Frage frage;
    
    /**
     * 
     * @param frage Frage, zu der dies der neue Status ist
     */
    public FrageOffen(Frage frage) {
        this.frage = frage;
    }
    
    /* (non-Javadoc)
     * @see de.hrw.swep.service.business.FrageStatus#abstimmen(int)
     */
    @Override
    public void abstimmen(int antwortNummer) {
        // Frage ist offen, abstimmen ist erlaubt
        List<Integer> stimmen;
        int maxIndex = frage.getAntworten().size();
        
        // Nur Abstimmung erlauben wenn es die Antwort auch gibt
        if (antwortNummer >= 0 && antwortNummer < maxIndex) {
           
            // Bisherige Stimmen holen
            stimmen = frage.getStimmen();
            if (stimmen != null) {
                // Abstimmen und fertig
                stimmen.set(antwortNummer, stimmen.get(antwortNummer) + 1);
            }
            else {
                // Stimmen waren im Frage-Objekt noch nicht initialisiert
                stimmen = new ArrayList<Integer>(maxIndex);
                stimmen.set(antwortNummer, stimmen.get(antwortNummer) + 1);
                frage.setStimmen(stimmen);
            }
        } else {
            throw new IndexOutOfBoundsException("Antwort-ID (ID= "
                    + antwortNummer
                    + "existiert nicht. Die aktuelle Frage (ID=" + frage.getId()
                    + ") hat Antwort-IDs zwischen 0 und " + (maxIndex - 1));
        }
    }

    /* (non-Javadoc)
     * @see de.hrw.swep.service.business.FrageStatus#oeffnen()
     */
    @Override
    public void oeffnen() {
        // Nichts machen, Frage ist bereits offen
    }

    /* (non-Javadoc)
     * @see de.hrw.swep.service.business.FrageStatus#schliessen()
     */
    @Override
    public void schliessen() {
        frage.setCurrentState(new FrageGeschlossen(frage));
    }

    @Override
    public String getStatus() {
        return Frage.STATUS_OFFEN;
    }

}
