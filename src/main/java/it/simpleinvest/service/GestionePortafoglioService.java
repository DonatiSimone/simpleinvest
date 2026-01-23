package it.simpleinvest.service;

import java.util.ArrayList;
import java.util.List;

import it.simpleinvest.dao.AndamentoRepositoryDAO;
import it.simpleinvest.dao.PortafoglioDAO;
import it.simpleinvest.model.AndamentoPredefinito;
import it.simpleinvest.model.Portafoglio;
import it.simpleinvest.model.ValoreStorico;

// Autore: Donati Simone

// Implementa la logica di business del caso d’uso “Gestione Portafoglio”.
// Coordina DAO e modelli per creare, modificare, eliminare portafogli e per generare o recuperare il loro andamento storico.

public class GestionePortafoglioService {

    private final PortafoglioDAO portafoglioDAO;
    private final AndamentoRepositoryDAO andamentoRepositoryDAO;

    public GestionePortafoglioService() {
        this.portafoglioDAO = new PortafoglioDAO();
        this.andamentoRepositoryDAO = new AndamentoRepositoryDAO();
    }

    //Recupera tutti i portafogli dell'utente.
    public List<Portafoglio> getPortafogliUtente(int utenteId) {

        List<Portafoglio> reali =
            portafoglioDAO.findByUtente(utenteId);

        List<Portafoglio> demo =
            generaPortafogliDemo(utenteId);

        List<Portafoglio> tutti = new ArrayList<>();
        tutti.addAll(reali);
        tutti.addAll(demo);

        return tutti;
    }


    //Crea un nuovo portafoglio.
    public void creaPortafoglio(String nome, double capitale, int utenteId) {
        if (nome == null || nome.isBlank()) {
            //System.out.println("Tentativo di creazione con nome non valido");
            throw new IllegalArgumentException("Nome portafoglio non valido");
        }
        if (capitale <= 0) {
            //System.out.println("Tentativo di creazione con capitale non valido");
            throw new IllegalArgumentException("Capitale non valido");
        }

        Portafoglio p = new Portafoglio(nome, capitale, utenteId);
        portafoglioDAO.create(p);
    }

    //Rinomina un portafoglio esistente.
    public void rinominaPortafoglio(int portafoglioId, String nuovoNome) {
        if (nuovoNome == null || nuovoNome.isBlank()) {
            throw new IllegalArgumentException("Nuovo nome non valido");
        }

        portafoglioDAO.rename(portafoglioId, nuovoNome);
    }

    //Elimina un portafoglio.
    public void eliminaPortafoglio(int portafoglioId) {
        portafoglioDAO.delete(portafoglioId);
    }


    //Portafoglio è nuovo?
    public boolean isNuovo(Portafoglio p) {
        List<ValoreStorico> storico =
            andamentoRepositoryDAO.findByPortafoglioId(p.getId());

        return storico == null || storico.isEmpty();
    }


    public List<ValoreStorico> getAndamentoPortafoglio(Portafoglio p) {

        List<ValoreStorico> storico =
            andamentoRepositoryDAO.findByPortafoglioId(p.getId());

        if (storico == null || storico.isEmpty()) {

            if (isDemo(p)) {
                AndamentoPredefinito profilo = scegliProfilo(p);
                storico = generaStoricoDaProfilo(
                    p.getCapitale(), profilo
                );
            } else {
                // portafoglio reale nuovo
                storico = generaLineaPiatta(p.getCapitale());
            }

            andamentoRepositoryDAO.save(p.getId(), storico);
        }

        return storico;
    }


    //Genera una linea piatta
    private List<ValoreStorico> generaLineaPiatta(double valore) {
        List<ValoreStorico> lista = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            lista.add(new ValoreStorico(i, valore));
        }
        return lista;
    }


    //Genera un andamento da un profilo pre-caricato
    private List<ValoreStorico> generaStoricoDaProfilo(
        double capitaleIniziale,
        AndamentoPredefinito profilo) {

        List<ValoreStorico> lista = new ArrayList<>();

        double valore = capitaleIniziale;
        int giorno = 0;

        lista.add(new ValoreStorico(giorno++, valore));

        for (double variazione : profilo.getVariazioni()) {
            valore += valore * variazione;
            lista.add(new ValoreStorico(giorno++, valore));
        }
        return lista;
    }

    private AndamentoPredefinito scegliProfilo(Portafoglio p) {

        String nome = p.getNome().toLowerCase();

        if (nome.contains("crescita")) {
            return AndamentoPredefinito.CRESCITA;
        }
        if (nome.contains("perdita")) {
            return AndamentoPredefinito.PERDITA;
        }
        if (nome.contains("volatile")) {
            return AndamentoPredefinito.VOLATILE;
        }
        if (nome.contains("stabile")) {
            return AndamentoPredefinito.STABILE;
        }

        // fallback di sicurezza
        return AndamentoPredefinito.STABILE;
    }

    //Genera i portafogli fittizi
    private List<Portafoglio> generaPortafogliDemo(int utenteId) {

        List<Portafoglio> demo = new ArrayList<>();

        demo.add(new Portafoglio(1001, "Demo Crescita - non modificabile", 10000, utenteId));
        demo.add(new Portafoglio(1002, "Demo Perdita - non modificabile", 8000, utenteId));
        demo.add(new Portafoglio(1003, "Demo Volatile - non modificabile", 12000, utenteId));
        demo.add(new Portafoglio(1004, "Demo Stabile - non modificabile", 5000, utenteId));

        return demo;
    }

    private boolean isDemo(Portafoglio p) {
        return p.getId() >= 1000;
    }




}
