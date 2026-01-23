package it.simpleinvest.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

// Autore: Donati Simone

 // Test di unità per l'enum AndamentoPredefinito.
 //
 // Verifica che ciascun profilo di andamento predefinito sia correttamente
 // inizializzato e che le variazioni percentuali associate siano coerenti
 // e accessibili tramite il metodo getVariazioni().

class AndamentoPredefinitoTest {

    @Test
    void testCrescitaVariazioni() {
        double[] variazioni = AndamentoPredefinito.CRESCITA.getVariazioni();

        assertNotNull(variazioni);
        assertTrue(variazioni.length > 0);
        assertEquals(0.00, variazioni[0]);
    }

    @Test
    void testPerditaVariazioni() {
        double[] variazioni = AndamentoPredefinito.PERDITA.getVariazioni();

        assertNotNull(variazioni);
        assertTrue(variazioni.length > 0);
        assertTrue(variazioni[1] < 0);
    }

    @Test
    void testVolatileContainsPositiveAndNegativeValues() {
        double[] variazioni = AndamentoPredefinito.VOLATILE.getVariazioni();

        boolean hasPositive = false;
        boolean hasNegative = false;

        for (double v : variazioni) {
            if (v > 0) hasPositive = true;
            if (v < 0) hasNegative = true;
        }

        assertTrue(hasPositive);
        assertTrue(hasNegative);
    }

    @Test
    void testStabileHasSmallVariations() {
        double[] variazioni = AndamentoPredefinito.STABILE.getVariazioni();

        for (double v : variazioni) {
            assertTrue(Math.abs(v) <= 0.01);
        }
    }
}
