package com.ooo.simulator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ooo.simulator.model.Driver.OOODriver;
import com.ooo.simulator.model.Engine.TransformationEngine;
import com.ooo.simulator.model.Engine.TransformationResult;
import com.ooo.simulator.model.Model.CoreMedal;
import com.ooo.simulator.model.Model.MedalType;
import com.ooo.simulator.model.Model.Tier;

class TransformationEngineTest {
    private OOODriver driver;
    private TransformationEngine engine;

    // Medal untuk combo Tatoba
    private CoreMedal taka, tora, batta;
    // Medal untuk combo Gatakiriba
    private CoreMedal kuwagata, kamakiri;
    // Medal untuk combo Latorartar
    private CoreMedal lion, cheetah;
    // Medal untuk combo Sagohzo
    private CoreMedal sai, gorilla, zou;
    // Medal untuk combo Shauta
    private CoreMedal shachi, unagi, tako;
    // Medal untuk combo Tajadol
    private CoreMedal kujaku, condor;
    // Medal untuk combo Putotyra
    private CoreMedal ptera, tricera, tyranno;
    // Medal untuk combo Burakawani
    private CoreMedal cobra, kame, wani;

    @BeforeEach
    public void setUp() {
        driver = new OOODriver();
        engine = new TransformationEngine();

        // Tatoba
        taka = new CoreMedal("Taka Head", Tier.I, MedalType.HEAD, "Taka", 10, "Taka.wav");
        tora = new CoreMedal("Tora Arms", Tier.II, MedalType.ARMS, "Tora", 15, "Tora.wav");
        batta = new CoreMedal("Batta Legs", Tier.I, MedalType.LEGS, "Batta", 8, "Batta.wav");

        // Gatakiriba
        kuwagata = new CoreMedal("Kuwagata Head", Tier.II, MedalType.HEAD, "Kuwagata", 12, "Kuwagata.wav");
        kamakiri = new CoreMedal("Kamakiri Arms", Tier.I, MedalType.ARMS, "Kamakiri", 10, "Kamakiri.wav");
        // batta digunakan lagi untuk legs

        // Latorartar
        lion = new CoreMedal("Lion Head", Tier.II, MedalType.HEAD, "Lion", 15, "Lion.wav");
        // tora digunakan lagi untuk arms
        cheetah = new CoreMedal("Cheetah Legs", Tier.II, MedalType.LEGS, "Cheetah", 14, "Cheetah.wav");

        // Sagohzo
        sai = new CoreMedal("Sai Head", Tier.III, MedalType.HEAD, "Sai", 20, "Sai.wav");
        gorilla = new CoreMedal("Gorilla Arms", Tier.III, MedalType.ARMS, "Gorilla", 25, "Gorilla.wav");
        zou = new CoreMedal("Zou Legs", Tier.III, MedalType.LEGS, "Zou", 20, "Zou.wav");

        // Shauta
        shachi = new CoreMedal("Shachi Head", Tier.I, MedalType.HEAD, "Shachi", 10, "Shachi.wav");
        unagi = new CoreMedal("Unagi Arms", Tier.I, MedalType.ARMS, "Unagi", 10, "Unagi.wav");
        tako = new CoreMedal("Tako Legs", Tier.I, MedalType.LEGS, "Tako", 9, "Tako.wav");

        // Tajadol
        // taka digunakan lagi untuk head
        kujaku = new CoreMedal("Kujaku Arms", Tier.II, MedalType.ARMS, "Kujaku", 18, "Kujaku.wav");
        condor = new CoreMedal("Condor Legs", Tier.II, MedalType.LEGS, "Condor", 16, "Condor.wav");

        // Putotyra
        ptera = new CoreMedal("Ptera Head", Tier.III, MedalType.HEAD, "Ptera", 25, "Ptera.wav");
        tricera = new CoreMedal("Tricera Arms", Tier.III, MedalType.ARMS, "Tricera", 22, "Tricera.wav");
        tyranno = new CoreMedal("Tyranno Legs", Tier.III, MedalType.LEGS, "Tyranno", 24, "Tyranno.wav");

        // Burakawani
        cobra = new CoreMedal("Cobra Head", Tier.II, MedalType.HEAD, "Cobra", 15, "Cobra.wav");
        kame = new CoreMedal("Kame Arms", Tier.I, MedalType.ARMS, "Kame", 10, "Kame.wav");
        wani = new CoreMedal("Wani Legs", Tier.II, MedalType.LEGS, "Wani", 12, "Wani.wav");
    }

    // ==================== 8 Official Combos ====================

    @Test
    void testTatobaCombo() {
        driver.insertMedal(taka, MedalType.HEAD);
        driver.insertMedal(tora, MedalType.ARMS);
        driver.insertMedal(batta, MedalType.LEGS);

        TransformationResult result = engine.transform(driver, false);
        assertTrue(result.isSuccess());
        assertEquals("Tatoba", result.getComboName());
        assertFalse(result.isMixedForm());
        assertEquals("TATOBA.wav", result.getSoundFile());
        assertTrue(result.getPower() > 0);
    }

