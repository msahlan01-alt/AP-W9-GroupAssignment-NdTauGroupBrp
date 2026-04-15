    package com.ooo.simulator.model;

    import static org.junit.jupiter.api.Assertions.assertEquals;
    import static org.junit.jupiter.api.Assertions.assertFalse;
    import static org.junit.jupiter.api.Assertions.assertNull;
    import static org.junit.jupiter.api.Assertions.assertThrows;
    import static org.junit.jupiter.api.Assertions.assertTrue;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;

    import com.ooo.simulator.model.Driver.OOODriver;
    import com.ooo.simulator.model.Exception.InvalidSlotException;
    import com.ooo.simulator.model.Exception.SlotOccupiedException;
    import com.ooo.simulator.model.Model.CoreMedal;
    import com.ooo.simulator.model.Model.MedalType;
    import com.ooo.simulator.model.Model.Tier;

    class OOODriverTest {
        private OOODriver driver;
        private CoreMedal takaHead;
        private CoreMedal toraArms;
        private CoreMedal battaLegs;

        @BeforeEach
        void setUp() {
            driver = new OOODriver();
            takaHead = new CoreMedal("Taka Head", Tier.I, MedalType.HEAD, "Taka", 10, "Taka.wav");
            toraArms = new CoreMedal("Tora Arms", Tier.II, MedalType.ARMS, "Tora", 15, "Tora.wav");
            battaLegs = new CoreMedal("Batta Legs", Tier.I, MedalType.LEGS, "Batta", 8, "Batta.wav");
        }

        @Test
        void testInsertMedalIntoSameBodyPartSlot() {
            driver.insertMedal(takaHead, MedalType.HEAD);
            assertEquals(takaHead, driver.getMedalInSlot(MedalType.HEAD));
        }

        @Test
        void testInsertMedalIntoWrongSlotThrowsException() {
            assertThrows(InvalidSlotException.class, () -> {
                driver.insertMedal(takaHead, MedalType.ARMS);
            });
        }

        @Test
        void testInsertMedalIntoOccupiedSlotThrowsException() {
            driver.insertMedal(takaHead, MedalType.HEAD);
            CoreMedal anotherHead = new CoreMedal("Lion Head", Tier.II, MedalType.HEAD, "Lion", 15, "Lion.wav");
            assertThrows(SlotOccupiedException.class, () -> {
                driver.insertMedal(anotherHead, MedalType.HEAD);
            });
        }

        @Test
        void testRemoveMedal() {
            driver.insertMedal(takaHead, MedalType.HEAD);
            CoreMedal removed = driver.removeMedal(MedalType.HEAD);
            assertEquals(takaHead, removed);
            assertNull(driver.getMedalInSlot(MedalType.HEAD));
        }

        @Test
        void testIsFullOnlyWhenAllSlotsOccupied() {
            assertFalse(driver.isFull());
            driver.insertMedal(takaHead, MedalType.HEAD);
            assertFalse(driver.isFull());
            driver.insertMedal(toraArms, MedalType.ARMS);
            assertFalse(driver.isFull());
            driver.insertMedal(battaLegs, MedalType.LEGS);
            assertTrue(driver.isFull());
        }

        @Test
        void testScanMiringDoesNotThrow() {
            // Tidak throw exception meskipun belum penuh
            driver.scanMiring();
            driver.insertMedal(takaHead, MedalType.HEAD);
            driver.scanMiring();
        }

        @Test
        void testClearAllSlots() {
            driver.insertMedal(takaHead, MedalType.HEAD);
            driver.insertMedal(toraArms, MedalType.ARMS);
            driver.clearAll();
            assertFalse(driver.isFull());
            assertNull(driver.getMedalInSlot(MedalType.HEAD));
            assertNull(driver.getMedalInSlot(MedalType.ARMS));
        }
    }