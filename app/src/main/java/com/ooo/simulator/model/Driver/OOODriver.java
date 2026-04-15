package com.ooo.simulator.model.Driver;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.ooo.simulator.model.Exception.InvalidSlotException;
import com.ooo.simulator.model.Exception.SlotOccupiedException;
import com.ooo.simulator.model.Model.ComboStatus;
import com.ooo.simulator.model.Model.CoreMedal;
import com.ooo.simulator.model.Model.MedalType;
import com.ooo.simulator.model.Model.SlotStatus;

public class OOODriver {
    private final Map<MedalType, CoreMedal> slots;
    private final Map<MedalType, SlotStatus> slotStatus;
    private ComboStatus currentComboStatus;
    private int powerLevel;

    public OOODriver() {
        slots = new EnumMap<>(MedalType.class);
        slotStatus = new EnumMap<>(MedalType.class);
        for (MedalType type : MedalType.values()) {
            slotStatus.put(type, SlotStatus.EMPTY);
        }
        currentComboStatus = ComboStatus.NORMAL;
        powerLevel = 0;
    }

    public void insertMedal(CoreMedal medal, MedalType targetSlot) {
        if (medal.getType() != targetSlot) {
            throw new InvalidSlotException(
                String.format("Medal %s untuk %s, tidak bisa dimasukkan ke slot %s",
                    medal.getName(), medal.getType(), targetSlot)
            );
        }
        if (slotStatus.get(targetSlot) == SlotStatus.OCCUPIED) {
            throw new SlotOccupiedException("Slot " + targetSlot + " sudah terisi");
        }
        slots.put(targetSlot, medal);
        slotStatus.put(targetSlot, SlotStatus.OCCUPIED);
        System.out.println("Memasukkan " + medal + " ke slot " + targetSlot);
    }

    public CoreMedal removeMedal(MedalType slot) {
        if (slotStatus.get(slot) == SlotStatus.EMPTY) {
            System.out.println("Slot " + slot + " sudah kosong.");
            return null;
        }
        CoreMedal removed = slots.remove(slot);
        slotStatus.put(slot, SlotStatus.EMPTY);
        System.out.println("Melepas " + removed + " dari slot " + slot);
        return removed;
    }

    public void scanMiring() {
        System.out.println("Memindai Driver dalam posisi miring...");
        if (isFull()) {
            System.out.println("Driver siap di-scan.");
        } else {
            System.out.println("Slot belum lengkap, tidak bisa scan miring.");
        }
    }

    public List<CoreMedal> getCurrentMedals() {
        return Arrays.stream(MedalType.values())
                .map(slots::get)
                .filter(Objects::nonNull)
                .toList();
    }

    public boolean isFull() {
        return slotStatus.values().stream().allMatch(s -> s == SlotStatus.OCCUPIED);
    }

    public void clearAll() {
        slots.clear();
        slotStatus.replaceAll((k, v) -> SlotStatus.EMPTY);
        System.out.println("Semua slot Driver dikosongkan.");
    }

    public CoreMedal getMedalInSlot(MedalType slot) {
        return slots.get(slot);
    }

    public ComboStatus getCurrentComboStatus() {
        return currentComboStatus;
    }

    public void setCurrentComboStatus(ComboStatus status) {
        this.currentComboStatus = status;
    }

    public int getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(int powerLevel) {
        this.powerLevel = powerLevel;
    }

    public void cancel() {
        System.out.println("Operasi pada Driver dibatalkan.");
    }

    public void displaySlots() {
        System.out.println("=== SLOT OOO DRIVER ===");
        for (MedalType type : MedalType.values()) {
            CoreMedal m = slots.get(type);
            System.out.printf("%-6s : %s%n", type, m != null ? m.toString() : "Kosong");
        }
    }
}