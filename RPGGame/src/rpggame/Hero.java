/*
 * Klik nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt untuk mengubah lisensi ini
 * Klik nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java untuk mengedit template ini
 */
package rpggame;

/**
 *
 * @author akane
 */
class Hero {
    private int level; // Level hero
    private int hp; // Hit Points atau kesehatan hero
    private int damage; // Damage atau kerusakan yang dapat ditimbulkan oleh hero
    private int exp; // Pengalaman hero
    private int expToNextLevel; // Pengalaman yang dibutuhkan untuk naik ke level berikutnya
    private int emas; // Emas yang dimiliki hero

    // Konstruktor untuk kelas Hero, menetapkan level, HP, damage, exp awal, dan emas awal
    public Hero(int level, int hp, int damage) {
        this.level = level;
        this.hp = hp;
        this.damage = damage;
        this.exp = 0;
        this.expToNextLevel = 20; // Pengalaman awal yang dibutuhkan untuk naik ke Level 2
        this.emas = 50; // Emas awal
    }

    // Getter untuk mendapatkan level hero
    public int getLevel() {
        return level;
    }

    // Getter untuk mendapatkan HP hero
    public int getHp() {
        return hp;
    }

    // Setter untuk menetapkan HP hero, tidak boleh melebihi HP maksimal
    public void setHp(int hp) {
        this.hp = Math.min(hp, getMaxHp());
    }

    // Getter untuk mendapatkan damage hero
    public int getDamage() {
        return damage;
    }

    // Setter untuk menetapkan damage hero
    public void setDamage(int damage) {
        this.damage = damage;
    }

    // Getter untuk mendapatkan pengalaman hero
    public int getExp() {
        return exp;
    }

    // Metode untuk menambahkan pengalaman hero dan mengecek apakah hero naik level
    public void addExp(int exp) {
        this.exp += exp;
        checkLevelUp();
    }

    // Getter untuk mendapatkan emas hero
    public int getEmas() {
        return emas;
    }

    // Metode untuk menambahkan emas hero
    public void addEmas(int emas) {
        this.emas += emas;
    }

    // Getter untuk mendapatkan HP maksimal berdasarkan level hero
    public int getMaxHp() {
        switch (this.level) {
            case 1: return 100;
            case 2: return 120;
            case 3: return 140;
            case 4: return 160;
            case 5: return 180;
            case 6: return 200;
            default: return 100;
        }
    }

    // Metode untuk mengecek apakah hero naik level
    private void checkLevelUp() {
        while (this.exp >= expToNextLevel) {
            levelUp();
        }
    }

    // Metode untuk meningkatkan level hero
    private void levelUp() {
        this.exp -= expToNextLevel;
        this.level++;
        this.hp = getMaxHp(); // Set HP ke maksimum saat naik level
        this.damage = (int) (this.damage * 1.2); // Naikkan damage sebesar 20%
        this.expToNextLevel += 20; // Tambah pengalaman yang dibutuhkan untuk level berikutnya
        this.emas += 20; // Tambah emas setiap kali naik level
        System.out.println("Hero naik level! Level baru: " + this.level);
    }
}
