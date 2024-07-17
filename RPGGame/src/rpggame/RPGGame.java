/*
 * Klik nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt untuk mengubah lisensi ini
 * Klik nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java untuk mengedit template ini
 */
package rpggame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author akane
 */

public class RPGGame {
    // Konstanta untuk koneksi database
    private static final String DB_URL = "jdbc:mysql://127.0.0.1/rpg_game";
    private static final String USER = "root";
    private static final String PASS = "";

    // Main method untuk menjalankan permainan
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hero hero = null;
        Shop shop = new Shop();
        int pengalaman = 0; // Pengalaman awal
        Instant waktuMulai = Instant.now();

        // Penerimaan input dari pengguna
        System.out.println("Selamat datang di AkaneRPGGame!");
        System.out.println("Masukkan Nama Hero Anda:");
        String namaHero = scanner.nextLine();

        System.out.println("Pilih Hero Anda:");
        System.out.println("1) Fighter");
        System.out.println("2) Mage");
        System.out.println("3) Archer");
        int pilihan = scanner.nextInt();

        // Pemilihan tipe hero berdasarkan input pengguna
        switch (pilihan) {
            case 1:
                hero = new Fighter(1);
                hero.setDamage(50); // Set damage awal untuk Fighter
                break;
            case 2:
                hero = new Mage(1);
                hero.setDamage(60); // Set damage awal untuk Mage
                break;
            case 3:
                hero = new Archer(1);
                hero.setDamage(55); // Set damage awal untuk Archer
                break;
            default:
                System.out.println("Pilihan tidak valid!");
                return;
        }

        System.out.println("Memulai permainan dengan Hero pilihan Anda...");
        System.out.println("Statistik Hero - Nama: " + namaHero + ", Level: " + hero.getLevel() + ", HP: " + hero.getHp() + ", Damage: " + hero.getDamage());

        Random random = new Random();

