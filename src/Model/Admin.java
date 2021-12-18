/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Utils.Helper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author kaeno
 */
public class Admin extends User {

    public Admin(User a) {
        super(a);
    }

    public Toko GetToko() throws SQLException {
        // Get toko by Admin ID
        Connection con = Database.SQLiteDB.getDB();
        PreparedStatement st = con.prepareStatement("SELECT * FROM toko WHERE (id_admin = ?);");
        st.setInt(1, super.getId());

        ResultSet rs = st.executeQuery();
        if (rs.next() == false) {
            // Ngecek apakah ada data?
            Utils.Logger.Warning("Tidak ditemukan User");
            throw new SQLException("Data tidak ditemukan");
        }
        
        Toko toko = new Toko(rs.getInt("id"), super.getId(), rs.getString("nama"),
                rs.getString("alamat"), rs.getString("kode"));
        return toko;
    }

    public Toko BuatToko(String nama, String alamat) throws SQLException {
        // Generate Kode
        String kode = Helper.RandomString20();
        // Ambil id user jadikan id admin
        int idAdmin = super.getId();
        Connection con = Database.SQLiteDB.getDB();
        PreparedStatement st = con.prepareStatement("INSERT INTO toko (nama, alamat, kode, id_admin) "
                + "VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
        st.setString(1, nama);
        st.setString(2, alamat);
        st.setString(3, kode);
        st.setInt(4, idAdmin);
        st.execute();
        ResultSet rs = st.getGeneratedKeys();

        int id = rs.getInt(1);
        Toko toko = new Toko(id, idAdmin, nama, alamat, kode);
        return toko;
    }
}
