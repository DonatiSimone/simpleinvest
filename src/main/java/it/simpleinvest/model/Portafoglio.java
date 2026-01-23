package it.simpleinvest.model;

// Autore: Donati Simone

// Modella un portafoglio di investimento appartenente a un utente.
// Memorizza i dati principali del portafoglio (nome, capitale e proprietario) ed è l’entità centrale del caso d’uso di gestione dei portafogli.

public class Portafoglio {

    private int id;
    private String nome;
    private double capitale;
    private int utenteId;

    // Costruttore completo (lettura dal DB)
    public Portafoglio(int id, String nome, double capitale, int utenteId) {
        this.id = id;
        this.nome = nome;
        this.capitale = capitale;
        this.utenteId = utenteId;
    }

    // Costruttore senza id (creazione)
    public Portafoglio(String nome, double capitale, int utenteId) {
        this.nome = nome;
        this.capitale = capitale;
        this.utenteId = utenteId;
    }

    // Getter e Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getCapitale() {
        return capitale;
    }

    public void setCapitale(double capitale) {
        this.capitale = capitale;
    }

    public int getUtenteId() {
        return utenteId;
    }

    public void setUtenteId(int utenteId) {
        this.utenteId = utenteId;
    }

    @Override
    public String toString() {
        return nome;
    }
}
