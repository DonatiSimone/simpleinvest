package it.simpleinvest.util;

import it.simpleinvest.model.Utente;

// Autore: Condiviso

// Gestisce l'utente attualmente loggato nell'applicazione.

public class UserSession {

    private static UserSession instance;
    private Utente utenteCorrente;

    // Costruttore privato
    private UserSession() {
    }

    //Restituisce l'istanza unica della sessione.
    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    //Imposta l'utente corrente.
    public void setUtenteCorrente(Utente utente) {
        this.utenteCorrente = utente;
    }

    //Restituisce l'utente corrente.
    public Utente getUtenteCorrente() {
        return utenteCorrente;
    }

    //Verifica se un utente è loggato.
    public boolean isUtenteLoggato() {
        return utenteCorrente != null;
    }

    //Termina la sessione utente.
    public void logout() {
        utenteCorrente = null;
    }
}
