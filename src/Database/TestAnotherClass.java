/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kaeno
 */
public class TestAnotherClass {

    public void TestDBIsConnected() {
        SQLiteDB a = new SQLiteDB();
        if (Objects.isNull(a)) {
            System.out.println("Objek masih kosong, belum disediakan || Database belum diinisialisasikan");
        } else {
            System.out.println("Sudah terkoneksi");
        }

        Connection test = a.con;

        try {
            Statement state = test.createStatement();
            state.execute("CREATE TABLE IF NOT EXISTS anjay(id INTEGER PRIMARY KEY AUTOINCREMENT, fname varchar(60), lname varchar(60));");

        } catch (SQLException ex) {
            Logger.getLogger(TestAnotherClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Ditemukan bahwa kita hanya butuh menginisialisasikan DB sekali saja, terus tinggal pakai kelas kita bisa get Connectionnya dan gunakan Connection itu untuk di modelnya masing2;
    }
}
