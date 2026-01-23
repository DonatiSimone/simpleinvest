package it.simpleinvest.portafoglio;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.simpleinvest.model.Portafoglio;
import it.simpleinvest.model.Utente;
import it.simpleinvest.model.ValoreStorico;
import it.simpleinvest.service.GestionePortafoglioService;
import it.simpleinvest.util.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// Autore: Donati Simone

// Controller JavaFX responsabile dell’interazione utente per la gestione dei portafogli.
// Gestisce la creazione, eliminazione, rinomina e selezione dei portafogli, delegando la logica applicativa al service.

public class PortafoglioController implements Initializable {

    //Componenti FXML
    @FXML
    private ListView<Portafoglio> portafogliListView;

    @FXML
    private TextField nomeField;

    @FXML
    private TextField capitaleField;

    @FXML
    private Label usernameLabel;



    //Service
    private GestionePortafoglioService service;

    //Dati
    private ObservableList<Portafoglio> portafogliObservable;

    //Inizializzazione
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        portafogliListView.requestFocus();


        usernameLabel.setText(
            UserSession.getInstance().getUtenteCorrente().getUsername()
        );

            service = new GestionePortafoglioService();
            portafogliObservable = FXCollections.observableArrayList();
            portafogliListView.setItems(portafogliObservable);


            caricaPortafogli();
    }


    //Azioni

    @FXML
    private void handleCreaPortafoglio() {
        String nome = nomeField.getText();
        String capitaleText = capitaleField.getText();

        try {
            double capitale = Double.parseDouble(capitaleText);

            Utente utente = UserSession.getInstance().getUtenteCorrente();
            service.creaPortafoglio(nome, capitale, utente.getId());

            nomeField.clear();
            capitaleField.clear();

            caricaPortafogli();

        } catch (NumberFormatException e) {
            mostraErrore("Capitale non valido", "Inserire un numero valido.");
        } catch (IllegalArgumentException e) {
            mostraErrore("Errore di input", e.getMessage());
        }
    }

    @FXML
    private void handleEliminaPortafoglio() {
        Portafoglio selezionato = portafogliListView.getSelectionModel().getSelectedItem();

        if (selezionato == null) {
            mostraErrore("Nessuna selezione", "Seleziona un portafoglio da eliminare.");
            return;
        }

        service.eliminaPortafoglio(selezionato.getId());
        caricaPortafogli();
    }

    @FXML
    private void handleRinominaPortafoglio() {
    Portafoglio selezionato = portafogliListView
            .getSelectionModel()
            .getSelectedItem();

    if (selezionato == null) {
        mostraErrore("Nessuna selezione",
                "Seleziona un portafoglio da rinominare.");
        return;
    }

    TextInputDialog dialog = new TextInputDialog(selezionato.getNome());
    dialog.setTitle("Rinomina portafoglio");
    dialog.setHeaderText("Inserisci il nuovo nome");
    dialog.setContentText("Nome:");

    dialog.showAndWait().ifPresent(nuovoNome -> {
        try {
            service.rinominaPortafoglio(selezionato.getId(), nuovoNome);
            caricaPortafogli();
        } catch (IllegalArgumentException e) {
            mostraErrore("Errore di input", e.getMessage());
            }
        });
    }

    @FXML
    private void handleAnalisiAndamento() {

        Portafoglio selezionato =
            portafogliListView.getSelectionModel().getSelectedItem();

        if (selezionato == null) {
            mostraErrore("Nessuna selezione",
                "Seleziona un portafoglio da analizzare.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/portafoglio/andamento.fxml")
            );
            Parent root = loader.load();

            AndamentoController controller = loader.getController();

            List<ValoreStorico> storico =
                service.getAndamentoPortafoglio(selezionato);

            controller.setPortafoglio(selezionato, storico);

            Stage stage = new Stage();
            stage.setTitle("Analisi andamento");
            stage.setScene(new Scene(root, 800, 550));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
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
    private BorderPane rootPane;

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



    //Metodi di supporto

    private void caricaPortafogli() {
        Utente utente = UserSession.getInstance().getUtenteCorrente();
        List<Portafoglio> lista = service.getPortafogliUtente(utente.getId());

        portafogliObservable.setAll(lista);
    }

    private void mostraErrore(String titolo, String messaggio) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText(titolo);
        alert.setContentText(messaggio);
        alert.showAndWait();
    }    
}
