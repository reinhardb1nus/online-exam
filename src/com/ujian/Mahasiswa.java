package com.ujian;
public class Mahasiswa {
  private String nama;
  private String nim;

  public String getNama() {
    return nama;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public String getNim() {
    return nim;
  }

  public void setNim(String nim) {
    this.nim = nim;
  }

  public void displayMahasiswa() {
    System.out.println("Nama: " + nama);
    System.out.println("NIM: " + nim);
  }
}
