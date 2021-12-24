package Model;

import Utils.Helper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author fanzru
 */
public class Pengurus extends User implements GetToko {
    
    public Pengurus(User a) {
        super(a);
    }

    @Override
    public Toko GetToko() throws SQLException {
        // Get toko by Pengurus ID
        Connection con = Database.SQLiteDB.getDB();
        PreparedStatement st = con.prepareStatement("SELECT 'toko'.'id', 'toko'.'nama', 'toko'.'alamat' FROM 'toko' INNER JOIN 'pengurus_toko_relation' ON 'toko'.'id'='pengurus_toko_relation'.'id_toko' WHERE 'pengurus_toko_relation'.'id_pengurus'= ?;");
        st.setInt(1, super.getId());
        ResultSet rs = st.executeQuery();
        if (rs.next() == false) {
            // Ngecek apakah ada data?
            Utils.Logger.Info("User Belum Join Toko");
            //throw new SQLException("Data tidak ditemukan");
        }
        Utils.Logger.Info("User Sudah Join Toko");
        Toko toko = new Toko(rs.getInt("id"), rs.getString("nama"),rs.getString("alamat"));
        return toko;
    }

    public Toko JoinToko(String kode_pengurus_toko) throws SQLException {
        int id_toko = -1;
        Connection con = Database.SQLiteDB.getDB();
        PreparedStatement st;
        st = con.prepareStatement("SELECT * FROM toko WHERE (kode = ?)");
        st.setString(1, kode_pengurus_toko);
        ResultSet data = st.executeQuery();
        if (!data.next()){
            throw new SQLException("Id Tidak ditemukan");
        } else {
            id_toko = data.getInt("id");
            Utils.Logger.Info(id_toko + "-----------------------------");
        }
        if (id_toko != -1 ){
            PreparedStatement st1 = con.prepareStatement("INSERT INTO pengurus_toko_relation ( id_toko, id_pengurus) "
                + "VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS);
            st1.setInt(1, id_toko);
            st1.setInt(2, super.getId());
            st1.execute();
            ResultSet rs = st.getGeneratedKeys();
            int id = rs.getInt(1);
            Toko toko = this.GetToko();
            return toko;
        } else {
            throw new SQLException("Gagal Join Toko");
        }
    }
}
