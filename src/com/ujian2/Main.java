package com.ujian2;

public class Main {
    public static void main(String[] args) {
        Manajer manajer = new Manajer("John", 5000000, 1000000);

        System.out.println("Nama: " + manajer.getNama());
        System.out.println("Gaji: " + manajer.getGaji());
        System.out.println("Tunjangan: " + manajer.getTunjangan());
    }
}
