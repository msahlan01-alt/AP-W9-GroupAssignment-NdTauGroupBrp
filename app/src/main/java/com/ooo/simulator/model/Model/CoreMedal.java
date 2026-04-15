package com.ooo.simulator.model.Model;

public class CoreMedal {
    private final String name;
    private final Tier tier;
    private final MedalType type;
    private final String creature;
    private final int basePower;
    private ComboStatus status;
    private final String soundEffect;

    public CoreMedal(String name, Tier tier, MedalType type, String creature,
                     int basePower, String soundEffect) {
        this.name = name;
        this.tier = tier;
        this.type = type;
        this.creature = creature;
        this.basePower = basePower;
        this.soundEffect = soundEffect;
        this.status = ComboStatus.NORMAL;
    }

    public String getName() { return name; }
    public Tier getTier() { return tier; }
    public MedalType getType() { return type; }
    public String getCreature() { return creature; }
    public int getBasePower() { return basePower; }
    public ComboStatus getStatus() { return status; }
    public void setStatus(ComboStatus status) { this.status = status; }
    public String getSoundEffect() { return soundEffect; }

    public int getComboPower(int medalCount) {
        int multiplier = switch (tier) {
            case I -> 1;
            case II -> 2;
            case III -> 3;
        };
        return basePower * multiplier * medalCount;
    }

    @Override
    public String toString() {
        return String.format("%s (%s, Tier %s)", name, creature, tier);
    }
}