package com.ooo.simulator.model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ooo.simulator.model.Driver.OOODriver;
import com.ooo.simulator.model.Driver.OScanner;
import com.ooo.simulator.model.Engine.TransformationResult;
import com.ooo.simulator.model.Exception.ScanNotReadyException;
import com.ooo.simulator.model.Model.CoreMedal;
import com.ooo.simulator.model.Model.MedalType;
import com.ooo.simulator.model.Model.Tier;
import com.ooo.simulator.model.Storage.Storage;

class OScannerTest {
    private OScanner scanner;
    private OOODriver driver;
    private Storage storage;
    private CoreMedal taka, tora, batta;

    @BeforeEach
    public void setUp() {
        scanner = new OScanner();
        driver = new OOODriver();
        storage = new Storage();
        taka = new CoreMedal("Taka", Tier.I, MedalType.HEAD, "Taka", 10, "Taka.wav");
        tora = new CoreMedal("Tora", Tier.II, MedalType.ARMS, "Tora", 15, "Tora.wav");
        batta = new CoreMedal("Batta", Tier.I, MedalType.LEGS, "Batta", 8, "Batta.wav");
    }

    @Test
    void testScanDriverThrowsWhenNotFull() {
        driver.insertMedal(taka, MedalType.HEAD);
        assertThrows(ScanNotReadyException.class, () -> scanner.scanDriver(driver));
    }

    @Test
    void testScanDriverSucceedsWhenFull() {
        driver.insertMedal(taka, MedalType.HEAD);
        driver.insertMedal(tora, MedalType.ARMS);
        driver.insertMedal(batta, MedalType.LEGS);
        TransformationResult result = scanner.scanDriver(driver);
        assertTrue(result.isSuccess());
        assertEquals(3, result.getMedalsUsed().size());
    }

    @Test
    void testScanStorage() {
        storage.addMedal(taka);
        storage.addMedal(tora);
        TransformationResult result = scanner.scanStorage(storage);
        assertTrue(result.isSuccess());
        assertEquals(2, result.getMedalsUsed().size());
    }

    @Test
    void testScanMedal() {
        TransformationResult result = scanner.scanMedal(taka);
        assertTrue(result.isSuccess());
        assertEquals(1, result.getMedalsUsed().size());
        assertTrue(result.getMessage().contains("Taka"));
    }

    @Test
    void testHitungJumlahMedal() {
        List<CoreMedal> medals = List.of(taka, tora, batta);
        assertEquals(3, scanner.hitungJumlahMedal(medals));
    }

    @Test
    void testKetahuiKodeMedal() {
        assertEquals(Tier.I, scanner.ketahuiKodeMedal(taka));
        assertEquals(Tier.II, scanner.ketahuiKodeMedal(tora));
    }

    @Test
    void testSuaraCombo() {
        assertDoesNotThrow(() -> scanner.suaraCombo(true));
        assertDoesNotThrow(() -> scanner.suaraCombo(false));
    }

    @Test
    void testCancel() {
        assertDoesNotThrow(() -> scanner.cancel());
    }

    @Test
    void testScannerActiveStatus() {
        assertTrue(scanner.isActive());
        scanner.setActive(false);
        assertFalse(scanner.isActive());
        // Saat tidak aktif, scanDriver harus throw
        driver.insertMedal(taka, MedalType.HEAD);
        driver.insertMedal(tora, MedalType.ARMS);
        driver.insertMedal(batta, MedalType.LEGS);
        assertThrows(ScanNotReadyException.class, () -> scanner.scanDriver(driver));
    }
}