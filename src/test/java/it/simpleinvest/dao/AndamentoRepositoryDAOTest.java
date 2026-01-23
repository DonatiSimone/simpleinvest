package it.simpleinvest.dao;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.simpleinvest.model.ValoreStorico;

// Autore: Donati Simone

// Test di unità per la classe AndamentoRepositoryDAO.

// Verifica il corretto funzionamento dell'archivio in memoria utilizzato
// per salvare e recuperare la serie storica dei portafogli.

// Essendo una DAO in-memory, il test è completamente deterministico
// e non dipende da database o componenti esterni.


class AndamentoRepositoryDAOTest {

    private AndamentoRepositoryDAO repository;

    @BeforeEach
    void setUp() {
        repository = new AndamentoRepositoryDAO();
    }

    @Test
    void testSaveAndFindByPortafoglioId() {
        int portafoglioId = 1;

        List<ValoreStorico> storico = List.of(
                new ValoreStorico(1, 1000.0),
                new ValoreStorico(1, 1050.0)
        );

        repository.save(portafoglioId, storico);

        List<ValoreStorico> result = repository.findByPortafoglioId(portafoglioId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1000.0, result.get(0).getValore());
        assertEquals(1050.0, result.get(1).getValore());
    }

    @Test
    void testFindByPortafoglioIdNotPresent() {
        List<ValoreStorico> result = repository.findByPortafoglioId(999);

        assertNull(result);
    }

    @Test
    void testOverwriteExistingStorico() {
        int portafoglioId = 2;

        List<ValoreStorico> firstStorico = List.of(
                new ValoreStorico(1, 500.0)
        );

        List<ValoreStorico> updatedStorico = List.of(
                new ValoreStorico(1, 800.0),
                new ValoreStorico(1, 900.0)
        );

        repository.save(portafoglioId, firstStorico);
        repository.save(portafoglioId, updatedStorico);

        List<ValoreStorico> result = repository.findByPortafoglioId(portafoglioId);

        assertEquals(2, result.size());
        assertEquals(800.0, result.get(0).getValore());
        assertEquals(900.0, result.get(1).getValore());
    }
}
