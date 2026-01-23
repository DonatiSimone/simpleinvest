package it.simpleinvest.db;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Autore: Condiviso

// Gestisce la connessione al database SQLite
// applicando il pattern Singleton.

public class DBManager {

    private static DBManager instance;
    private Connection connection;


    //Inserisco path assoluto per db perchè classe di test non la trova
    //Costruttore privato (Singleton)
    private DBManager() {
        try {
            String dbName = System.getProperty(
                "simpleinvest.db.name",
                "simpleinvest.db"   // default produzione
            );

            String dbPath = Paths.get("db", dbName)
                    .toAbsolutePath()
                    .toString();

            //System.out.println("DB IN USO: " + dbPath);
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Restituisce l'unica istanza di DBManager
    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    //     // SOLO PER I TEST
    // public static DBManager getTestInstance() {
    //     return new DBManager("simpleinvest-test.db");
    // }

    //Restituisce la connessione al database
    public Connection getConnection() {
        return connection;
    }
}
