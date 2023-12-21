package com.pbo3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class DataPelanggan {
    public static List<Pelanggan> getAllPelanggan() {
        List<Pelanggan> pelangganList = new ArrayList<>();

        try (Connection connection = Conn.connect()) {
            String query = "SELECT * FROM laundry_orders";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String nama = resultSet.getString("nama");
                    String alamat = resultSet.getString("alamat");
                    String jenisCucian = resultSet.getString("jenis_cucian");
                    double berat = resultSet.getDouble("berat");
                    double biaya = resultSet.getDouble("biaya");
                    Date tanggalPesan = resultSet.getDate("tanggal_pesan");
                    Date tanggalSelesai = resultSet.getDate("tanggal_selesai");

                    Pelanggan pelanggan = new Pelanggan(nama, alamat, jenisCucian, berat, biaya, tanggalPesan, tanggalSelesai);
                    pelangganList.add(pelanggan);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return pelangganList;
    }

    public static void updatePelanggan(Pelanggan pelanggan) {
        try (Connection connection = Conn.connect()) {
            String query = "UPDATE laundry_orders SET alamat=?, jenis_cucian=?, berat=?, biaya=?, tanggal_pesan=?, tanggal_selesai=? WHERE nama=?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, pelanggan.getAlamat());
                preparedStatement.setString(2, pelanggan.getJenisCucian());
                preparedStatement.setDouble(3, pelanggan.getBeratPakaian());
                preparedStatement.setDouble(4, pelanggan.getBiaya());
                preparedStatement.setDate(5, new java.sql.Date(pelanggan.getTanggalPesan().getTime()));
                preparedStatement.setDate(6, new java.sql.Date(pelanggan.getTanggalSelesai().getTime()));
                preparedStatement.setString(7, pelanggan.getNama());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void deletePelanggan(String namaPelanggan) {
        try (Connection connection = Conn.connect()) {
            String query = "DELETE FROM laundry_orders WHERE nama=?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, namaPelanggan);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

