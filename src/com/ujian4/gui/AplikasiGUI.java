package com.ujian4.gui;

import javax.swing.*;

import com.ujian4.dao.KomikDAO;
import com.ujian4.dao.TransaksiDAO;
import com.ujian4.model.Komik;
import com.ujian4.model.Transaksi;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AplikasiGUI extends JFrame implements ActionListener {
    private JComboBox<String> komikComboBox;
    private JTextField jumlahField;
    private JButton sewaButton;
    private JLabel totalBiayaLabel;

    private KomikDAO komikDAO;
    private TransaksiDAO transaksiDAO;

    public AplikasiGUI() {
        komikDAO = new KomikDAO();
        transaksiDAO = new TransaksiDAO();

        setTitle("Aplikasi Penyewaan Komik");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JLabel komikLabel = new JLabel("Pilih Komik:");
        add(komikLabel);

        komikComboBox = new JComboBox<>();
        add(komikComboBox);

        JLabel jumlahLabel = new JLabel("Jumlah Sewa:");
        add(jumlahLabel);

        jumlahField = new JTextField(10);
        add(jumlahField);

        sewaButton = new JButton("Sewa");
        sewaButton.addActionListener(this);
        add(sewaButton);

        totalBiayaLabel = new JLabel("");
        add(totalBiayaLabel);

        updateKomikComboBox();
    }

    private void updateKomikComboBox() {
        komikComboBox.removeAllItems();

        List<Komik> daftarKomik = komikDAO.ambilSemuaKomik();
        for (Komik komik : daftarKomik) {
            komikComboBox.addItem(komik.getJudul());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sewaButton) {
            int selectedKomikIndex = komikComboBox.getSelectedIndex();
            int komikId = selectedKomikIndex + 1; // ID komik dihitung dari 1
            int jumlahSewa = Integer.parseInt(jumlahField.getText());

            Komik komik = komikDAO.ambilKomikBerdasarkanId(komikId);
            if (komik == null) {
                JOptionPane.showMessageDialog(this, "Komik dengan ID tersebut tidak ditemukan.");
                return;
            }

            if (jumlahSewa > komik.getStok()) {
                JOptionPane.showMessageDialog(this, "Jumlah komik yang diminta melebihi stok yang tersedia.");
                return;
            }

            int totalBiaya = jumlahSewa * 10000;

            int confirmResult = JOptionPane.showConfirmDialog(this, "Total Biaya: Rp " + totalBiaya +
                    "\nApakah Anda ingin menyimpan transaksi?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirmResult == JOptionPane.YES_OPTION) {
                Transaksi transaksi = new Transaksi(0, komikId, jumlahSewa, totalBiaya);
                transaksiDAO.simpanTransaksi(transaksi);

                komikDAO.kurangiStokKomik(komikId, jumlahSewa);

                JOptionPane.showMessageDialog(this, "Transaksi berhasil disimpan.");
            } else {
                JOptionPane.showMessageDialog(this, "Transaksi dibatalkan.");
            }

            updateKomikComboBox();
            jumlahField.setText("");
            totalBiayaLabel.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AplikasiGUI aplikasi = new AplikasiGUI();
                aplikasi.setVisible(true);
            }
        });
    }
}
