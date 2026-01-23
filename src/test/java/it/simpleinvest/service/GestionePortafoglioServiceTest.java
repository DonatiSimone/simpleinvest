package it.simpleinvest.service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import it.simpleinvest.dao.PortafoglioDAO;
import it.simpleinvest.model.Portafoglio;

// Autore: Donati Simone

//Test del Service layer per il caso d'uso GestionePortafoglio

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GestionePortafoglioServiceTest {

    private static GestionePortafoglioService service;
    private static PortafoglioDAO dao;

    private static final int TEST_UTENTE_ID = 1;
    private static int createdPortafoglioId;


    @BeforeAll
    static void setup() {
        service = new GestionePortafoglioService();
        dao = new PortafoglioDAO();
    }

    @Test
    @Order(1)
    void testRecuperoPortafogliUtente() {
        List<Portafoglio> portafogli = service.getPortafogliUtente(TEST_UTENTE_ID);

        assertNotNull(portafogli);
    }

    @Test
    @Order(2)
    void testCreazionePortafoglioValido() {
        service.creaPortafoglio("ServiceTest", 1000.0, TEST_UTENTE_ID);

        List<Portafoglio> portafogli = dao.findByUtente(TEST_UTENTE_ID);
        assertFalse(portafogli.isEmpty());

        Portafoglio ultimo = portafogli.get(portafogli.size() - 1);
        createdPortafoglioId = ultimo.getId();

        assertEquals("ServiceTest", ultimo.getNome());
        assertEquals(1000.0, ultimo.getCapitale());
    }

    @Test
    @Order(3)
    void testCreazionePortafoglioCapitaleNonValido() {
        assertThrows(IllegalArgumentException.class, () ->
            service.creaPortafoglio("Errore", -500, TEST_UTENTE_ID)
        );
    }

    @Test
    @Order(4)
    void testRinominaPortafoglio() {
        service.rinominaPortafoglio(createdPortafoglioId, "RinominatoService");

        List<Portafoglio> portafogli = dao.findByUtente(TEST_UTENTE_ID);

        boolean trovato = portafogli.stream()
                .anyMatch(p ->
                        p.getId() == createdPortafoglioId &&
                        p.getNome().equals("RinominatoService")
                );

        assertTrue(trovato);
    }

    @Test
    @Order(5)
    void testRinominaNomeNonValido() {
        assertThrows(IllegalArgumentException.class, () ->
            service.rinominaPortafoglio(createdPortafoglioId, "")
        );
    }

    @Test
    @Order(6)
    void testEliminazionePortafoglio() {
        service.eliminaPortafoglio(createdPortafoglioId);

        List<Portafoglio> portafogli = dao.findByUtente(TEST_UTENTE_ID);

        boolean trovato = portafogli.stream()
                .anyMatch(p -> p.getId() == createdPortafoglioId);

        assertFalse(trovato);
    }
}
