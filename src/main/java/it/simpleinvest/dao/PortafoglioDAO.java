package it.simpleinvest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.simpleinvest.db.DBManager;
import it.simpleinvest.model.Portafoglio;

// Autore: Donati Simone

// Gestisce l’accesso ai dati persistenti dei portafogli.
// Si occupa delle operazioni CRUD sui portafogli interagendo direttamente con il database tramite JDBC.

public class PortafoglioDAO {

    private final Connection connection;

    //costruttore
    public PortafoglioDAO() {
        this.connection = DBManager.getInstance().getConnection();
    }
    
    //metodi DAO

    //trova portafoglio
    public List<Portafoglio> findByUtente(int utenteId) {
        List<Portafoglio> portafogli = new ArrayList<>();

        String sql = "SELECT id, nome, capitale, utente_id FROM portafoglio WHERE utente_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, utenteId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Portafoglio p = new Portafoglio(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("capitale"),
                        rs.getInt("utente_id")
                );
                portafogli.add(p);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return portafogli;
    }


    //crea portafoglio
    public void create(Portafoglio portafoglio) {
        String sql = "INSERT INTO portafoglio (nome, capitale, utente_id) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, portafoglio.getNome());
            stmt.setDouble(2, portafoglio.getCapitale());
            stmt.setInt(3, portafoglio.getUtenteId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //rinomina portafoglio
    public void rename(int portafoglioId, String nuovoNome) {
        String sql = "UPDATE portafoglio SET nome = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nuovoNome);
            stmt.setInt(2, portafoglioId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //elimina portafoglio
    public void delete(int portafoglioId) {
        String sql = "DELETE FROM portafoglio WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, portafoglioId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


