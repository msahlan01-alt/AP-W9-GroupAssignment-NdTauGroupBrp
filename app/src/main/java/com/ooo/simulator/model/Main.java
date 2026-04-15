package com.ooo.simulator.model;

import com.ooo.simulator.model.Driver.OOODriver;
import com.ooo.simulator.model.Engine.TransformationEngine;
import com.ooo.simulator.model.Engine.TransformationResult;
import com.ooo.simulator.model.Exception.InvalidSlotException;
import com.ooo.simulator.model.Exception.SlotOccupiedException;
import com.ooo.simulator.model.Model.CoreMedal;
import com.ooo.simulator.model.Model.MedalType;
import com.ooo.simulator.model.Model.Tier;
import com.ooo.simulator.model.Repository.MedalRepository;
import com.ooo.simulator.model.Storage.Storage;

public class Main {
    public static void main(String[] args) {
        MedalRepository<CoreMedal> medalRepo = new MedalRepository<>();
        initializeMedals(medalRepo);

        OOODriver driver = new OOODriver();
        Storage storage = new Storage();
        TransformationEngine engine = new TransformationEngine();

        // Isi storage
        medalRepo.getAll().forEach(storage::addMedal);

        System.out.println("\n=== SIMULATOR OOO DRIVER (FULL FEATURES) ===");
        engine.displayCatalogue();

        // Demonstrasi Storage
        System.out.println("\n--- Demonstrasi Storage ---");
        storage.pressFront();
        storage.pullBack();
        storage.close();

        // Demonstrasi scan miring sebelum penuh
        System.out.println("\n--- Scan Miring Sebelum Slot Penuh ---");
        driver.scanMiring();

        // Masukkan combo TATOBA
        try {
            CoreMedal taka = findMedal(medalRepo, "Taka", MedalType.HEAD);
            CoreMedal tora = findMedal(medalRepo, "Tora", MedalType.ARMS);
            CoreMedal batta = findMedal(medalRepo, "Batta", MedalType.LEGS);

            driver.insertMedal(taka, MedalType.HEAD);
            driver.insertMedal(tora, MedalType.ARMS);
            driver.insertMedal(batta, MedalType.LEGS);
            driver.displaySlots();

            // Scan miring setelah penuh
            System.out.println("\n--- Scan Miring Setelah Slot Penuh ---");
            driver.scanMiring();

            // Scan pertama
            System.out.println("\n--- Scan Pertama ---");
            TransformationResult result = engine.transform(driver, false);
            result.display();

            // Scan ulang (Scanning Charge)
            System.out.println("\n--- Scan Ulang (Scanning Charge) ---");
            result = engine.transform(driver, true);
            result.display();

            // Mixed Form
            System.out.println("\n--- Ganti Medal Jadi Mixed Form ---");
            driver.removeMedal(MedalType.HEAD);
            CoreMedal kuwagata = findMedal(medalRepo, "Kuwagata", MedalType.HEAD);
            driver.insertMedal(kuwagata, MedalType.HEAD);
            driver.displaySlots();

            result = engine.transform(driver, false);
            result.display();

        } catch (SlotOccupiedException | InvalidSlotException e) {
            System.err.println("Error: " + e.getMessage());
        }

        // Cancel
        System.out.println("\n--- Demonstrasi Cancel ---");
        driver.cancel();
        storage.cancel();
        engine.getScanner().cancel();

        // Scan medal tunggal
        System.out.println("\n--- Scan Medal Tunggal ---");
        CoreMedal single = medalRepo.getAll().get(0);
        TransformationResult singleResult = engine.getScanner().scanMedal(single);
        singleResult.display();
    }

    private static void initializeMedals(MedalRepository<CoreMedal> repo) {
        // Head
        repo.add(new CoreMedal("Taka Head", Tier.I, MedalType.HEAD, "Taka", 10, "Taka.wav"));
        repo.add(new CoreMedal("Kuwagata Head", Tier.II, MedalType.HEAD, "Kuwagata", 12, "Kuwagata.wav"));
        repo.add(new CoreMedal("Lion Head", Tier.II, MedalType.HEAD, "Lion", 15, "Lion.wav"));
        repo.add(new CoreMedal("Sai Head", Tier.III, MedalType.HEAD, "Sai", 20, "Sai.wav"));
        repo.add(new CoreMedal("Shachi Head", Tier.I, MedalType.HEAD, "Shachi", 10, "Shachi.wav"));
        repo.add(new CoreMedal("Ptera Head", Tier.III, MedalType.HEAD, "Ptera", 25, "Ptera.wav"));
        repo.add(new CoreMedal("Cobra Head", Tier.II, MedalType.HEAD, "Cobra", 15, "Cobra.wav"));
        // Arms
        repo.add(new CoreMedal("Tora Arms", Tier.II, MedalType.ARMS, "Tora", 15, "Tora.wav"));
        repo.add(new CoreMedal("Kamakiri Arms", Tier.I, MedalType.ARMS, "Kamakiri", 10, "Kamakiri.wav"));
        repo.add(new CoreMedal("Gorilla Arms", Tier.III, MedalType.ARMS, "Gorilla", 25, "Gorilla.wav"));
        repo.add(new CoreMedal("Unagi Arms", Tier.I, MedalType.ARMS, "Unagi", 10, "Unagi.wav"));
        repo.add(new CoreMedal("Kujaku Arms", Tier.II, MedalType.ARMS, "Kujaku", 18, "Kujaku.wav"));
        repo.add(new CoreMedal("Tricera Arms", Tier.III, MedalType.ARMS, "Tricera", 22, "Tricera.wav"));
        repo.add(new CoreMedal("Kame Arms", Tier.I, MedalType.ARMS, "Kame", 10, "Kame.wav"));
        // Legs
        repo.add(new CoreMedal("Batta Legs", Tier.I, MedalType.LEGS, "Batta", 8, "Batta.wav"));
        repo.add(new CoreMedal("Cheetah Legs", Tier.II, MedalType.LEGS, "Cheetah", 14, "Cheetah.wav"));
        repo.add(new CoreMedal("Zou Legs", Tier.III, MedalType.LEGS, "Zou", 20, "Zou.wav"));
        repo.add(new CoreMedal("Tako Legs", Tier.I, MedalType.LEGS, "Tako", 9, "Tako.wav"));
        repo.add(new CoreMedal("Condor Legs", Tier.II, MedalType.LEGS, "Condor", 16, "Condor.wav"));
        repo.add(new CoreMedal("Tyranno Legs", Tier.III, MedalType.LEGS, "Tyranno", 24, "Tyranno.wav"));
        repo.add(new CoreMedal("Wani Legs", Tier.II, MedalType.LEGS, "Wani", 12, "Wani.wav"));
    }

    private static CoreMedal findMedal(MedalRepository<CoreMedal> repo, String creature, MedalType type) {
        return repo.findByPredicate(m -> m.getCreature().equals(creature) && m.getType() == type)
                .stream().findFirst().orElse(null);
    }
}