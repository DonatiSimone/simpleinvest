package it.simpleinvest.ricerca;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.simpleinvest.model.Asset;
import it.simpleinvest.service.RicercaAssetService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

// Autore: Gabriele Levati

// Controller JavaFX responsabile della gestione della vista di ricerca degli asset.
// Coordina l’interazione tra l’interfaccia grafica (FXML) e il servizio RicercaAssetService,
// gestendo le ricerche semplici, le ricerche con filtri avanzati e i suggerimenti
// del robo-advisor in base al profilo di rischio dell’utente.

public class RicercaAssetController implements Initializable {

    //@FXML private ListView<Asset> assetListView;

    //Ricerca Semplice
    @FXML private TextField searchField;

    //Ricerca con filtri
    @FXML private ComboBox<String> tipoCombo;
    @FXML private ComboBox<String> settoreCombo;
    @FXML private TextField durataMinField;
    @FXML private TextField durataMaxField;

    //Ricerca con form
    @FXML private ComboBox<String> rischioCombo;
    @FXML private TextField orizzonteField;


    @FXML private TableView<Asset> assetTable;

    @FXML private TableColumn<Asset, String> nomeCol;
    @FXML private TableColumn<Asset, String> tipoCol;
    @FXML private TableColumn<Asset, String> settoreCol;
    @FXML private TableColumn<Asset, Integer> durataCol;
    @FXML private TableColumn<Asset, String> isinCol;

    @FXML private BorderPane rootPane;


    private RicercaAssetService service;
    private ObservableList<Asset> assetObservableList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        service = new RicercaAssetService();
        assetObservableList = FXCollections.observableArrayList();
        assetTable.setItems(assetObservableList);

        //CONFIGURAZIONE TABELLA
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tipoCol.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        settoreCol.setCellValueFactory(new PropertyValueFactory<>("settore"));
        durataCol.setCellValueFactory(new PropertyValueFactory<>("durata"));
        isinCol.setCellValueFactory(new PropertyValueFactory<>("isin"));

        //COMBOBOX
        tipoCombo.getItems().addAll("Tutti", "AZIONE", "OBBLIGAZIONE", "ETF");
        tipoCombo.getSelectionModel().selectFirst();

        settoreCombo.getItems().addAll(
            "Tutti",
            "Tecnologia",
            "Finanza",
            "Sanità",
            "Governativo",
            "Privato"
        );
        settoreCombo.getSelectionModel().selectFirst();

        rischioCombo.getItems().addAll("BASSO", "MEDIO", "ALTO");
        rischioCombo.getSelectionModel().selectFirst();

        System.out.println("RicercaAssetController inizializzato");
        ricaricaTutto();
}


    //Ricerca Semplice
    @FXML
    private void handleRicerca() {
        String keyword = searchField.getText();
        if (keyword == null || keyword.isBlank()) {
            ricaricaTutto();
            return;
        }
        List<Asset> risultati = service.cercaAsset(keyword);
        aggiornaLista(risultati);
    }

    //Ricerca con filtri
    @FXML
    private void handleRicercaFiltri() {
        String tipo = tipoCombo.getValue();
        if ("Tutti".equals(tipo)) tipo = null; 

        String settore = settoreCombo.getValue();
        if ("Tutti".equals(settore)) settore = null;

        Integer min = parseInteger(durataMinField.getText());
        Integer max = parseInteger(durataMaxField.getText());

        List<Asset> risultati = service.cercaConFiltri(tipo, settore, min, max);
        aggiornaLista(risultati);
    }

    //Ricerca con form 
    @FXML
    private void handleAdvisor() {
        String rischio = rischioCombo.getValue();
        String anniStr = orizzonteField.getText();

        if (rischio == null || anniStr.isBlank()) {
            mostraErrore("Dati mancanti", "Seleziona il rischio e inserisci gli anni.");
            return;
        }

        try {
            int anni = Integer.parseInt(anniStr);
            if (anni < 0) throw new NumberFormatException();

            List<Asset> suggerimenti = service.suggerisciInvestimenti(rischio, anni);
            
            aggiornaLista(suggerimenti);

            if (suggerimenti.isEmpty()) {
                mostraInfo("Advisor", "Nessun asset corrisponde esattamente a questo profilo.");
            } else {
                mostraInfo("Consiglio Advisor", "Ecco gli asset ideali per un rischio " + rischio + " su " + anni + " anni.");
            }

        } catch (NumberFormatException e) {
            mostraErrore("Input non valido", "Inserisci un numero valido di anni (positivo).");
        }
    }

    @FXML
    private void handleReset() {
        searchField.clear();
        durataMinField.clear();
        durataMaxField.clear();
        orizzonteField.clear();
        tipoCombo.getSelectionModel().selectFirst();
        settoreCombo.getSelectionModel().selectFirst();
        ricaricaTutto();
    }

    private void ricaricaTutto() {
        assetObservableList.setAll(service.getTuttiAsset());
    }

    private void aggiornaLista(List<Asset> lista) {
        assetObservableList.setAll(lista);
    }

    private Integer parseInteger(String str) {
        if (str == null || str.isBlank()) return null;
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return null; // Se l'utente scrive lettere, ignoriamo il filtro
        }
    }

    private void mostraErrore(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void mostraInfo(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void handleVaiRicerca() {
        try {
            Parent view = FXMLLoader.load(
                 getClass().getResource("/ricerca/ricerca.fxml")
            );
            rootPane.setCenter(view);
        } catch (IOException e) {
                mostraErrore("Errore", "Impossibile aprire Ricerca Asset");
        }
    }

    @FXML
    private void handleVaiPortafogli() {
        try {
            Parent view = FXMLLoader.load(
                getClass().getResource("/portafoglio/portafoglio.fxml")
            );
            rootPane.setCenter(view);
        } catch (IOException e) {
            mostraErrore("Errore", "Impossibile tornare ai Portafogli");
        }
    }
}