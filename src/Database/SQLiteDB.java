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
        // Tabel A
        temp.add("CREATE TABLE IF NOT EXISTS user (id INTEGER PRIMARY KEY AUTOINCREMENT, username varchar(60) UNIQUE, password varchar(60));");
        // Tabel B
        temp.add("CREATE TABLE IF NOT EXISTS supplier (id INTEGER PRIMARY KEY AUTOINCREMENT, nama varchar(60), alamat varchar(200), telepon varchar(60));");
        // Tabel C
//        temp.add("CREATE TABLE IF NOT EXISTS ...");
        // Tabel D
//        temp.add("CREATE TABLE IF NOT EXISTS ...");

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
