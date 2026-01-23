package it.simpleinvest.service;

import java.util.ArrayList;
import java.util.List;

import it.simpleinvest.dao.AssetDAO;
import it.simpleinvest.model.Asset;

// Autore: Gabriele Levati

// Controller JavaFX responsabile della gestione della vista di ricerca degli asset.
// Coordina l’interazione tra l’interfaccia grafica (FXML) e il servizio RicercaAssetService,
// gestendo le ricerche semplici, le ricerche con filtri avanzati e i suggerimenti
// del robo-advisor in base al profilo di rischio dell’utente.

public class RicercaAssetService {

    private final AssetDAO assetDAO;

    public RicercaAssetService() {
        this.assetDAO = new AssetDAO();
    }

    // Metodo per ottenere tutti gli asset

    public List<Asset> getTuttiAsset() {
        return assetDAO.findAll();
    }

    //RICERCA VELOCE: Cerca per parola chiave.
    public List<Asset> cercaAsset(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return new ArrayList<>();
        }
        return assetDAO.searchAssets(keyword.trim());
    }

    //RICERCA FILTRI: Cerca con filtri avanzati.
    public List<Asset> cercaConFiltri(String tipo, String settore, Integer durataMin, Integer durataMax) {
        return assetDAO.findWithFilters(tipo, settore, durataMin, durataMax);
    }

    //ROBO-ADVISOR (FORM): Traduce il profilo utente in filtri tecnici.
    public List<Asset> suggerisciInvestimenti(String propensioneRischio, int anni) {
        String tipoTarget = null;
        String settoreTarget = null;
        Integer durataMax = null;

        // Logica di traduzione
        if ("BASSO".equalsIgnoreCase(propensioneRischio)) {
            tipoTarget = "OBBLIGAZIONE";
            durataMax = anni; 
        } 
        else if ("MEDIO".equalsIgnoreCase(propensioneRischio)) {
            if (anni >= 5) {
                tipoTarget = "ETF";
            } else {
                tipoTarget = "OBBLIGAZIONE";
            }
        } 
        else if ("ALTO".equalsIgnoreCase(propensioneRischio)) {
            tipoTarget = "AZIONE";
            settoreTarget = "Tecnologia"; 
        }

        return assetDAO.findWithFilters(tipoTarget, settoreTarget, null, durataMax);
    }

    // Aggiunta di un nuovo asset per admin
    public void aggiungiAsset(Asset asset) {
        if (asset.getNome() == null || asset.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome obbligatorio");
        }
        assetDAO.create(asset);
    }
}