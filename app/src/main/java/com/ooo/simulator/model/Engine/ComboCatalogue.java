package com.ooo.simulator.model.Engine;

import java.util.Optional;

import com.ooo.simulator.model.Model.ComboRule;
import com.ooo.simulator.model.Repository.MedalRepository;

public class ComboCatalogue {
    private final MedalRepository<ComboRule> rules;

    public ComboCatalogue() {
        rules = new MedalRepository<>();
        initializeCombos();
    }

    private void initializeCombos() {
        rules.add(new ComboRule("Tatoba", "Taka", "Tora", "Batta", "TATOBA.wav"));
        rules.add(new ComboRule("Gatakiriba", "Kuwagata", "Kamakiri", "Batta", "GATAKIRIBA.wav"));
        rules.add(new ComboRule("Latorartar", "Lion", "Tora", "Cheetah", "LATORARTAR.wav"));
        rules.add(new ComboRule("Sagohzo", "Sai", "Gorilla", "Zou", "SAGOHZO.wav"));
        rules.add(new ComboRule("Shauta", "Shachi", "Unagi", "Tako", "SHAUTA.wav"));
        rules.add(new ComboRule("Tajadol", "Taka", "Kujaku", "Condor", "TAJADOL.wav"));
        rules.add(new ComboRule("Putotyra", "Ptera", "Tricera", "Tyranno", "PUTOTYRA.wav"));
        rules.add(new ComboRule("Burakawani", "Cobra", "Kame", "Wani", "BURAKAWANI.wav"));
    }

    public Optional<ComboRule> findCombo(String head, String arms, String legs) {
        return rules.getAll().stream()
                .filter(r -> r.matches(head, arms, legs))
                .findFirst();
    }

    public void displayAll() {
        System.out.println("=== KATALOG COMBO ===");
        rules.getAll().forEach(System.out::println);
    }
}