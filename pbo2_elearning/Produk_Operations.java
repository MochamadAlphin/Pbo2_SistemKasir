/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pbo2_elearning;
    import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author LENOVO
 */
public class Produk_Operations {
  

    public void addProduk(Produk produk) throws SQLException {
        String sql = "INSERT INTO produk (nama_produk, stok, harga, kategori) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, produk.getNamaProduk());
            pstmt.setInt(2, produk.getStok());
            pstmt.setDouble(3, produk.getHarga());
            pstmt.setString(4, produk.getKategori());
            pstmt.executeUpdate();
        }
    }

    public void updateProduk(Produk produk) throws SQLException {
        String sql = "UPDATE produk SET nama_produk = ?, stok = ?, harga = ?, kategori = ? WHERE id_produk = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, produk.getNamaProduk());
            pstmt.setInt(2, produk.getStok());
            pstmt.setDouble(3, produk.getHarga());
            pstmt.setString(4, produk.getKategori());
            pstmt.setInt(5, produk.getIdProduk());
            pstmt.executeUpdate();
        }
    }

    public void deleteProduk(int idProduk) throws SQLException {
        String sql = "DELETE FROM produk WHERE id_produk = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idProduk);
            pstmt.executeUpdate();
        }
    }

    public Produk getProduk(int idProduk) throws SQLException {
        String sql = "SELECT * FROM produk WHERE id_produk = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idProduk);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Produk(rs.getInt("id_produk"),
                                  rs.getString("nama_produk"),
                                  rs.getInt("stok"),
                                  rs.getDouble("harga"),
                                  rs.getString("kategori"));
            }
        }
        return null; 
    }

    public List<Produk> getAllProduk() throws SQLException {
        List<Produk> listProduk = new ArrayList<>();
        String sql = "SELECT * FROM produk";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                listProduk.add(new Produk(rs.getInt("id_produk"),
                                          rs.getString("nama_produk"),
                                          rs.getInt("stok"),
                                          rs.getDouble("harga"),
                                          rs.getString("kategori")));
            }
        }
        return listProduk;
    }
}
