package it.simpleinvest.db;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

// Autore: Condiviso

// Test di integrazione per la classe DBManager.
// Verifica il corretto funzionamento del Singleton e della connessione
// al database SQLite, assicurando che il database sia accessibile
// e contenga dati coerenti.

class DBManagerTest {

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
    void testGetConnectionAndQuery() {
        try {
            Connection conn = DBManager.getInstance().getConnection();

            assertNotNull(conn);
            assertFalse(conn.isClosed());

            Statement stmt = conn.createStatement();
            
            assertDoesNotThrow(() ->
                stmt.executeQuery("SELECT username FROM utente")
            );

            stmt.close();

        } catch (Exception e) {
            fail("Errore durante il test del DBManager: " + e.getMessage());
        }
    }
}
