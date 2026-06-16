package de.juliusxyz.schrottcraft;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class SchrottBankTest {
    @Test
    void newPlayersStartWithNothing() {
        SchrottBank bank = new SchrottBank();

        assertEquals(0, bank.pointsFor(UUID.fromString("00000000-0000-0000-0000-000000000001")));
    }

    @Test
    void canAddAndSpendPoints() {
        SchrottBank bank = new SchrottBank();
        UUID player = UUID.fromString("00000000-0000-0000-0000-000000000002");

        bank.add(player, 9);

        assertTrue(bank.trySpend(player, 4));
        assertEquals(5, bank.pointsFor(player));
    }

    @Test
    void cannotSpendMoreThanAvailable() {
        SchrottBank bank = new SchrottBank();
        UUID player = UUID.fromString("00000000-0000-0000-0000-000000000003");

        bank.add(player, 2);

        assertFalse(bank.trySpend(player, 3));
        assertEquals(2, bank.pointsFor(player));
    }

    @Test
    void keepsTypicalPlayersApart() {
        SchrottBank bank = new SchrottBank();
        UUID anna = UUID.fromString("7f1608a4-9f4e-4f48-a6c9-0f2ebf2d8801");
        UUID ben = UUID.fromString("086b7037-88fb-4f68-9990-59ce54c72458");

        bank.add(anna, 11);
        bank.add(ben, 3);

        assertEquals(11, bank.pointsFor(anna));
        assertEquals(3, bank.pointsFor(ben));
    }
}
