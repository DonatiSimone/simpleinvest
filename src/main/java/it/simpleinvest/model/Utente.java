package it.simpleinvest.model;

// Autore: Condiviso

// Rappresenta un utente del sistema SimpleInvest.
// Contiene le informazioni identificative dell’utente ed è utilizzato per associare uno o più portafogli al proprietario.

public class Utente {

    private int id;
    private String username;

    // Costruttore completo (lettura dal DB)
    public Utente(int id, String username) {
        this.id = id;
        this.username = username;
    }

    // Costruttore senza id (creazione)
    public Utente(String username) {
        this.username = username;
    }

    // Getter e Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return username;
    }
}
