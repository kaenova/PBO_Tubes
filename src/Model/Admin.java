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
public class Admin extends User implements GetToko {

    public Admin(User a) {
        super(a);
    }

    @Override
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

    public Produk TambahProduk(String nama, String satuan, float stok, Toko toko) throws SQLException {
        Connection con = Database.SQLiteDB.getDB();

        PreparedStatement st = con.prepareStatement("INSERT INTO produk (id_toko, nama, stok, satuan) "
                + "VALUES (?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
        st.setInt(1, toko.getId());
        st.setString(2, nama);
        st.setFloat(3, stok);
        st.setString(4, satuan);
        st.execute();
        ResultSet rs = st.getGeneratedKeys();

        int id = rs.getInt(1);

        Produk produk = new Produk(id, toko.getId(), nama, satuan, stok);
        return produk;
    }

    public Produk UbahProduk(String nama, String satuan, float stok, Toko toko, Produk produk) throws SQLException {
        Connection con = Database.SQLiteDB.getDB();
        PreparedStatement st = con.prepareStatement("UPDATE produk SET nama = ?, satuan = ?, stok = ? WHERE (id = ? AND id_toko = ?);");
        st.setString(1, nama);
        st.setString(2, satuan);
        st.setFloat(3, stok);
        st.setInt(4, produk.getId());
        st.setInt(5, toko.getId());
        st.execute();
        Produk produkNew = new Produk(produk.getId(), toko.getId(), nama, satuan, stok);
        return produkNew;
    }
    
    public void HapusProduk(Toko toko, Produk produk) throws SQLException{
        Connection con = Database.SQLiteDB.getDB();
        PreparedStatement st = con.prepareStatement("DELETE FROM produk WHERE (id_toko = ? AND id = ?);");
        st.setInt(1, toko.getId());
        st.setInt(2, produk.getId());
        int affected = st.executeUpdate();
        if (affected == 0){
            throw new SQLException("Gagal menghapus produk");
        }
    }
    
    public void HapusSupplier(Toko toko, Supplier supplier) throws SQLException{
        Connection con = Database.SQLiteDB.getDB();
        PreparedStatement st = con.prepareStatement("DELETE FROM supplier WHERE (id_toko = ? AND id = ?);");
        st.setInt(1, toko.getId());
        st.setInt(2, supplier.getId());
        int affected = st.executeUpdate();
        if (affected == 0){
            throw new SQLException("Gagal menghapus supplier");
        }
    }
<<<<<<< HEAD
    
    public Supplier InputSupplier(Toko toko, String nama, String alamat, String telepon) throws SQLException{
        Connection con = Database.SQLiteDB.getDB();
        
        PreparedStatement st = con.prepareStatement("INSERT INTO supplier (id_toko, nama, alamat, telepon) "
                + "VALUES (?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
        st.setInt(1, toko.getId());
        st.setString(2, nama);
        st.setString(3, alamat);
        st.setString(4, telepon);
        st.execute();
        ResultSet rs = st.getGeneratedKeys();
        
        int id = rs.getInt(1);

        Supplier supplier = new Supplier(id, toko.getId(), nama, alamat, telepon);
        return supplier;
    }
=======
>>>>>>> 638282b5e58a4baef21834be92b0b298e735303b
}
