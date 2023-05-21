package com.ujian4.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ujian4.model.Komik;

public class KomikDAO {
    private static final String DB_URL = "jdbc:postgresql://{host}[:{port}]/[{database}]";
    private static final String USERNAME = "randomize";
    private static final String PASSWORD = "";

    private Connection connection;

    public KomikDAO() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void simpanKomik(Komik komik) {
        String sql = "INSERT INTO komik (judul, penulis, stok) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, komik.getJudul());
            statement.setString(2, komik.getPenulis());
            statement.setInt(3, komik.getStok());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Komik> ambilSemuaKomik() {
        List<Komik> daftarKomik = new ArrayList<>();
        String sql = "SELECT * FROM komik";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String judul = resultSet.getString("judul");
                String penulis = resultSet.getString("penulis");
                int stok = resultSet.getInt("stok");

                Komik komik = new Komik(id, judul, penulis, stok);
                daftarKomik.add(komik);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return daftarKomik;
    }

    public void kurangiStokKomik(int komikId, int jumlah) {
        String sql = "UPDATE komik SET stok = stok - ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, jumlah);
            statement.setInt(2, komikId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Komik ambilKomikBerdasarkanId(int komikId) {
        String sql = "SELECT * FROM komik WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, komikId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String judul = resultSet.getString("judul");
                    String penulis = resultSet.getString("penulis");
                    int stok = resultSet.getInt("stok");

                    return new Komik(id, judul, penulis, stok);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}

