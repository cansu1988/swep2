package de.hrw.swep.persistence.dto;

/**
 * Das Data Transfer Object (DTO) fuer die Daten einer Abstimmungsfrage.
 * 
 * @author Sandra Rebholz
 * @author andriesc
 * Student Serife Bolat, 22407
 *
 */
public class FrageDTO {
    /**
     * Attribute:
     * für id der Fragen -> id
     * text zu der Frage -> text
     * status der Frage -> offen oder geschlossen
     */
    private int id;
    private String text;
    private String status;

    /**
     * Konstruktor
     * 
     * @param id
     *            die Fragen-ID
     * @param text
     *            der Text der Fragestellung
     * @param status
     *            der Status der Frage (offen/geschlossen)
     */
    public FrageDTO(int id, String text, String status) {
        super();
        this.id = id;
        this.text = text;
        this.status = status;
    }

    /**
     * Liefert die Frage-ID zurueck.
     * 
     * @return die Frage-ID
     */
    public int getId() {
        return id;
    }

    /**
     * Setzt die Frage-ID
     * 
     * @param id
     *            die Frage-ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Liefert den Text der Fragestellung
     * 
     * @return die Fragestellung
     */
    public String getText() {
        return text;
    }

    /**
     * Setzt den Text der Fragestellung
     * 
     * @param text
     *            die Fragestellung
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Liefert den Status der Frage
     * 
     * @return der Status (offen/geschlossen)
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setzt den Status der Fragehgjghgjk
     * 
     * @param status
     *            der Status (offen/geschlossen)
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * <code>equals</code> überschrieben, damit FrageDTOs aufgrund der Attribute
     * miteinander im Test verglichen werden können
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (getClass() == obj.getClass()) {
            FrageDTO other = (FrageDTO) obj;

            if (id != other.id)
                return false;
            if (status == null) {
                if (other.status != null)
                    return false;
            } else if (!status.equals(other.status))
                return false;

            if (text == null) {
                if (other.text != null)
                    return false;
            } else if (!text.equals(other.text))
                return false;

            // Wenn sowohl id, status als auch text nicht ungleich sind, sind
            // sie alle gleich und die Objekte sind damit gleich
            return true;

        } else
            return false;
    }

    /**
     * Überschrieben, weil die Spezifikation von hashcode verlangt: "If two
     * objects are equal according to the equals(Object) method, then calling
     * the hashCode method on each of the two objects must produce the same
     * integer result."
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((text == null) ? 0 : text.hashCode());
        return result;
    }

}
