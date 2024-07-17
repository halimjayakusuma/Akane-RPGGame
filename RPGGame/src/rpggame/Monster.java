/*
 * Klik nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt untuk mengubah lisensi ini
 * Klik nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java untuk mengedit template ini
 */
package rpggame;

/**
 *
 * @author akane
 */
public class Monster {
    private int stage; // Tahap atau level monster
    private int hp; // Hit Points atau kesehatan monster
    private int damage; // Damage atau kerusakan yang dapat ditimbulkan oleh monster

    // Konstruktor untuk kelas Monster, menetapkan stage, HP, dan damage berdasarkan stage
    public Monster(int stage) {
        this.stage = stage;
        switch (stage) {
            case 1:
                this.hp = 200;
                this.damage = 10;
                break;
            case 2:
                this.hp = 300;
                this.damage = 15;
                break;
            case 3:
                this.hp = 400;
                this.damage = 20;
                break;
            case 4:
                this.hp = 500;
                this.damage = 25;
                break;
            default:
                this.hp = 100;
                this.damage = 5;
                break;
        }
    }

    // Getter untuk mendapatkan tahap monster
    public int getStage() {
        return stage;
    }

    // Getter untuk mendapatkan HP monster
    public int getHp() {
        return hp;
    }

    // Setter untuk menetapkan HP monster
    public void setHp(int hp) {
        this.hp = hp;
    }

    // Getter untuk mendapatkan damage monster
    public int getDamage() {
        return damage;
    }
}
