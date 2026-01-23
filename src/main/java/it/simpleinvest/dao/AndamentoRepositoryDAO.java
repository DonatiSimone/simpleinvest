package it.simpleinvest.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.simpleinvest.model.ValoreStorico;

// Autore: Donati Simone

// Fornisce un archivio in memoria per la gestione delle serie storiche dei portafogli.
// Permette di salvare e recuperare l’andamento di un portafoglio senza persistenza su database.

public class AndamentoRepositoryDAO {

    private final Map<Integer, List<ValoreStorico>> storage = new HashMap<>();

    public List<ValoreStorico> findByPortafoglioId(int portafoglioId) {
        return storage.get(portafoglioId);
    }

    public void save(int portafoglioId, List<ValoreStorico> storico) {
        storage.put(portafoglioId, storico);
    }
}