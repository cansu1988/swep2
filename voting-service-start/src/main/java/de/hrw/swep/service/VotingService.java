package de.hrw.swep.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.hrw.swep.persistence.DataStoreInterface;
import de.hrw.swep.persistence.dto.FrageDTO;
import de.hrw.swep.service.business.Frage;

/**
 * Implementierungsklasse des Voting-Service. Alle Daten werden ueber das
 * DataStoreInterface abgerufen und dort auch hinterlegt.
 * 
 * @author Sandra Rebholz
 *@author Serife Bolat,22407
 */
public class VotingService implements VotingServiceInterface {

    /**
     * Referenz auf den DataStore
     */
    private DataStoreInterface dataStore;

    /**
     * Konstruktor mit Dependency Injection
     * 
     * @param ds
     *            Referenz auf den DataStore
     */
    public VotingService(DataStoreInterface ds) {
        this.dataStore = ds;
    }
    
    /**
     * Frage wieder in der Datenbank speichern
     * 
     * @param frage Die zu speichernde Frage
     */
    private void updateDBFrage(Frage frage) {
        FrageDTO dto = new FrageDTO(frage.getId(), frage.getText(), frage.getStatus());
        dataStore.updateFrage(dto);
        for (int i = 0; i < frage.getAntworten().size(); i++) {
            dataStore.updateStimmen(frage.getId(), i, frage.getStimmen().get(i));
        }
    }

    /**
     * Liefert alle im System hinterlegten Fragen
     * 
     * @return das Set aller Fragen
     */
    public Set<Frage> getAlleFragen() {
        Set<Frage> result = new HashSet<Frage>();
        Set<FrageDTO> fragen = dataStore.getAlleFragen();
        for (FrageDTO dto : fragen) {
            int frageId = dto.getId();
            List<String> antworten = dataStore.getAntwortenById(frageId);
            List<Integer> votes = dataStore.getVotings(frageId);
            Frage frage = new Frage(dto.getId(), dto.getText(),
                    dto.getStatus(), antworten, votes);
            result.add(frage);
        }
        return result;
    }
    
    /**
     * Schaltet eine Frage frei
     * @param frageId der Frage
     * 
     */
    public void frageFreischalten(int frageId) {
        dataStore.updateStatusOfFrage(frageId, Frage.STATUS_OFFEN);
    }

    /**
     * Schliesst die gegebene Frage.
     * 
     * @param frageId
     *            die Frage ID
     */
    public void frageSchliessen(int frageId) {
        dataStore.updateStatusOfFrage(frageId, Frage.STATUS_GESCHLOSSEN);
    }

    /**
     * Eine Antwort fuer eine gegebene Frage abschicken. Es koennen nur
     * Antworten fuer freigeschaltete Fragen abgeschickt werden.
     * 
     * @param frageId
     *            die Frage ID
     * @param antwortNummer
     *            Die Nummer der gewaehlten Antwort. Eine Antwort ist eindeutig
     *            durch die Position in der Antwortliste einer Frage
     *            gekennzeichnet. Die Nummerierung beginnt bei 0: die möglichen
     *            Antwortnummern liegen also im Bereich zwischen 0 und (Anzahl
     *            der Antwortoptionen - 1)
     */
    public void abstimmen(int frageId, int antwortNummer) {
        Frage frage = getFrageById(frageId);
        frage.abstimmen(antwortNummer);
        updateDBFrage(frage);
    }

    /**
     * Gibt die Frage mit ihrer ID-Nr zurück.
     * 
     * @param frageId der Frage
     * @return die Frage         
     */
    
    public Frage getFrageById(int frageId) {
        FrageDTO dto = dataStore.getFrageById(frageId);
        List<String> antworten = dataStore.getAntwortenById(frageId);
        List<Integer> votes = dataStore.getVotings(frageId);
        Frage result = new Frage(dto.getId(), dto.getText(), dto.getStatus(),
                antworten, votes);
        return result;
    }

}
