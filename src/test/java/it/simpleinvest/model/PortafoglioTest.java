package it.simpleinvest.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

// Autore: Donati Simone

// Test di unità per la classe Portafoglio.
// Verifica il corretto funzionamento dei costruttori, dei getter e dei setter,
// e il comportamento del metodo toString(), che restituisce il nome del portafoglio.

class PortafoglioTest {

    @Test
    void testCostruttoreCompleto() {
        Portafoglio portafoglio = new Portafoglio(
                1,
                "Portafoglio Test",
                10000.0,
                5
        );

        assertEquals(1, portafoglio.getId());
        assertEquals("Portafoglio Test", portafoglio.getNome());
        assertEquals(10000.0, portafoglio.getCapitale());
        assertEquals(5, portafoglio.getUtenteId());
    }

    @Test
    void testCostruttoreSenzaId() {
        Portafoglio portafoglio = new Portafoglio(
                "Nuovo Portafoglio",
                5000.0,
                3
        );

        assertEquals("Nuovo Portafoglio", portafoglio.getNome());
        assertEquals(5000.0, portafoglio.getCapitale());
        assertEquals(3, portafoglio.getUtenteId());
    }

    @Test
    void testSetterEGetter() {
        Portafoglio portafoglio = new Portafoglio(
                "Temp",
                0.0,
                0
        );

        portafoglio.setId(10);
        portafoglio.setNome("Aggiornato");
        portafoglio.setCapitale(7500.5);
        portafoglio.setUtenteId(7);

        assertEquals(10, portafoglio.getId());
        assertEquals("Aggiornato", portafoglio.getNome());
        assertEquals(7500.5, portafoglio.getCapitale());
        assertEquals(7, portafoglio.getUtenteId());
    }

    @Test
    void testToStringRestituisceNome() {
        Portafoglio portafoglio = new Portafoglio(
                "Portafoglio String",
                3000.0,
                2
        );

        assertEquals("Portafoglio String", portafoglio.toString());
    }
}
