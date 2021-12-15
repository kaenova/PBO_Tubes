/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kaeno
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Hello! this is just a test file for the database");

        SQLiteDB test = new SQLiteDB();
        ResultSet rs;
        try {
            rs = test.displayUser();
            while (rs.next()) {
                System.out.print(rs.getString("fname") + " | ");
                System.out.println(rs.getString("lname") + " | ");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        TestAnotherClass a = new TestAnotherClass();
        a.TestDBIsConnected();

    }
}
