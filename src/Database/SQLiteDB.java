/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author kaeno
 */
public class SQLiteDB {

    public static Connection con;
    private static boolean hasData = false;

    public ResultSet displayUser() throws ClassNotFoundException, SQLException {
        if (con == null) {
            getConnection();
        }
        Statement state = con.createStatement();
        ResultSet res = state.executeQuery("Select fname, lname FROM user");
        return res;
    }

    private void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:SQLiteTest.db");
        System.out.println("Connected to Database");
        initialize();
    }

    private void initialize() throws SQLException {

        // Preparing table
        if (!hasData) {
            Statement state0 = con.createStatement();
            state0.execute("CREATE TABLE IF NOT EXISTS user(id INTEGER PRIMARY KEY AUTOINCREMENT, fname varchar(60), lname varchar(60));");
            System.out.println("Creating Dummy Data");
            // Coba masukkin data
            PreparedStatement prep = con.prepareStatement("INSERT INTO user(fname, lname) values(?,?);");
            prep.setString(1, "Ini"); // Tanda tanya ke satu
            prep.setString(2, "Satu Field"); // Tanda tanya ke dua
            prep.execute();

            PreparedStatement prep2 = con.prepareStatement("INSERT INTO user(fname, lname) values(?,?);");
            prep2.setString(1, "Field"); // Tanda tanya ke satu
            prep2.setString(2, "Ke Dua"); // Tanda tanya ke dua
            prep2.execute();
        }

    }

    public void addUser(String firstname, String lastName) throws ClassNotFoundException, SQLException {
        if (con == null) {
            getConnection();
        }

        PreparedStatement prep = con.prepareStatement("INSERT INTO user values(?,?,?);");
        prep.setString(2, firstname); // Tanda tanya ke dua
        prep.setString(3, lastName); // Tanda tanya ke tiga
        prep.execute();
    }
    
    public Connection getDB() {
        return con;
    }
}
