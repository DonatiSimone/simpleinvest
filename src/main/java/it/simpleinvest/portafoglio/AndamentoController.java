package it.simpleinvest.portafoglio;

import java.util.List;

import it.simpleinvest.model.Portafoglio;
import it.simpleinvest.model.ValoreStorico;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

// Autore: Donati Simone

// Controller JavaFX dedicato alla visualizzazione grafica dell’andamento di un portafoglio.
// Riceve i dati del portafoglio e della serie storica e li presenta tramite un grafico e indicatori riassuntivi.

public class AndamentoController {

    @FXML private LineChart<Number, Number> lineChart;

    @FXML private Label titoloLabel;
    @FXML private Label inizialeLabel;
    @FXML private Label attualeLabel;
    @FXML private Label percentualeLabel;

    public void setPortafoglio(Portafoglio p,
                               List<ValoreStorico> storico) {

        titoloLabel.setText("Andamento portafoglio: " + p.getNome());

        XYChart.Series<Number, Number> serie = new XYChart.Series<>();

        for (ValoreStorico v : storico) {
            double base = storico.get(0).getValore();

            double indice = (v.getValore() / base) * 100;

            serie.getData().add(
                new XYChart.Data<>(v.getGiorno(), v.getValore())
            );
        }

        lineChart.getData().clear();
        lineChart.getData().add(serie);

        double iniziale = storico.get(0).getValore();
        double attuale = storico.get(storico.size() - 1).getValore();
        double perc = ((attuale - iniziale) / iniziale) * 100;
        double min = storico.stream()
            .mapToDouble(ValoreStorico::getValore)
            .min().orElse(iniziale);

        double max = storico.stream()
            .mapToDouble(ValoreStorico::getValore)
            .max().orElse(iniziale);

        // calcolo escursione massima rispetto all'iniziale
        double delta = Math.max(
            Math.abs(max - iniziale),
            Math.abs(iniziale - min)
        );

        // margine visivo (10%)
        delta *= 1.1;

        NumberAxis yAxis = (NumberAxis) lineChart.getYAxis();

        yAxis.setAutoRanging(true);
        double range = max - min;

        // margine visivo extra (10–20%)
        double padding = range * 0.15;

        yAxis.setLowerBound(min - padding);
        yAxis.setUpperBound(max + padding);

        // 🔑 controllo densità griglia
        yAxis.setTickUnit(delta / 4);


        inizialeLabel.setText(
            String.format("Valore iniziale: € %.2f", iniziale)
        );

        attualeLabel.setText(
            String.format("Valore attuale: € %.2f", attuale)
        );

        percentualeLabel.setText(
            String.format("Variazione: %+.2f%%", perc)
        );

        // Colore percentuale (verde / rosso)
        if (perc >= 0) {
            percentualeLabel.setStyle("-fx-text-fill: green;");
        } else {
            percentualeLabel.setStyle("-fx-text-fill: red;");
        }
    }
}
