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
import java.util.ArrayList;

/**
 *
 * @author kaeno
 */
public class Toko {

    private int id, idAdmin;
    private String nama, alamat, kode;
    
    public Toko(int id, String nama, String alamat) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
    }
    
    public Toko(int id, int idAdmin, String nama, String alamat, String kode) {
        this.id = id;
        this.idAdmin = idAdmin;
        this.nama = nama;
        this.alamat = alamat;
        this.kode = kode;
    }

    public int getId() {
        return id;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getKode() {
        return kode;
    }

    public ArrayList<Produk> GetProduk() throws SQLException {
        ArrayList<Produk> arr = new ArrayList();
        Connection con = SQLiteDB.getDB();
        PreparedStatement st = con.
                prepareStatement("SELECT * FROM produk WHERE id_toko = ?");
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            arr.add(new Produk(rs.getInt("id"), rs.getInt("id_toko"),
                    rs.getString("nama"), rs.getString("satuan"),
                    rs.getFloat("stok")));
        }
        return arr;
    }

    public ArrayList<Supplier> GetSupplier() throws SQLException {
        ArrayList<Supplier> arr = new ArrayList();
        Connection con = SQLiteDB.getDB();
        PreparedStatement st = con.
                prepareStatement("SELECT * FROM supplier WHERE id_toko = ?");
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            arr.add(new Supplier(rs.getInt("id"), rs.getInt("id_toko"),
                    rs.getString("nama"), rs.getString("alamat"),
                    rs.getString("telepon")));
        }
        return arr;
    }
}
