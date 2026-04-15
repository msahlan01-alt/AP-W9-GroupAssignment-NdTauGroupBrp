package com.ooo.simulator.model.Model;

public class ComboRule {
    private final String comboName;
    private final String headCreature;
    private final String armsCreature;
    private final String legsCreature;
    private final String soundFile;

    public ComboRule(String comboName, String head, String arms, String legs, String soundFile) {
        this.comboName = comboName;
        this.headCreature = head;
        this.armsCreature = arms;
        this.legsCreature = legs;
        this.soundFile = soundFile;
    }

    public boolean matches(String head, String arms, String legs) {
        return this.headCreature.equals(head) &&
               this.armsCreature.equals(arms) &&
               this.legsCreature.equals(legs);
    }

    public String getComboName() { return comboName; }
    public String getHeadCreature() { return headCreature; }
    public String getArmsCreature() { return armsCreature; }
    public String getLegsCreature() { return legsCreature; }
    public String getSoundFile() { return soundFile; }

    @Override
    public String toString() {
        return comboName + " (" + soundFile + ")";
    }
}