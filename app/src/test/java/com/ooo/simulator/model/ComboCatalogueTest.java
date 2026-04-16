package com.ooo.simulator.model;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ooo.simulator.model.Engine.ComboCatalogue;
import com.ooo.simulator.model.Model.ComboRule;

class ComboCatalogueTest {
    private ComboCatalogue catalogue;

    @BeforeEach
    public void setUp() {
        catalogue = new ComboCatalogue();
    }

    @Test
    void testTatobaComboExists() {
        Optional<ComboRule> rule = catalogue.findCombo("Taka", "Tora", "Batta");
        assertTrue(rule.isPresent());
        assertEquals("Tatoba", rule.get().getComboName());
        assertEquals("TATOBA.wav", rule.get().getSoundFile());
    }

    @Test
    void testGatakiribaComboExists() {
        Optional<ComboRule> rule = catalogue.findCombo("Kuwagata", "Kamakiri", "Batta");
        assertTrue(rule.isPresent());
        assertEquals("Gatakiriba", rule.get().getComboName());
    }

    @Test
    void testLatorartarComboExists() {
        Optional<ComboRule> rule = catalogue.findCombo("Lion", "Tora", "Cheetah");
        assertTrue(rule.isPresent());
        assertEquals("Latorartar", rule.get().getComboName());
    }

    @Test
    void testSagohzoComboExists() {
        Optional<ComboRule> rule = catalogue.findCombo("Sai", "Gorilla", "Zou");
        assertTrue(rule.isPresent());
        assertEquals("Sagohzo", rule.get().getComboName());
    }

    @Test
    void testShautaComboExists() {
        Optional<ComboRule> rule = catalogue.findCombo("Shachi", "Unagi", "Tako");
        assertTrue(rule.isPresent());
        assertEquals("Shauta", rule.get().getComboName());
    }

    @Test
    void testTajadolComboExists() {
        Optional<ComboRule> rule = catalogue.findCombo("Taka", "Kujaku", "Condor");
        assertTrue(rule.isPresent());
        assertEquals("Tajadol", rule.get().getComboName());
    }

    @Test
    void testPutotyraComboExists() {
        Optional<ComboRule> rule = catalogue.findCombo("Ptera", "Tricera", "Tyranno");
        assertTrue(rule.isPresent());
        assertEquals("Putotyra", rule.get().getComboName());
    }

    @Test
    void testBurakawaniComboExists() {
        Optional<ComboRule> rule = catalogue.findCombo("Cobra", "Kame", "Wani");
        assertTrue(rule.isPresent());
        assertEquals("Burakawani", rule.get().getComboName());
    }

    @Test
    void testMixedFormNotFound() {
        Optional<ComboRule> rule = catalogue.findCombo("Taka", "Kamakiri", "Batta");
        assertFalse(rule.isPresent());
    }
}