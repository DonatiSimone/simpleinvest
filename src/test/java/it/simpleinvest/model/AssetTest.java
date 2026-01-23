package it.simpleinvest.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

// Autore: Gabriele Levati

// Test di unità per la classe Asset.
// Verifica il corretto funzionamento dei costruttori, dei getter e dei setter,
// inclusa la gestione dei campi che possono assumere valore null.

class AssetTest {

    @Test
    void testCostruttoreCompletoEGetter() {
        Asset asset = new Asset(
                1,
                "Apple",
                "AZIONE",
                "TECH",
                12,
                true,
                "US0378331005"
        );

        assertEquals(1, asset.getId());
        assertEquals("Apple", asset.getNome());
        assertEquals("AZIONE", asset.getTipo());
        assertEquals("TECH", asset.getSettore());
        assertEquals(12, asset.getDurata());
        assertTrue(asset.getIsStorico());
        assertEquals("US0378331005", asset.getIsin());
    }

    @Test
    void testCostruttoreVuoto() {
        Asset asset = new Asset();

        assertNull(asset.getId());
        assertNull(asset.getNome());
        assertNull(asset.getTipo());
        assertNull(asset.getSettore());
        assertNull(asset.getDurata());
        assertNull(asset.getIsStorico());
        assertNull(asset.getIsin());
    }

    @Test
    void testSetterEGetter() {
        Asset asset = new Asset();

        asset.setId(10);
        asset.setNome("Bitcoin");
        asset.setTipo("CRYPTO");
        asset.setSettore("FINANCE");
        asset.setDurata(null);
        asset.setIsStorico(false);
        asset.setIsin("BTC-ISIN");

        assertEquals(10, asset.getId());
        assertEquals("Bitcoin", asset.getNome());
        assertEquals("CRYPTO", asset.getTipo());
        assertEquals("FINANCE", asset.getSettore());
        assertNull(asset.getDurata());
        assertFalse(asset.getIsStorico());
        assertEquals("BTC-ISIN", asset.getIsin());
    }

    @Test
    void testToStringNonNull() {
        Asset asset = new Asset(
                2,
                "Test Asset",
                "BOND",
                "GOV",
                null,
                false,
                "TEST123"
        );

        assertNotNull(asset.toString());
        assertTrue(asset.toString().contains("Test Asset"));
    }
}
