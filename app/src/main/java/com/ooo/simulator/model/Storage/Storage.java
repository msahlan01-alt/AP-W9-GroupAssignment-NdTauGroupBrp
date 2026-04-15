package com.ooo.simulator.model.Storage;

import java.util.List;

import com.ooo.simulator.model.Model.CoreMedal;
import com.ooo.simulator.model.Repository.MedalRepository;

public class Storage {
    private final MedalRepository<CoreMedal> medals;
    private boolean active;
    private int powerStatus;

    public Storage() {
        medals = new MedalRepository<>();
        active = true;
        powerStatus = 100;
    }

    public void addMedal(CoreMedal medal) {
        medals.add(medal);
        System.out.println(medal.getName() + " ditambahkan ke Storage.");
    }

    public void removeMedal(CoreMedal medal) {
        if (medals.remove(medal)) {
            System.out.println(medal.getName() + " dikeluarkan dari Storage.");
        } else {
            System.out.println("Medal tidak ditemukan.");
        }
    }

    public void pressFront() {
        if (!active) {
            System.out.println("Storage tidak aktif.");
            return;
        }
        System.out.println("Menekan depan Storage... Memindai koleksi medal.");
        System.out.println("Jumlah medal: " + medals.size());
        medals.getAll().forEach(m -> System.out.println(" - " + m.getName()));
    }

    public void pullBack() {
        System.out.println("Menarik belakang Storage...");
        open();
    }

    public void open() {
        System.out.println("Storage terbuka. Isi koleksi:");
        if (medals.isEmpty()) {
            System.out.println(" (kosong)");
        } else {
            medals.getAll().forEach(System.out::println);
        }
    }

    public void close() {
        System.out.println("Storage ditutup.");
    }

    public void cancel() {
        System.out.println("Operasi Storage dibatalkan.");
    }

    public List<CoreMedal> getAllMedals() {
        return medals.getAll();
    }

    public MedalRepository<CoreMedal> getRepository() {
        return medals;
    }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public int getPowerStatus() { return powerStatus; }
    public void setPowerStatus(int powerStatus) { this.powerStatus = powerStatus; }
}