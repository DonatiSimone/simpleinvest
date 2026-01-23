package it.simpleinvest.model;

// Autore: Gabriele Levati

// Modella un Asset finanziario che
// Rappresenta uno strumento di investimento (azione, obbligazione o ETF)
// e contiene tutte le informazioni principali necessarie alla gestione
// e alla visualizzazione degli asset, come nome, tipo, settore, durata
// e codice ISIN.

public class Asset {

    private Integer id; //Integer perché può essere null
    private String nome;
    private String tipo;      
    private String settore;
    private Integer durata; // Integer perché può essere null
    private Boolean isStorico;
    private String isin;

    public Asset() { // Costruttore vuoto per valori di default
    }

   
    public Asset(Integer id, String nome, String tipo, String settore, Integer durata, Boolean isStorico, String isin) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.settore = settore;
        this.durata = durata;
        this.isStorico = isStorico;
        this.isin = isin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSettore() {
        return settore;
    }

    public void setSettore(String settore) {
        this.settore = settore;
    }

    public Integer getDurata() {
        return durata;
    }

    public void setDurata(Integer durata) {
        this.durata = durata;
    }

    public Boolean getIsStorico() {
        return isStorico;
    }

    public void setIsStorico(Boolean isStorico) {
        this.isStorico = isStorico;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                ", settore='" + settore + '\'' +
                ", durata=" + durata +
                ", isStorico=" + isStorico +
                ", isin='" + isin + '\'' +
                '}';
    }
}