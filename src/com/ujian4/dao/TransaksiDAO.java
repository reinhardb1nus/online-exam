package com.ujian4.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ujian4.model.Transaksi;

public class TransaksiDAO {
    private static final String DB_URL = "jdbc:postgresql://{host}[:{port}]/[{database}]";
    private static final String USERNAME = "randomize";
    private static final String PASSWORD = "";

    private Connection connection;

    public TransaksiDAO() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void simpanTransaksi(Transaksi transaksi) {
        String sql = "INSERT INTO transaksi (komik_id, jumlah_sewa, total_biaya) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, transaksi.getKomikId());
            statement.setInt(2, transaksi.getJumlahSewa());
            statement.setInt(3, transaksi.getTotalBiaya());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Transaksi> ambilSemuaTransaksi() {
        List<Transaksi> daftarTransaksi = new ArrayList<>();
        String sql = "SELECT * FROM transaksi";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int komikId = resultSet.getInt("komik_id");
                int jumlahSewa = resultSet.getInt("jumlah_sewa");
                int totalBiaya = resultSet.getInt("total_biaya");

                Transaksi transaksi = new Transaksi(id, komikId, jumlahSewa, totalBiaya);
                daftarTransaksi.add(transaksi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return daftarTransaksi;
    }
}
