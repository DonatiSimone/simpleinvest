package it.simpleinvest.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.simpleinvest.model.Utente;

// Autore: Condiviso

// Test di unità per la classe UserSession.
// Verifica il corretto funzionamento del pattern Singleton e la gestione
// dello stato dell’utente corrente, inclusi login, logout e controllo
// dello stato di autenticazione.

class UserSessionTest {

    private UserSession session;

    @BeforeEach
    void setUp() {
        session = UserSession.getInstance();
        session.logout();
    }

    @Test
    void testSingletonStessaIstanza() {
        UserSession session1 = UserSession.getInstance();
        UserSession session2 = UserSession.getInstance();

        assertSame(session1, session2);
    }

    @Test
    void testSetEGetUtenteCorrente() {
        Utente utente = new Utente(1, "testUser");

        session.setUtenteCorrente(utente);

        assertEquals(utente, session.getUtenteCorrente());
    }

    @Test
    void testIsUtenteLoggatoTrue() {
        Utente utente = new Utente("mario");
        session.setUtenteCorrente(utente);

        assertTrue(session.isUtenteLoggato());
    }

    @Test
    void testIsUtenteLoggatoFalse() {
        session.logout();

        assertFalse(session.isUtenteLoggato());
    }

    @Test
    void testLogout() {
        Utente utente = new Utente("luigi");
        session.setUtenteCorrente(utente);

        session.logout();

        assertNull(session.getUtenteCorrente());
        assertFalse(session.isUtenteLoggato());
    }
}
