package it.simpleinvest.service;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.simpleinvest.dao.AssetDAO;
import it.simpleinvest.db.DBManager;
import it.simpleinvest.model.Asset;

// Autore: Gabriele Levati

// Test di integrazione per la classe RicercaAssetService.
// Verifica il corretto funzionamento dei metodi di ricerca semplice, ricerca avanzata,
// suggerimento degli investimenti (robo-advisor) e validazione dei dati in ingresso,
// utilizzando un database reale per l’accesso agli asset.

class RicercaAssetServiceTest {

    private static RicercaAssetService service;

    @BeforeAll
    static void resetDBManager() throws Exception {
        Field instanceField = DBManager.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null);
    }
    @BeforeAll
    static void forceTestDatabase() {
        System.setProperty("simpleinvest.db.name", "simpleinvest-test.db");
    }
    @BeforeAll
    static void setUp() throws Exception {
        service = new RicercaAssetService();

        // Pulizia tabella asset per garantire test deterministici
        Connection conn = DBManager.getInstance().getConnection();
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM asset");
        }

        AssetDAO dao = new AssetDAO();

        dao.create(new Asset(null, "ETF Globale", "ETF", "FINANZA", null, false, "ETF001"));
        dao.create(new Asset(null, "Bond Stato", "OBBLIGAZIONE", "GOV", 5, false, "BOND001"));
        dao.create(new Asset(null, "Tech Stock", "AZIONE", "Tecnologia", null, true, "AZ001"));
    }

    @Test
    void testGetTuttiAsset() {
        List<Asset> assets = service.getTuttiAsset();

        assertEquals(4, assets.size());
    }

    @Test
    void testCercaAssetKeywordValida() {
        List<Asset> result = service.cercaAsset("ETF");

        assertEquals(2, result.size());
        assertEquals("ETF Globale", result.get(0).getNome());
    }

    @Test
    void testCercaAssetKeywordVuota() {
        List<Asset> result = service.cercaAsset("   ");

        assertTrue(result.isEmpty());
    }

    @Test
    void testCercaConFiltri() {
        List<Asset> result = service.cercaConFiltri(
                "OBBLIGAZIONE",
                null,
                null,
                null
        );

        assertEquals(1, result.size());
        assertEquals("Bond Stato", result.get(0).getNome());
    }

    @Test
    void testSuggerisciInvestimentiRischioBasso() {
        List<Asset> result = service.suggerisciInvestimenti("BASSO", 5);

        assertEquals(1, result.size());
        assertEquals("OBBLIGAZIONE", result.get(0).getTipo());
    }

    @Test
    void testSuggerisciInvestimentiRischioAlto() {
        List<Asset> result = service.suggerisciInvestimenti("ALTO", 10);

        assertEquals(1, result.size());
        assertEquals("AZIONE", result.get(0).getTipo());
        assertEquals("Tecnologia", result.get(0).getSettore());
    }

    @Test
    void testAggiungiAssetValido() {
        Asset asset = new Asset(
                null,
                "Nuovo Asset",
                "ETF",
                "FINANZA",
                null,
                false,
                "ETF999"
        );

        service.aggiungiAsset(asset);

        List<Asset> assets = service.getTuttiAsset();
        assertEquals(4, assets.size());
    }

    @Test
    void testAggiungiAssetNomeNonValido() {
        Asset asset = new Asset(
                null,
                "   ",
                "ETF",
                "FINANZA",
                null,
                false,
                "ERR001"
        );

        assertThrows(IllegalArgumentException.class, () -> service.aggiungiAsset(asset));
    }
}
