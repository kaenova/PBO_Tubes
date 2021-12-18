/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author kaeno
 */
public class Toko {
    private final int id, idAdmin;
    private String nama, alamat, kode;
    
    public Toko(int id, int idAdmin, String nama, String alamat, String kode){
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
        
}
