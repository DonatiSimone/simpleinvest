package it.simpleinvest.layout;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;

// Autore: Condiviso

// Controller del layout principale dell’applicazione.
// Gestisce la navigazione tra le viste principali mantenendo persistente la struttura dell’interfaccia grafica.

public class MainLayoutController {

    @FXML private BorderPane rootPane;

    @FXML private ToggleButton btnPortafogli;
    @FXML private ToggleButton btnRicerca;


    @FXML
    public void initialize() {
        btnPortafogli.setSelected(true);
        vaiPortafogli();
    }

    @FXML
    private void vaiPortafogli() {
        seleziona(btnPortafogli);
        caricaCentro("/portafoglio/portafoglio.fxml");
    }

    @FXML
    private void vaiRicerca() {
        seleziona(btnRicerca);
        caricaCentro("/ricerca/ricerca.fxml");
    }

    private void seleziona(ToggleButton btn) {
        btn.setSelected(true);
    }
    private void caricaCentro(String fxml) {
        try {
            Parent view = FXMLLoader.load(getClass().getResource(fxml));
            rootPane.setCenter(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
