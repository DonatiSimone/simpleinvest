package it.simpleinvest.model;

// Autore: Donati Simone

// Rappresenta un singolo valore della serie storica di un portafoglio in un determinato giorno.
// È utilizzato per costruire l’andamento temporale del capitale e per la visualizzazione grafica.

public class ValoreStorico {
    private final int giorno;
    private final double valore;

    public ValoreStorico(int giorno, double valore) {
        this.giorno = giorno;
        this.valore = valore;
    }

    public int getGiorno() { return giorno; }
    public double getValore() { return valore; }
}
