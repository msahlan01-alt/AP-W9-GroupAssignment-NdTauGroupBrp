package com.ooo.simulator.model;

import com.ooo.simulator.model.Model.CoreMedal;
import com.ooo.simulator.model.Model.MedalType;
import com.ooo.simulator.model.Model.Tier;
import com.ooo.simulator.model.Storage.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {
    private Storage storage;
    private CoreMedal medal1, medal2;

    @BeforeEach
    void setUp() {
        storage = new Storage();
        medal1 = new CoreMedal("Taka", Tier.I, MedalType.HEAD, "Taka", 10, "Taka.wav");
        medal2 = new CoreMedal("Tora", Tier.II, MedalType.ARMS, "Tora", 15, "Tora.wav");
    }

    @Test
    void testAddMedalIncreasesCount() {
        storage.addMedal(medal1);
        assertEquals(1, storage.getAllMedals().size());
        storage.addMedal(medal2);
        assertEquals(2, storage.getAllMedals().size());
    }

    @Test
    void testRemoveMedalDecreasesCount() {
        storage.addMedal(medal1);
        storage.removeMedal(medal1);
        assertEquals(0, storage.getAllMedals().size());
    }

    @Test
    void testPressFrontShowsMedals() {
        storage.addMedal(medal1);
        storage.addMedal(medal2);
        // Hanya verifikasi tidak throw exception dan menjalankan print
        assertDoesNotThrow(() -> storage.pressFront());
    }

    @Test
    void testOpenAndClose() {
        assertDoesNotThrow(() -> storage.open());
        assertDoesNotThrow(() -> storage.close());
    }

    @Test
    void testPullBackOpensStorage() {
        storage.addMedal(medal1);
        assertDoesNotThrow(() -> storage.pullBack());
    }

    @Test
    void testCancel() {
        assertDoesNotThrow(() -> storage.cancel());
    }
}