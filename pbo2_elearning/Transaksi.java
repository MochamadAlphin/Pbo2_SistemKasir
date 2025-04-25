/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pbo2_elearning;
  import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class Transaksi {

    private int idTransaksi;
    private int idProduk;
    private Date tanggalTransaksi;
    private double totalHarga;

    public Transaksi(int idTransaksi, int idProduk, Date tanggalTransaksi, double totalHarga) {
        this.idTransaksi = idTransaksi;
        this.idProduk = idProduk;
        this.tanggalTransaksi = tanggalTransaksi;
        this.totalHarga = totalHarga;
    }

    public int getIdTransaksi() { return idTransaksi; }
    public int getIdProduk() { return idProduk; }
    public Date getTanggalTransaksi() { return tanggalTransaksi; }
    public double getTotalHarga() { return totalHarga; }
}