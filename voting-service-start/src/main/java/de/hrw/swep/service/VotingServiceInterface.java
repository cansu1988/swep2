package de.hrw.swep.service;

import java.util.Set;

import de.hrw.swep.service.business.Frage;

/**
 * Das VotingServiceInterface ist die Schnittstelle, ueber die die Dienste
 * des Voting-Services in Anspruch genommen werden koennen. Alle hinterlegten
 * Fragen und Antwortmoeglichkeiten koennen hier abgefragt werden, sowie
 * einzelne Fragen freigeschaltet bzw. geschlossen werden. Auch Votings werden
 * ueber das VotingServiceInterface entgegengenommen und zur Auswertung an
 * das System weiter geleitet.
 * 
 * @author Sandra Rebholz
 * @author andriesc
 *
 */
public interface VotingServiceInterface {
	
    /**
     * Liefert alle im System hinterlegten Fragen
     * @return das Set aller Fragen
     */
	Set<Frage> getAlleFragen();

	/**
	 * Gibt die Frage zur ID zurück
	 * 
	 * @param frageId zu einer Frage
	 * @return die Frage
	 */
	Frage getFrageById(int frageId);
	
    /**
     * Schaltet die gegebene Frage zur Abstimmung frei.
     * @param frageId die Frage ID
     */
	void frageFreischalten(int frageId);

    /**
     * Schliesst die gegebene Frage. 
     * @param frageId die Frage ID
     */
	void frageSchliessen(int frageId);

    /**
     * Eine Antwort fuer eine gegebene Frage abschicken. Es koennen nur Antworten
     * fuer freigeschaltete Fragen abgeschickt werden.
     * @param frageId die Frage ID
     * @param antwortNummer Die Nummer der gewaehlten Antwort. Eine Antwort
     *                      ist eindeutig durch die Position in der Antwortliste einer
     *                      Frage gekennzeichnet. Die Nummerierung beginnt bei 0: die
     *                      möglichen Antwortnummern liegen also im Bereich zwischen
     *                      0 und (Anzahl der Antwortoptionen - 1)
     */
	void abstimmen(int frageId, int antwortNummer);
	
}
