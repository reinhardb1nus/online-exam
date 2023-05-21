package com.ujian2;

public class Pegawai {
  private String nama;
  private int gaji;

  public Pegawai(String nama, int gaji) {
    this.nama = nama;
    this.gaji = gaji;
  }

  public String getNama() {
    return nama;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public int getGaji() {
    return gaji;
  }

  public void setGaji(int gaji) {
    this.gaji = gaji;
  }
} 
