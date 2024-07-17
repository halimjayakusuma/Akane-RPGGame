/*
 * Klik nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt untuk mengubah lisensi ini
 * Klik nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java untuk mengedit template ini
 */
package rpggame;

/**
 *
 * @author akane
 */
public class Mage extends Hero {
    // Konstruktor untuk kelas Mage, menetapkan level awal, HP awal, dan damage awal
    public Mage(int level) {
        super(level, 80, 30); // Memanggil konstruktor kelas induk (Hero) dengan level, HP, dan damage awal
    }
}
