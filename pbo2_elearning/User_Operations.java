/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pbo2_elearning;
 import java.sql.*;

/**
 *
 * @author LENOVO
 */
public class User_Operations {


    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO user (username, password, nama_lengkap, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getNamaLengkap());
            pstmt.setString(4, user.getRole());
            pstmt.executeUpdate();
        }
    }

    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM user WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id_user"),
                                rs.getString("username"),
                                rs.getString("password"),
                                rs.getString("nama_lengkap"),
                                rs.getString("role"));
            }
        }
        return null; 
    }

    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE user SET username = ?, password = ?, nama_lengkap = ?, role = ? WHERE id_user = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getNamaLengkap());
            pstmt.setString(4, user.getRole());
            pstmt.setInt(5, user.getIdUser());
            pstmt.executeUpdate();
        }
    }

    public void deleteUser(int idUser) throws SQLException {
        String sql = "DELETE FROM user WHERE id_user = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idUser);
            pstmt.executeUpdate();
        }
    }
}

