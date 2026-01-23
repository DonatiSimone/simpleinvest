package it.simpleinvest.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.simpleinvest.db.DBManager;
import it.simpleinvest.model.Asset;

// Test di integrazione minimale per la classe AssetDAO.
// Verifica che il DAO sia in grado di eseguire correttamente
// le operazioni principali sul database senza errori,
// senza assumere uno stato specifico del DB.

class AssetDAOTest {

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

    @Test
    void testCreateAndRead() throws Exception {
        AssetDAO dao = new AssetDAO();

        Connection conn = DBManager.getInstance().getConnection();
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM asset");
        }

        Asset asset = new Asset(
                null,
                "Test Asset",
                "AZIONE",
                "TECH",
                null,
                false,
                "TEST123"
        );

        assertDoesNotThrow(() -> dao.create(asset));

        List<Asset> assets = dao.findAll();
        assertFalse(assets.isEmpty());

        Asset loaded = assets.get(0);
        assertEquals("Test Asset", loaded.getNome());
        assertEquals("AZIONE", loaded.getTipo());
        assertNull(loaded.getDurata());
    }

    @Test
    void testSearchDoesNotFail() {
        AssetDAO dao = new AssetDAO();

        assertDoesNotThrow(() -> dao.searchAssets("Test"));
    }

    @Test
    void testDeleteDoesNotFail() {
        AssetDAO dao = new AssetDAO();

        List<Asset> assets = dao.findAll();
        if (!assets.isEmpty()) {
            assertDoesNotThrow(() ->
                dao.delete(assets.get(0).getId())
            );
        }
    }
}
