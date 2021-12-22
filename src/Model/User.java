/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Database.SQLiteDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kaenova
 */
public class User {

    private int id;
    private String username, password;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public User(User a) {
        this.id = a.getId();
        this.username = a.getUsername();
        this.password = a.getPassword();
    }

    public User(String username, String password) throws SQLException {
        Connection con = SQLiteDB.getDB();
        PreparedStatement st = con.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?;");
        st.setString(1, username);
        st.setString(2, password);
        ResultSet rs = st.executeQuery();
        if (rs.next() == false) {
            // Ngecek apakah ada data?
            Utils.Logger.Warning("Tidak ditemukan User");
            throw new SQLException("Data tidak ditemukan");
        }
        this.id = rs.getInt("id");
        this.username = rs.getString("username");
        this.password = rs.getString("password");
    }

    public static void SignUp(String username, String password) throws SQLException {
        // Ini tuh SignUp
        Connection con = SQLiteDB.getDB();
        PreparedStatement st = con.prepareStatement("INSERT INTO user (username, password) values (?, ?);");
        st.setString(1, username);
        st.setString(2, password);
        st.execute();
    }

    public void TambahJumlahStok(Toko toko, Produk produk) throws SQLException {
        Connection con = Database.SQLiteDB.getDB();
        PreparedStatement st = con.prepareStatement("UPDATE produk SET stok = (? + 1) WHERE (id = ? AND id_toko = ?);");
        st.setFloat(1, produk.getStok());
        st.setInt(2, produk.getId());
        st.setInt(3, toko.getId());
        int affected = st.executeUpdate();
        if (affected == 0) {
            throw new SQLException("Gagal menambahkan jumlah stok");
        }
    }

    public void KurangJumlahStok(Toko toko, Produk produk) throws SQLException {
        Connection con = Database.SQLiteDB.getDB();
        PreparedStatement st = con.prepareStatement("UPDATE produk SET stok = (? - 1) WHERE (id = ? AND id_toko = ?);");
        st.setFloat(1, produk.getStok());
        st.setInt(2, produk.getId());
        st.setInt(3, toko.getId());
        int affected = st.executeUpdate();
        if (affected == 0) {
            throw new SQLException("Gagal menambahkan jumlah stok");
        }
    }

}
