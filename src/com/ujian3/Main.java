package com.ujian3;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int jumlahBilangan = 0;
        double total = 0;

        System.out.print("Masukkan jumlah bilangan: ");
        try {
            jumlahBilangan = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Input tidak valid. Program dihentikan.");
            return;
        }

        System.out.println("Masukkan " + jumlahBilangan + " bilangan:");

        for (int i = 0; i < jumlahBilangan; i++) {
            try {
                double bilangan = scanner.nextDouble();
                total += bilangan;
            } catch (InputMismatchException e) {
                System.out.println("Bilangan tidak valid. Penghitungan dihentikan.");
                return;
            }
        }

        if (jumlahBilangan == 0) {
            System.out.println("Tidak ada bilangan yang dimasukkan.");
        } else {
            double rataRata = total / jumlahBilangan;
            System.out.println("Rata-rata: " + rataRata);
        }
    }
}
