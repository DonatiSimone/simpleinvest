package it.simpleinvest.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

// Autore: Donati Simone

// Test di unità per la classe ValoreStorico.
//
// Verifica il corretto funzionamento del costruttore
// e dei metodi getter della classe, che rappresenta
// un singolo valore della serie storica di un portafoglio.

class ValoreStoricoTest {

    @Test
    void testConstructorAndGetters() {
        int giorno = 10;
        double valore = 1500.75;

        ValoreStorico storico = new ValoreStorico(giorno, valore);

        assertEquals(giorno, storico.getGiorno());
        assertEquals(valore, storico.getValore());
    }

    @Test
    void testValoreZero() {
        ValoreStorico storico = new ValoreStorico(1, 0.0);

        assertEquals(0.0, storico.getValore());
    }

    @Test
    void testNegativeValore() {
        ValoreStorico storico = new ValoreStorico(5, -250.0);

        assertEquals(-250.0, storico.getValore());
    }

    @Test
    void testGiornoZero() {
        ValoreStorico storico = new ValoreStorico(0, 1000.0);

        assertEquals(0, storico.getGiorno());
    }
}
