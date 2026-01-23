package it.simpleinvest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import it.simpleinvest.db.DBManager;
import it.simpleinvest.model.Asset;

// Autore: Gabriele Levati

// Classe DAO responsabile dell’accesso ai dati relativi agli Asset.
// Incapsula tutte le operazioni di persistenza e interrogazione del database SQLite,
// fornendo metodi per la creazione, la ricerca semplice, la ricerca con filtri avanzati,
// il recupero completo e l’eliminazione degli asset.

public class AssetDAO {

    private final Connection connection;

    public AssetDAO() {
        this.connection = DBManager.getInstance().getConnection();
    }


    //Ricerca semplice
    public List<Asset> searchAssets(String keyword) {
        List<Asset> assets = new ArrayList<>();
        String sql = "SELECT * FROM asset WHERE nome LIKE ? OR isin LIKE ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Gestione manuale del NULL per la durata
                Integer durata = rs.getInt("durata");
                if (rs.wasNull()) durata = null;

                assets.add(new Asset(
                    rs.getInt("id"), rs.getString("nome"), rs.getString("tipo"),
                    rs.getString("settore"), durata, rs.getBoolean("is_storico"),
                    rs.getString("isin")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assets;
    }
   
    //Ricerca avanzata
    public List<Asset> findWithFilters(String tipo, String settore, Integer durataMin, Integer durataMax) {
        List<Asset> assets = new ArrayList<>();
        
        StringBuilder sql = new StringBuilder("SELECT * FROM asset WHERE 1=1");
        List<Object> params = new ArrayList<>();

        // Costruzione la query personalizzata
        if (tipo != null && !tipo.isBlank()) {
            sql.append(" AND tipo = ?");
            params.add(tipo);
        }
        if (settore != null && !settore.isBlank()) {
            sql.append(" AND settore = ?");
            params.add(settore);
        }
        if (durataMin != null) {
            sql.append(" AND durata >= ?");
            params.add(durataMin);
        }
        if (durataMax != null) {
            sql.append(" AND durata <= ?");
            params.add(durataMax);
        }

        try (PreparedStatement stmt = connection.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Integer durata = rs.getInt("durata");
                if (rs.wasNull()) durata = null;

                assets.add(new Asset(
                    rs.getInt("id"), rs.getString("nome"), rs.getString("tipo"),
                    rs.getString("settore"), durata, rs.getBoolean("is_storico"),
                    rs.getString("isin")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assets;
    }

    // creazione di un nuovo asset per admin
    public void create(Asset asset) {
        String sql = "INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, asset.getNome());
            stmt.setString(2, asset.getTipo());
            stmt.setString(3, asset.getSettore());

            if (asset.getDurata() != null) {
                stmt.setInt(4, asset.getDurata());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }

            stmt.setBoolean(5, asset.getIsStorico());
            stmt.setString(6, asset.getIsin()); 
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // tutti gli asset del DB
    public List<Asset> findAll() {
        List<Asset> assets = new ArrayList<>();
        String sql = "SELECT * FROM asset";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Integer durata = rs.getInt("durata");
                if (rs.wasNull()) durata = null;

                assets.add(new Asset(
                    rs.getInt("id"), rs.getString("nome"), rs.getString("tipo"),
                    rs.getString("settore"), durata, rs.getBoolean("is_storico"),
                    rs.getString("isin")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assets;
    }

    //eliminazione di un asset per admin
    public void delete(int assetId) {
        String sql = "DELETE FROM asset WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, assetId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}