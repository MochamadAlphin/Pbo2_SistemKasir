/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pbo2_elearning;
 import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author LENOVO
 */


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User_Operations userOps = new User_Operations();
        Produk_Operations produkOps = new Produk_Operations();
        Transaksi_Operations transaksiOps = new Transaksi_Operations();

        try {
            while (true) {
                System.out.println("1. Login");
                System.out.println("2. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (choice == 1) {
                    System.out.print("Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();

                    User user = userOps.getUserByUsername(username);
                    if (user != null && user.getPassword().equals(password)) {
                        System.out.println("Login berhasil! Anda adalah " + user.getRole());

                        if (user.getRole().equals("admin")) {
                            while (true) {
                                System.out.println("1. Kelola Produk");
                                System.out.println("2. Kelola Transaksi");
                                System.out.println("3. Logout");
                                int adminChoice = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                if (adminChoice == 1) {
                                    manageProducts(scanner, produkOps);
                                } else if (adminChoice == 2) {
                                    manageTransactions(scanner, transaksiOps);
                                } else if (adminChoice == 3) {
                                    break; // Logout
                                }
                            }
                        }
                    } else {
                        System.out.println("Login gagal! Username atau password salah.");
                    }
                } else if (choice == 2) {
                    break; // Exit
                }
            }
        } catch (SQLException e) {
            System.err.println("Kesalahan database: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void manageProducts(Scanner scanner, Produk_Operations produkOps) throws SQLException {
        while (true) {
            System.out.println("1. Tambah Produk");
            System.out.println("2. Update Produk");
            System.out.println("3. Hapus Produk");
            System.out.println("4. Lihat Produk");
            System.out.println("5. Kembali");
            int produkChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (produkChoice == 1) {
                System.out.print("Nama Produk: ");
                String namaProduk = scanner.nextLine();
                System.out.print("Stok: ");
                int stok = scanner.nextInt();
                System.out.print("Harga: ");
                double harga = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                System.out.print("Kategori: ");
                String kategori = scanner.nextLine();

                if (kategori.isEmpty()) {
                    System.out.println("Kategori tidak boleh kosong.");
                } else {
                    produkOps.addProduk(new Produk(0, namaProduk, stok, harga, kategori));
                    System.out.println("Produk berhasil ditambahkan.");
                }
            } else if (produkChoice == 2) {
                System.out.print("ID Produk yang ingin diupdate: ");
                int idProduk = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                Produk produk = produkOps.getProduk(idProduk);
                if (produk != null) {
                    System.out.print("Nama Produk Baru: ");
                    String namaProduk = scanner.nextLine();
                    System.out.print("Stok Baru: ");
                    int stok = scanner.nextInt();
                    System.out.print("Harga Baru: ");
                    double harga = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Kategori Baru: ");
                    String kategori = scanner.nextLine();

                    if (kategori.isEmpty()) {
                        System.out.println("Kategori tidak boleh kosong.");
                    } else {
                        produkOps.updateProduk(new Produk(idProduk, namaProduk, stok, harga, kategori));
                        System.out.println("Produk berhasil diupdate.");
                    }
                } else {
                    System.out.println("Produk tidak ditemukan.");
                }
            } else if (produkChoice == 3) {
                System.out.print("ID Produk yang ingin dihapus: ");
                int idProduk = scanner.nextInt();
                produkOps.deleteProduk(idProduk);
                System.out.println("Produk berhasil dihapus.");
            } else if (produkChoice == 4) {
                List<Produk> produkList = produkOps.getAllProduk();
                if (produkList.isEmpty()) {
                    System.out.println("Tidak ada produk yang tersedia.");
                } else {
                    System.out.println("Daftar Produk:");
                    for (Produk p : produkList) {
                        System.out.println("ID: " + p.getIdProduk() + ", Nama: " + p.getNamaProduk() +
                                           ", Stok: " + p.getStok() + ", Harga: " + p.getHarga() +
                                           ", Kategori: " + p.getKategori());
                    }
                }
            } else if (produkChoice == 5) {
                break; // Kembali ke menu admin
            }
        }
    }

    private static void manageTransactions(Scanner scanner, Transaksi_Operations transaksiOps) throws SQLException {
        while (true) {
            System.out.println("1. Tambah Transaksi");
            System.out.println("2. Lihat Transaksi");
            System.out.println("3. Hapus Transaksi");
            System.out.println("4. Kembali");
            int transaksiChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (transaksiChoice == 1) {
                System.out.print("ID Produk: ");
                int idProduk = scanner.nextInt();
                System.out.print("Total Harga: ");
                double totalHarga = scanner.nextDouble();
                transaksiOps.addTransaksi(new Transaksi(0, idProduk, new Date(), totalHarga));
                System.out.println("Transaksi berhasil ditambahkan.");
            } else if (transaksiChoice == 2) {
                List<Transaksi> transaksiList = transaksiOps.getAllTransaksi();
                if (transaksiList.isEmpty()) {
                    System.out.println("Tidak ada transaksi yang tersedia.");
                } else {
                    System.out.println("Daftar Transaksi:");
                    for (Transaksi t : transaksiList) {
                        System.out.println("ID: " + t.getIdTransaksi() + ", ID Produk: " + t.getIdProduk() +
                                           ", Tanggal: " + t.getTanggalTransaksi() +
                                           ", Total Harga: " + t.getTotalHarga());
                    }
                }
            } else if (transaksiChoice == 3) {
                System.out.print("ID Transaksi yang ingin dihapus: ");
                int idTransaksi = scanner.nextInt();
                // Cek apakah transaksi ada sebelum menghapus
                List<Transaksi> transaksiList = transaksiOps.getAllTransaksi();
                boolean found = false;
                for (Transaksi t : transaksiList) {
                    if (t.getIdTransaksi() == idTransaksi) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    transaksiOps.deleteTransaksi(idTransaksi);
                    System.out.println("Transaksi berhasil dihapus.");
                } else {
                    System.out.println("Transaksi tidak ditemukan.");
                }
            } else if (transaksiChoice == 4) {
                break; // Kembali ke menu admin
            }
        }
    }
}