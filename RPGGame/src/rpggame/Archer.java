/*
 * Klik nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt untuk mengubah lisensi ini
 * Klik nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java untuk mengedit template ini
 */
package rpggame;

/**
 *
 * @author akane
 */
public class Archer extends Hero {
    // Konstruktor untuk kelas Archer, menetapkan level awal, HP awal, dan damage awal
    public Archer(int level) {
        super(level, 90, 25); // Memanggil konstruktor kelas induk (Hero) dengan level, HP, dan damage awal
    }
}
