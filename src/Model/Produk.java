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
public class Produk {
    final private int id, idToko;
    private String nama, satuan;
    private float stok;

    public Produk(int id, int idToko, String nama, String satuan, float stok){
        this.id = id;
        this.idToko = idToko;
        this.nama= nama;
        this.satuan = satuan;
        this.stok = stok;
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

    public String getSatuan() {
        return satuan;
    }

    public float getStok() {
        return stok;
    }
    
    
}
