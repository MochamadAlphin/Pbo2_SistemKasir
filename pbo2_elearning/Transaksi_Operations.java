/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pbo2_elearning;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author LENOVO
 */

public class Transaksi_Operations {


    public void addTransaksi(Transaksi transaksi) throws SQLException {
        String sql = "INSERT INTO transaksi (id_produk, tanggal_transaksi, total_harga) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, transaksi.getIdProduk());
            pstmt.setDate(2, new java.sql.Date(transaksi.getTanggalTransaksi().getTime()));
            pstmt.setDouble(3, transaksi.getTotalHarga());
            pstmt.executeUpdate();
        }
    }

    public void deleteTransaksi(int idTransaksi) throws SQLException {
        String sql = "DELETE FROM transaksi WHERE id_transaksi = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idTransaksi);
            pstmt.executeUpdate();
        }
    }

    public List<Transaksi> getAllTransaksi() throws SQLException {
        List<Transaksi> listTransaksi = new ArrayList<>();
        String sql = "SELECT * FROM transaksi";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                listTransaksi.add(new Transaksi(
                    rs.getInt("id_transaksi"),
                    rs.getInt("id_produk"),
                    rs.getDate("tanggal_transaksi"),
                    rs.getDouble("total_harga")
                ));
            }
        }
        return listTransaksi;
    }
}