        // Loop melalui tiga tahap permainan
        for (int tahap = 1; tahap <= 3; tahap++) {
            // Membuat monster untuk setiap tahap
            Monster monster;
            if (tahap == 1) {
                monster = new Monster(random.nextInt(2) + 1); // Monster level 1 atau 2
            } else {
                monster = new Monster(random.nextInt(4) + 1); // Monster level 1 hingga 4
            }

            System.out.println("Tahap " + tahap + " dimulai!");
            System.out.println("Statistik Monster - Tahap: " + monster.getStage() + ", HP: " + monster.getHp() + ", Damage: " + monster.getDamage());

            // Pertarungan antara hero dan monster
            while (monster.getHp() > 0 && hero.getHp() > 0) {
                System.out.println("Anda bertemu dengan musuh Monster Babak " + tahap + ", apa yang Anda akan lakukan?");
                System.out.println("1) Lawan");
                System.out.println("2) Bertahan");
                System.out.println("3) Kabur");
                System.out.println("4) Beli Heal Potion");
                System.out.println("5) Keluar Game");
                int aksi = scanner.nextInt();

                switch (aksi) {
                    case 1:
                        // Hero menyerang monster
                        monster.setHp(Math.max(monster.getHp() - hero.getDamage(), 0));
                        pengalaman += 10; // Tambah pengalaman untuk menyerang
                        hero.addExp(10); // Tambah EXP
                        System.out.println("Hero menyerang! HP Monster: " + monster.getHp());
                        if (monster.getHp() > 0) {
                            // Monster menyerang kembali jika masih hidup
                            hero.setHp(Math.max(hero.getHp() - monster.getDamage(), 0));
                            System.out.println("Monster menyerang! HP Hero: " + hero.getHp());
                        } else {
                            System.out.println("Hero menyerang! Monster Tahap " + monster.getStage() + " kalah.");
                        }
                        break;
                    case 2:
                        // Hero bertahan dari serangan monster
                        hero.setHp(Math.max(hero.getHp() - (monster.getDamage() / 2), 0));
                        pengalaman += 5; // Tambah pengalaman untuk bertahan
                        hero.addExp(5); // Tambah EXP
                        System.out.println("Hero bertahan! HP Hero: " + hero.getHp());
                        break;
                    case 3:
                        // Hero kabur dan mendapatkan sedikit heal
                        hero.setHp(Math.min(hero.getHp() + 20, hero.getMaxHp())); // Heal sedikit saat kabur, tidak boleh melebihi HP max
                        System.out.println("Hero kabur! HP Hero: " + hero.getHp());

                        // Pilih monster baru secara acak untuk babak 2
                        if (tahap == 2) {
                            monster = new Monster(random.nextInt(4) + 1); // Monster level 1 hingga 4
                            System.out.println("Statistik Monster Baru - Tahap: " + monster.getStage() + ", HP: " + monster.getHp() + ", Damage: " + monster.getDamage());
                        }
                        break;
                    case 4:
                        // Hero membeli Heal Potion jika memiliki emas yang cukup
                        if (hero.getEmas() >= shop.getPotionPrice()) {
                            hero.setHp(Math.min(hero.getHp() + 10, hero.getMaxHp())); // Heal 10 HP saat membeli potion, tidak boleh melebihi HP max
                            hero.addEmas(-shop.getPotionPrice());
                            System.out.println("Anda membeli Heal Potion. HP Hero: " + hero.getHp() + ", Sisa Emas: " + hero.getEmas());
                        } else {
                            System.out.println("Emas tidak cukup untuk membeli Heal Potion.");
                        }
                        break;
                    case 5:
                        // Keluar dari permainan dan mencatat data hero
                        System.out.println("Permainan dihentikan.");
                        catatDataHero(namaHero, hero, "Keluar", pengalaman, waktuMulai);
                        return;
                    default:
                        System.out.println("Aksi tidak valid!");
                        continue;
                }

                // Cek apakah hero kalah
                if (hero.getHp() <= 0) {
                    System.out.println("Monster menyerang! Hero kalah.");
                    System.out.println("Game over.");
                    catatDataHero(namaHero, hero, "Kalah", pengalaman, waktuMulai);
                    return;
                }
            }

            // Jika hero mengalahkan monster dan masih hidup
            if (monster.getHp() <= 0 && hero.getHp() > 0) {
                System.out.println("Tahap " + tahap + " selesai!");

                // Tambah HP berdasarkan tahap dan sesuaikan damage
                if (tahap == 1) {
                    hero.setHp(hero.getHp() + 100);
                    hero.setDamage((int) (hero.getDamage() * 1.2)); // Sesuaikan damage untuk Tahap 2 (20% peningkatan)
                } else if (tahap == 2) {
                    hero.setHp(hero.getHp() + 250);
                    hero.setDamage((int) (hero.getDamage() * 1.2)); // Sesuaikan damage untuk Tahap 3 (20% peningkatan)
                }

                System.out.println("Hero naik level! Statistik baru - Nama: " + namaHero + ", Level: " + hero.getLevel() + ", HP: " + hero.getHp() + ", Damage: " + hero.getDamage() + ", EXP: " + hero.getExp()+ ", Emas: " + hero.getEmas());
            }
        }

        // Permainan selesai dan mencatat data hero
        System.out.println("Permainan selesai! Statistik akhir Hero - Nama: " + namaHero + ", Level: " + hero.getLevel() + ", HP: " + hero.getHp() + ", Damage: " + hero.getDamage() + ", Emas: " + hero.getEmas());
        catatDataHero(namaHero, hero, "Selesai", pengalaman, waktuMulai);
    }

    // Metode untuk mencatat data hero ke database
    private static void catatDataHero(String namaHero, Hero hero, String status, int pengalaman, Instant waktuMulai) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String query = "INSERT INTO hero_stats (nama_hero, tipe_hero, level, hp, damage, emas, status, waktu_bermain, waktu_mulai, skor_akhir, total_pengalaman) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);

            Timestamp waktuMulaiTimestamp = Timestamp.from(waktuMulai);
            Instant waktuAkhir = Instant.now();
            Duration waktuBermain = Duration.between(waktuMulai, waktuAkhir);
            long waktuBermainDetik = waktuBermain.getSeconds();
            int skorAkhir = hero.getLevel() * 100 + hero.getEmas() + pengalaman;

            // Set nilai parameter untuk query
            stmt.setString(1, namaHero);
            stmt.setString(2, hero.getClass().getSimpleName());
            stmt.setInt(3, hero.getLevel());
            stmt.setInt(4, hero.getHp());
            stmt.setInt(5, hero.getDamage());
            stmt.setInt(6, hero.getEmas());
            stmt.setString(7, status);
            stmt.setLong(8, waktuBermainDetik);
            stmt.setTimestamp(9, waktuMulaiTimestamp);
            stmt.setInt(10, skorAkhir);
            stmt.setInt(11, pengalaman);

            stmt.executeUpdate();
            System.out.println("Data hero berhasil dicatat!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
