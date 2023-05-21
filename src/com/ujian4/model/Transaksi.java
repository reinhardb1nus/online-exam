package com.ujian4.model;

public class Transaksi {
    private int id;
    private int komikId;
    private int jumlahSewa;
    private int totalBiaya;

    public Transaksi(int id, int komikId, int jumlahSewa, int totalBiaya) {
        this.id = id;
        this.komikId = komikId;
        this.jumlahSewa = jumlahSewa;
        this.totalBiaya = totalBiaya;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKomikId() {
        return komikId;
    }

    public void setKomikId(int komikId) {
        this.komikId = komikId;
    }

    public int getJumlahSewa() {
        return jumlahSewa;
    }

    public void setJumlahSewa(int jumlahSewa) {
        this.jumlahSewa = jumlahSewa;
    }

    public int getTotalBiaya() {
        return totalBiaya;
    }

    public void setTotalBiaya(int totalBiaya) {
        this.totalBiaya = totalBiaya;
    }
}
