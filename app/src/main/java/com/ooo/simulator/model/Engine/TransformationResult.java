package com.ooo.simulator.model.Engine;

import java.util.List;

import com.ooo.simulator.model.Model.CoreMedal;

public class TransformationResult {
    private final boolean success;
    private final String comboName;
    private final boolean isMixedForm;
    private final boolean isScanningCharge;
    private final String message;
    private final List<CoreMedal> medalsUsed;
    private final int power;
    private final String soundFile;

    private TransformationResult(Builder builder) {
        this.success = builder.success;
        this.comboName = builder.comboName;
        this.isMixedForm = builder.isMixedForm;
        this.isScanningCharge = builder.isScanningCharge;
        this.message = builder.message;
        this.medalsUsed = builder.medalsUsed;
        this.power = builder.power;
        this.soundFile = builder.soundFile;
    }

    public static class Builder {
        private boolean success;
        private String comboName = "Unknown";
        private boolean isMixedForm = false;
        private boolean isScanningCharge = false;
        private String message = "";
        private List<CoreMedal> medalsUsed;
        private int power = 0;
        private String soundFile = "";

        public Builder success(boolean s) { this.success = s; return this; }
        public Builder comboName(String n) { this.comboName = n; return this; }
        public Builder isMixedForm(boolean m) { this.isMixedForm = m; return this; }
        public Builder isScanningCharge(boolean c) { this.isScanningCharge = c; return this; }
        public Builder message(String m) { this.message = m; return this; }
        public Builder medalsUsed(List<CoreMedal> m) { this.medalsUsed = m; return this; }
        public Builder power(int p) { this.power = p; return this; }
        public Builder soundFile(String s) { this.soundFile = s; return this; }

        public TransformationResult build() {
            if (message.isEmpty()) {
                if (isScanningCharge) message = "SCANNING CHARGE!";
                else if (isMixedForm) message = "MIXED FORM";
                else message = comboName + " COMBO!";
            }
            return new TransformationResult(this);
        }
    }

    public void display() {
        System.out.println("\n===== HASIL TRANSFORMASI =====");
        System.out.println("Sukses   : " + success);
        System.out.println("Combo    : " + comboName);
        System.out.println("Power    : " + power);
        System.out.println("Pesan    : " + message);
        if (soundFile != null && !soundFile.isEmpty()) {
            System.out.println("♪ Memutar: " + soundFile + " ♪");
        }
        System.out.print("Medal    : ");
        medalsUsed.forEach(m -> System.out.print(m.getCreature() + " "));
        System.out.println("\n==============================\n");
    }

    // getters...
    public boolean isSuccess() { return success; }
    public String getComboName() { return comboName; }
    public boolean isMixedForm() { return isMixedForm; }
    public boolean isScanningCharge() { return isScanningCharge; }
    public String getMessage() { return message; }
    public List<CoreMedal> getMedalsUsed() { return medalsUsed; }
    public int getPower() { return power; }
    public String getSoundFile() { return soundFile; }
}