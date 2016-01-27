package de.hrw.swep.persistence;

import java.util.List;
import java.util.Set;

import de.hrw.swep.persistence.dto.FrageDTO;

/** 
 * Das DataStoreInterface repraesentiert die Schnittstelle zur Datenhaltung. Ueber den
 * DataStore koennen alle im System hinterlegten Fragen und Antworten abgerufen werden.
 * Ausserdem koennen Fragen zur Abstimmung freigeschaltet oder geschlossen werden und
 * einzelne Votings zu einer Abstimmung entgegengenommen werden.
 * 
 * @author Sandra Rebholz
 *
 */
public interface DataStoreInterface {

	/**
	 * Liefert alle im System hinterlegten Fragen
	 * @return Das Set aller Fragen
	 */
    Set<FrageDTO> getAlleFragen();
    
    /**
     * Liefert alle Antwortoptionen zu einer Frage
     * @param frageId die Frage-ID
     * @return eine geordnete Liste aller Antwortoptionen
     */
	List<String> getAntwortenById(int frageId);

	/**
	 * Setzt den Status einer Frage
	 * @param frageId die Frage-ID
	 * @param status der zu setzende Status (offen/geschlossen)
	 */
	void updateStatusOfFrage(int frageId, String status);

	/**
	 * Fuegt ein Voting hinzu
	 * @param frageId die Frage-ID
	 * @param antwortNummer die Nummer der ausgewaehlten Antwort
	 */
	void addVoting(int frageId, int antwortNummer);

	/**
	 * Liefert die Frage mit der gegebenen ID zurueck
	 * @param frageId die Frage-ID
	 * @return die Frage
	 */
	FrageDTO getFrageById(int frageId);
	
	/**
	 * Aktualisiert Frage in Datenbank
	 * 
	 * @param dto DTO für die Frage
	 */
	void updateFrage(FrageDTO dto);
	
	/**
	 * Abstimmungen zur Frage in Datenbank aktualisieren 
	 * 
	 * @param frageId Die Frage, um die es geht
	 * @param antwortNummer Die Antwort zur Frage, um die es geht
	 * @param stimmen Die Anzahl der Stimmen
	 */
	void updateStimmen(int frageId, int antwortNummer, int stimmen);
	
	/**
	 * Liefert die Abstimmungsergebnisse zu einer Frage zurueck
	 * 
	 * @param frageId die Frage-ID
	 * @return Liste
	 */
	List<Integer> getVotings(int frageId);
		
}
