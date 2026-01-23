package it.simpleinvest;

import it.simpleinvest.model.Utente;
import it.simpleinvest.util.UserSession;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Autore: Condiviso

// Classe principale dell’applicazione.
// Estende Application e rappresenta il punto di ingresso
// dell’applicazione JavaFX.
//
// Si occupa dell’inizializzazione dell’ambiente grafico,
// del caricamento del layout principale tramite FXML
// e dell’avvio della sessione utente iniziale (utente demo).

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        Utente demo = new Utente(1, "demo");
        UserSession.getInstance().setUtenteCorrente(demo);

        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/layout/mainLayout.fxml")
        );

        Scene scene = new Scene(loader.load());

        stage.setTitle("SimpleInvest");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }
}
