package com.ooo.simulator.model.Engine;

import com.ooo.simulator.model.Driver.OOODriver;
import com.ooo.simulator.model.Driver.OScanner;
import com.ooo.simulator.model.Model.ComboRule;
import com.ooo.simulator.model.Model.ComboStatus;
import com.ooo.simulator.model.Model.CoreMedal;
import com.ooo.simulator.model.Model.MedalType;

import java.util.List;
import java.util.Optional;

public class TransformationEngine {
    private final ComboCatalogue catalogue;
    private final OScanner scanner;

    public TransformationEngine() {
        catalogue = new ComboCatalogue();
        scanner = new OScanner();
    }

    public TransformationResult transform(OOODriver driver, boolean isRescan) {
        if (!driver.isFull()) {
            return new TransformationResult.Builder()
                    .success(false)
                    .message("Slot Driver belum lengkap.")
                    .medalsUsed(driver.getCurrentMedals())
                    .build();
        }

        CoreMedal head = driver.getMedalInSlot(MedalType.HEAD);
        CoreMedal arms = driver.getMedalInSlot(MedalType.ARMS);
        CoreMedal legs = driver.getMedalInSlot(MedalType.LEGS);

        String hCreature = head.getCreature();
        String aCreature = arms.getCreature();
        String lCreature = legs.getCreature();

        Optional<ComboRule> matched = catalogue.findCombo(hCreature, aCreature, lCreature);
        TransformationResult.Builder builder = new TransformationResult.Builder()
                .success(true)
                .medalsUsed(List.of(head, arms, legs));

        if (matched.isPresent()) {
            ComboRule rule = matched.get();
            builder.comboName(rule.getComboName());
            driver.setCurrentComboStatus(ComboStatus.VALID);
            int totalPower = head.getComboPower(3) + arms.getComboPower(3) + legs.getComboPower(3);
            driver.setPowerLevel(totalPower);
            builder.power(totalPower);
            builder.soundFile(rule.getSoundFile());

            if (isRescan) {
                builder.isScanningCharge(true)
                       .message("SCANNING CHARGE! " + rule.getComboName());
            } else {
                builder.isMixedForm(false)
                       .message(rule.getComboName() + " COMBO!");
            }
            scanner.suaraCombo(true);
        } else {
            builder.comboName("Mixed Form")
                   .isMixedForm(true)
                   .message("MIXED FORM");
            driver.setCurrentComboStatus(ComboStatus.INVALID);
            driver.setPowerLevel(0);
            scanner.suaraCombo(false);
        }

        return builder.build();
    }

    public void displayCatalogue() {
        catalogue.displayAll();
    }

    public OScanner getScanner() {
        return scanner;
    }
}