package de.hrw.swep.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.collections.Sets;

import de.hrw.swep.service.business.Frage;
import de.hrw.swep.service.business.FrageGeschlossen;
import de.hrw.swep.persistence.DataStoreInterface;
import de.hrw.swep.persistence.dto.FrageDTO;

/**
 * Testklasse fuer den Voting-Service
 * 
 * @author Sandra Rebholz
 *Student Serife Bolat, 22407
 */
public class VotingServiceTest {

    private static final String AUTO_FRAGE = "Ab welchem Alter sollte man Auto fahren duerfen?";
    private static final String TEMPO_30 = "Sollte in der Innenstadt generell Tempo 30 gelten?";

    VotingServiceInterface votingService;
    DataStoreInterface dsi;

    FrageDTO fuehrerschein = new FrageDTO(1, AUTO_FRAGE, "offen");
    FrageDTO tempo = new FrageDTO(2, TEMPO_30, "geschlossen");

    /**
     * Initialisiert das Mock-Objekt fuer den DataStore und erzeugt das zu
     * testende Objekt.
     */
    @Before
    public void setUp() {
        dsi = Mockito.mock(DataStoreInterface.class);

        votingService = new VotingService(dsi);
    }

    /**
     * Testet, ob alle im System hinterlegten Fragen zurueck geliefert werden.
     */
    @Test
    public void testGetAlleFragen() {

        when(dsi.getAlleFragen()).thenReturn(Sets.newSet(fuehrerschein, tempo));

        Set<Frage> fragen = votingService.getAlleFragen();
        assertEquals(2, fragen.size());
        for (Frage frage : fragen) {
            if (frage.getText().equals(AUTO_FRAGE)) {
                return;
            }
        }
        fail();
    }

    /**
     * Testet, ob eine Frage freigeschaltet werden kann.
     */
    @Test
    public void testFrageFreischalten() {
        votingService.frageFreischalten(1);
        verify(dsi, times(1)).updateStatusOfFrage(1, Frage.STATUS_OFFEN);
    }

    /**
     * Testet, ob eine Abstimmung geschlossen werden kann.
     */
    @Test
    public void testFrageSchliessen() {
        votingService.frageSchliessen(1);
        verify(dsi, times(1)).updateStatusOfFrage(1, Frage.STATUS_GESCHLOSSEN);
    }

    /**
     * Testet, ob ein Voting zu einer freigegebenen Frage eingereicht werden
     * kann.
     */
    @Test
    public void testAbstimmen() {
        // DataStore-Mock vorbereiten mit Fuehrerschein-Frage und passenden
        // Antworten
        when(dsi.getFrageById(1)).thenReturn(fuehrerschein);
        when(dsi.getAntwortenById(1)).thenReturn(
                Arrays.asList("Fuehrerschein ab 16 Jahren",
                        "Fuehrerschein ab 17 Jahren",
                        "Fuehrerschein ab 18 Jahren",
                        "Fuehrerschein ab 20 Jahren"));
        // DataStore-Mock mit Abstimmungsergebnissen für Fuehrerschein-Frage
        // vorbereiten
        when(dsi.getVotings(1)).thenReturn(Arrays.asList(0, 0, 0, 0));

        // Eigentlicher Test
        votingService.abstimmen(1, 2);

        // Abstimmungsergebnisse werden über updateFrage und updateVotings im
        // DataStore abgelegt -- prüfen, ob wirklich das zugehörige FrageDTO und
        // die Abstimmungen für Antwort 2 dieser Frage 1 genau einmal
        // abgespeichert wurde
        verify(dsi, times(1)).updateFrage(fuehrerschein);
        verify(dsi, times(1)).updateStimmen(1, 2, 1);
    }

    /**
     * Prueft, ob ein Voting zu einer geschlossenen Frage die erwartete
     * Exception ausloest.
     * canuu ist cool
     */
    @Test(expected = IllegalStateException.class)
    public void testAbstimmenBeiGeschlossenerFrage() {
        Frage frage = new Frage(2, "TestText", "geschlossen", null, null);
        frage.getStatus();
        
        frage.abstimmen(2);
        assertEquals("de.hrw.swep.service.business.FrageOffen", frage.getStatus().getClass().getName());
        
        
    }

}