    @Test
    void testGatakiribaCombo() {
        driver.insertMedal(kuwagata, MedalType.HEAD);
        driver.insertMedal(kamakiri, MedalType.ARMS);
        driver.insertMedal(batta, MedalType.LEGS);

        TransformationResult result = engine.transform(driver, false);
        assertTrue(result.isSuccess());
        assertEquals("Gatakiriba", result.getComboName());
        assertFalse(result.isMixedForm());
        assertEquals("GATAKIRIBA.wav", result.getSoundFile());
        assertTrue(result.getPower() > 0);
    }

    @Test
    void testLatorartarCombo() {
        driver.insertMedal(lion, MedalType.HEAD);
        driver.insertMedal(tora, MedalType.ARMS);
        driver.insertMedal(cheetah, MedalType.LEGS);

        TransformationResult result = engine.transform(driver, false);
        assertTrue(result.isSuccess());
        assertEquals("Latorartar", result.getComboName());
        assertFalse(result.isMixedForm());
        assertEquals("LATORARTAR.wav", result.getSoundFile());
        assertTrue(result.getPower() > 0);
    }

    @Test
    void testSagohzoCombo() {
        driver.insertMedal(sai, MedalType.HEAD);
        driver.insertMedal(gorilla, MedalType.ARMS);
        driver.insertMedal(zou, MedalType.LEGS);

        TransformationResult result = engine.transform(driver, false);
        assertTrue(result.isSuccess());
        assertEquals("Sagohzo", result.getComboName());
        assertFalse(result.isMixedForm());
        assertEquals("SAGOHZO.wav", result.getSoundFile());
        assertTrue(result.getPower() > 0);
    }

    @Test
    void testShautaCombo() {
        driver.insertMedal(shachi, MedalType.HEAD);
        driver.insertMedal(unagi, MedalType.ARMS);
        driver.insertMedal(tako, MedalType.LEGS);

        TransformationResult result = engine.transform(driver, false);
        assertTrue(result.isSuccess());
        assertEquals("Shauta", result.getComboName());
        assertFalse(result.isMixedForm());
        assertEquals("SHAUTA.wav", result.getSoundFile());
        assertTrue(result.getPower() > 0);
    }

    @Test
    void testTajadolCombo() {
        driver.insertMedal(taka, MedalType.HEAD);
        driver.insertMedal(kujaku, MedalType.ARMS);
        driver.insertMedal(condor, MedalType.LEGS);

        TransformationResult result = engine.transform(driver, false);
        assertTrue(result.isSuccess());
        assertEquals("Tajadol", result.getComboName());
        assertFalse(result.isMixedForm());
        assertEquals("TAJADOL.wav", result.getSoundFile());
        assertTrue(result.getPower() > 0);
    }

    @Test
    void testPutotyraCombo() {
        driver.insertMedal(ptera, MedalType.HEAD);
        driver.insertMedal(tricera, MedalType.ARMS);
        driver.insertMedal(tyranno, MedalType.LEGS);

        TransformationResult result = engine.transform(driver, false);
        assertTrue(result.isSuccess());
        assertEquals("Putotyra", result.getComboName());
        assertFalse(result.isMixedForm());
        assertEquals("PUTOTYRA.wav", result.getSoundFile());
        assertTrue(result.getPower() > 0);
    }

    @Test
    void testBurakawaniCombo() {
        driver.insertMedal(cobra, MedalType.HEAD);
        driver.insertMedal(kame, MedalType.ARMS);
        driver.insertMedal(wani, MedalType.LEGS);

        TransformationResult result = engine.transform(driver, false);
        assertTrue(result.isSuccess());
        assertEquals("Burakawani", result.getComboName());
        assertFalse(result.isMixedForm());
        assertEquals("BURAKAWANI.wav", result.getSoundFile());
        assertTrue(result.getPower() > 0);
    }

    // ==================== Other Tests ====================

    @Test
    void testMixedFormDetected() {
        driver.insertMedal(kuwagata, MedalType.HEAD);
        driver.insertMedal(tora, MedalType.ARMS);      // mixed
        driver.insertMedal(batta, MedalType.LEGS);

        TransformationResult result = engine.transform(driver, false);
        assertTrue(result.isSuccess());
        assertEquals("Mixed Form", result.getComboName());
        assertTrue(result.isMixedForm());
        assertEquals(0, result.getPower());
    }

    @Test
    void testScanningChargeOnRescan() {
        driver.insertMedal(taka, MedalType.HEAD);
        driver.insertMedal(tora, MedalType.ARMS);
        driver.insertMedal(batta, MedalType.LEGS);

        TransformationResult first = engine.transform(driver, false);
        assertFalse(first.isScanningCharge());

        TransformationResult second = engine.transform(driver, true);
        assertTrue(second.isScanningCharge());
        assertTrue(second.getMessage().contains("SCANNING CHARGE"));
    }

    @Test
    void testIncompleteSlotsReturnsFailure() {
        driver.insertMedal(taka, MedalType.HEAD);
        TransformationResult result = engine.transform(driver, false);
        assertFalse(result.isSuccess());
        assertEquals("Slot Driver belum lengkap.", result.getMessage());
    }

    @Test
    void testPowerCalculationBasedOnTier() {
        driver.insertMedal(taka, MedalType.HEAD);   // 10 * 1 * 3 = 30
        driver.insertMedal(tora, MedalType.ARMS);   // 15 * 2 * 3 = 90
        driver.insertMedal(batta, MedalType.LEGS);  //  8 * 1 * 3 = 24
        TransformationResult result = engine.transform(driver, false);
        assertEquals(144, result.getPower());
    }
}