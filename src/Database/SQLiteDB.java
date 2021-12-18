/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author kaeno
 */
public class SQLiteDB {

    private static Connection con;
    private static boolean hasData = false;

    public SQLiteDB() throws SQLException, ClassNotFoundException {
        // Always do this at the start of a application
        Utils.Logger.Info("Connecting to Database");
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:SQLiteTest.db");
        Utils.Logger.Info("Database Connected");
        if (!hasData) {
            Utils.Logger.Warning("Database not yet Initialized. Initializing Database");
            migration();
            hasData = true;
        }
        Utils.Logger.Info("Use SQLiteDB.getDB() to get the connection.");
    }

    private void migration() throws SQLException {
        Utils.Logger.Info("Starting Database Migration");
        ArrayList<String> temp = new ArrayList();
        // Tabel user
        temp.add("CREATE TABLE IF NOT EXISTS user (id INTEGER PRIMARY KEY AUTOINCREMENT, username varchar(60) NOT NULL UNIQUE, password varchar(60) NOT NULL);");
        // Tabel toko
        temp.add("CREATE TABLE IF NOT EXISTS toko (id INTEGER PRIMARY KEY AUTOINCREMENT, nama varchar(60) NOT NULL, alamat varchar(200) NOT NULL, kode varchar(20) NOT NULL, id_admin INTEGER NOT NULL, FOREIGN KEY (id_admin) REFERENCES user(id));");
        // Tabel supplier
        temp.add("CREATE TABLE IF NOT EXISTS supplier (id INTEGER PRIMARY KEY AUTOINCREMENT, nama varchar(60) NOT NULL, alamat varchar(200) NOT NULL, telepon varchar(60) NOT NULL, id_toko INTEGER NOT NULL, FOREIGN KEY (id_toko) REFERENCES toko(id));");
        // Tabel produk
        temp.add("CREATE TABLE IF NOT EXISTS produk (id INTEGER PRIMARY KEY AUTOINCREMENT, nama varchar(60) NOT NULL, satuan varchar(20) NOT NULL, stok float(10) NOT NULL, id_toko INTEGER NOT NULL, FOREIGN KEY (id_toko) REFERENCES toko(id));");
        // Tabel relasi toko-pengurus(user)
        temp.add("CREATE TABLE IF NOT EXISTS pengurus_toko_relation (id INTEGER PRIMARY KEY AUTOINCREMENT, id_toko INTEGER NOT NULL, id_pengurus INTEGER NOT NULL, FOREIGN KEY (id_toko) REFERENCES toko(id), FOREIGN KEY (id_pengurus) REFERENCES user(id));");
        for (String a : temp) {
            Statement tempState = con.createStatement();
            tempState.execute(a);
        }
        // Migration Complete
        Utils.Logger.Info("Migration Complete");
    }

    public static Connection getDB() {
        return con;
    }
}


/*
Buat Koneksi database
    Connection con = SQLiteDB.getDB();

Memperisapkan SQL Statment
    




*/
