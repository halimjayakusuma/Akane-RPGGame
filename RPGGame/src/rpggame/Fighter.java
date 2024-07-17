/*
 * Klik nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt untuk mengubah lisensi ini
 * Klik nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java untuk mengedit template ini
 */
package rpggame;

/**
 *
 * @author akane
 */
public class Fighter extends Hero {
    // Konstruktor untuk kelas Fighter, menetapkan level awal, HP awal, dan damage awal
    public Fighter(int level) {
        super(level, 100, 20); // Memanggil konstruktor kelas induk (Hero) dengan level, HP, dan damage awal
    }
}
