package com.ooo.simulator.model.Driver;

import java.util.List;

import com.ooo.simulator.model.Engine.TransformationResult;
import com.ooo.simulator.model.Exception.ScanNotReadyException;
import com.ooo.simulator.model.Model.CoreMedal;
import com.ooo.simulator.model.Model.Tier;
import com.ooo.simulator.model.Storage.Storage;

public class OScanner {
    private boolean active;
    private final int lastScanHash;
    private int scanCount;

    public OScanner() {
        active = true;
        lastScanHash = 0;
        scanCount = 0;
    }

    public TransformationResult scanDriver(OOODriver driver) {
        if (!active) throw new ScanNotReadyException("Scanner tidak aktif.");
        System.out.println("Memindai OOO Driver...");
        if (!driver.isFull()) {
            throw new ScanNotReadyException("Slot Driver belum terisi penuh.");
        }
        List<CoreMedal> medals = driver.getCurrentMedals();
        // Placeholder result, akan diolah oleh TransformationEngine
        return new TransformationResult.Builder()
                .success(true)
                .medalsUsed(medals)
                .build();
    }

    public TransformationResult scanStorage(Storage storage) {
        if (!active) throw new ScanNotReadyException("Scanner tidak aktif.");
        System.out.println("Memindai Storage...");
        List<CoreMedal> medals = storage.getAllMedals();
        return new TransformationResult.Builder()
                .success(true)
                .message("Hasil scan Storage: " + medals.size() + " medal.")
                .medalsUsed(medals)
                .build();
    }

    public TransformationResult scanMedal(CoreMedal medal) {
        if (!active) throw new ScanNotReadyException("Scanner tidak aktif.");
        System.out.println("Memindai medal tunggal: " + medal.getName());
        return new TransformationResult.Builder()
                .success(true)
                .message("Medal: " + medal.getCreature() + ", Tier: " + medal.getTier())
                .medalsUsed(List.of(medal))
                .build();
    }

    public int hitungJumlahMedal(List<CoreMedal> medals) {
        return medals.size();
    }

    public Tier ketahuiKodeMedal(CoreMedal medal) {
        return medal.getTier();
    }

    public void suaraCombo(boolean isValid) {
        if (isValid) {
            System.out.println("♪♪♪ Lagu combo valid diputar! ♪♪♪");
        } else {
            System.out.println("(Tidak ada lagu, combo invalid)");
        }
    }

    public void cancel() {
        System.out.println("Pemindaian dibatalkan.");
        scanCount = 0;
    }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public int getScanCount() { return scanCount; }
    public void incrementScanCount() { scanCount++; }

    public int getLastScanHash() {
        return lastScanHash;
    }
}