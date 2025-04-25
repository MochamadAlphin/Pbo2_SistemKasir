/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pbo2_elearning;

/**
 *
 * @author LENOVO
 */
public class Produk {

    private int idProduk;
    private String namaProduk;
    private int stok;
    private double harga;
    private String kategori;

    public Produk(int idProduk, String namaProduk, int stok, double harga, String kategori) {
        this.idProduk = idProduk;
        this.namaProduk = namaProduk;
        this.stok = stok;
        this.harga = harga;
        this.kategori = kategori;
    }

    // Getters
    public int getIdProduk() { return idProduk; }
    public String getNamaProduk() { return namaProduk; }
    public int getStok() { return stok; }
    public double getHarga() { return harga; }
    public String getKategori() { return kategori; }
}