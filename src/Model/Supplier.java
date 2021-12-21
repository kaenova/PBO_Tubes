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
public class Supplier {
    final private int id, idToko;
    private String nama, alamat, telepon;

    public Supplier(int id, int idToko, String nama, String alamat, String telepon){
        this.id = id;
        this.idToko = idToko;
        this.nama = nama;
        this.alamat = alamat;
        this.telepon = telepon;
    }

    public int getId() {
        return id;
    }

    public int getIdToko() {
        return idToko;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getTelepon() {
        return telepon;
    }
    
}
