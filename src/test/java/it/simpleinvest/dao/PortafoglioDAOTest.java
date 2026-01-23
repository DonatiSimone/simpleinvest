package it.simpleinvest.dao;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import it.simpleinvest.model.Portafoglio;

// Autore: Donati Simone

// I test JUnit interagiscono con il database SQLite 
// per verificare il corretto funzionamento dei DAO. 
// Trattandosi di un ambiente di sviluppo e test, 
// le operazioni di modifica dei dati sono 
// controllate e ripristinate a fine test.

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PortafoglioDAOTest {

    private static PortafoglioDAO dao;
    private static int TEST_UTENTE_ID = 1;
    private static int createdPortafoglioId;

    @BeforeAll
    static void setup() {
        dao = new PortafoglioDAO();
    }

    @Test
    @Order(1)
    void testFindByUtente() {
        List<Portafoglio> list = dao.findByUtente(TEST_UTENTE_ID);
        assertNotNull(list);
        list.forEach(System.out::println);
    }

    @Test
    @Order(2)
    void testCreatePortafoglio() {
        Portafoglio p = new Portafoglio("PortafoglioTest", 1000.0, TEST_UTENTE_ID);
        dao.create(p);

        List<Portafoglio> list = dao.findByUtente(TEST_UTENTE_ID);
        assertFalse(list.isEmpty());

        createdPortafoglioId = list.get(list.size() - 1).getId();
        assertTrue(createdPortafoglioId > 0);
        list.forEach(System.out::println);
    }

    @Test
    @Order(3)
    void testRenamePortafoglio() {
        dao.rename(createdPortafoglioId, "PortafoglioRinominato");

        List<Portafoglio> list = dao.findByUtente(TEST_UTENTE_ID);
        boolean found = list.stream()
                .anyMatch(p -> p.getId() == createdPortafoglioId &&
                               p.getNome().equals("PortafoglioRinominato"));

        assertTrue(found);
        list.forEach(System.out::println);
    }

    @Test
    @Order(4)
    void testDeletePortafoglio() {
        dao.delete(createdPortafoglioId);

        List<Portafoglio> list = dao.findByUtente(TEST_UTENTE_ID);
        boolean found = list.stream()
                .anyMatch(p -> p.getId() == createdPortafoglioId);

        assertFalse(found);
    }
}
