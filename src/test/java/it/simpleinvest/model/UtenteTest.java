package it.simpleinvest.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

// Autore: Condiviso

// Test di unità per la classe Utente.
// Verifica il corretto funzionamento dei costruttori, dei getter e dei setter,
// e il comportamento del metodo toString(), che restituisce lo username dell’utente.

class UtenteTest {

    @Test
    void testCostruttoreCompleto() {
        Utente utente = new Utente(1, "mario");

        assertEquals(1, utente.getId());
        assertEquals("mario", utente.getUsername());
    }

    @Test
    void testCostruttoreSenzaId() {
        Utente utente = new Utente("luigi");

        assertEquals("luigi", utente.getUsername());
    }

    @Test
    void testSetterEGetter() {
        Utente utente = new Utente("temp");

        utente.setId(5);
        utente.setUsername("admin");

        assertEquals(5, utente.getId());
        assertEquals("admin", utente.getUsername());
    }

    @Test
    void testToStringRestituisceUsername() {
        Utente utente = new Utente("utenteTest");

        assertEquals("utenteTest", utente.toString());
    }
}
